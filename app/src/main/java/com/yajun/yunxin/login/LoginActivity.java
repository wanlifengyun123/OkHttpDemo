package com.yajun.yunxin.login;

import android.Manifest;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yajun.yunxin.R;
import com.yajun.yunxin.activity.BaseActivity;
import com.yajun.yunxin.activity.MainActivity;
import com.yajun.yunxin.activity.WelcomeActivity;
import com.yajun.yunxin.dialog.DialogMaker;
import com.yajun.yunxin.helper.UserHelper;
import com.yajun.yunxin.model.UserModel;
import com.yajun.yunxin.net.OkHttpUtil;
import com.yajun.yunxin.net.event.BaseBusEvent;
import com.yajun.yunxin.net.event.LoginEvent;
import com.yajun.yunxin.net.event.RegisterEvent;
import com.yajun.yunxin.net.iml.UserService;
import com.yajun.yunxin.util.NetworkUtil;
import com.yajun.yunxin.util.ToastUtil;
import com.yajun.yunxin.view.ClearableEditText;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yajun on 2016/9/21.
 *
 */
public class LoginActivity extends BaseActivity {

    String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edit_login_account)
    ClearableEditText loginAccountEdit;
    @Bind(R.id.edit_login_password)
    ClearableEditText loginPasswordEdit;
    @Bind(R.id.login_layout)
    LinearLayout loginLayout;

    @Bind(R.id.edit_register_account)
    ClearableEditText registerAccountEdit;
    @Bind(R.id.edit_register_nickname)
    ClearableEditText registerNickNameEdit;
    @Bind(R.id.edit_register_password)
    ClearableEditText registerPasswordEdit;
    @Bind(R.id.register_layout)
    LinearLayout registerLayout;

    @Bind(R.id.register_login_tip)
    TextView registerLoginTip;

    boolean isLogin = true;
    String mUserAccount;
    String mUserPaass;
    String mUserNick;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        registerEventBus();
        requestBasicPermission(permissions);
        initToolBar(toolbar,false);
        setupLoginPanel();
        setupRegisterPanel();
    }

    @Override
    public void initToolBar(Toolbar toolbar, boolean isBack) {
        super.initToolBar(toolbar, isBack);
        toolbar.setTitle(getResources().getString(R.string.login));
    }

    /**
     * 登录面板
     */
    private void setupLoginPanel() {

        loginAccountEdit.setIconResource(R.drawable.user_account_icon);
        loginPasswordEdit.setIconResource(R.drawable.user_pwd_lock_icon);

        loginAccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        loginPasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
        loginAccountEdit.addTextChangedListener(textWatcher);
        loginPasswordEdit.addTextChangedListener(textWatcher);

        String account = UserHelper.getUserAccount();
        loginAccountEdit.setText(account);

    }

    /**
     * 注册面板
     */
    private void setupRegisterPanel() {
        registerAccountEdit.setIconResource(R.drawable.user_account_icon);
        registerNickNameEdit.setIconResource(R.drawable.user_nick_icon);
        registerPasswordEdit.setIconResource(R.drawable.user_pwd_lock_icon);

        registerAccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        registerNickNameEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        registerPasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        registerAccountEdit.addTextChangedListener(textWatcher);
        registerNickNameEdit.addTextChangedListener(textWatcher);
        registerPasswordEdit.addTextChangedListener(textWatcher);
    }

    @OnClick(R.id.register_login_tip)
    public void onClickTip(){
        if(isLogin){
            isLogin = false;
        }else {
            isLogin = true;
        }
        registerLayout.setVisibility(isLogin ? View.GONE : View.VISIBLE);
        loginLayout.setVisibility(isLogin ? View.VISIBLE : View.GONE);

        String account = UserHelper.getUserAccount();
        loginAccountEdit.setText(account);

        registerLoginTip.setText(isLogin?getResources().getString(R.string.register):getResources().getString(R.string.login_has_account));
    }

    public void doLogin() {
        mUserAccount = loginAccountEdit.getText().toString().trim();
        mUserPaass = loginPasswordEdit.getText().toString().trim();
        if(TextUtils.isEmpty(mUserAccount) || TextUtils.isEmpty(mUserPaass)){
            ToastUtil.show("账户密码不能为空");
            return;
        }
        if (!NetworkUtil.isNetAvailable(LoginActivity.this)) {
            ToastUtil.show(R.string.network_is_not_available);
            return;
        }
        DialogMaker.showProgressDialog(getSupportFragmentManager(),"登录中...");
        UserService.login(mUserAccount,mUserPaass);
    }

    public void doRegister(){
        if(!checkRegisterContentValid()){
            return;
        }
        if (!NetworkUtil.isNetAvailable(LoginActivity.this)) {
            ToastUtil.show(R.string.network_is_not_available);
            return;
        }
        // 注册流程
        final String account = registerAccountEdit.getText().toString();
        final String nickName = registerNickNameEdit.getText().toString();
        final String password = registerPasswordEdit.getText().toString();
        DialogMaker.showProgressDialog(getSupportFragmentManager(),"注册中...");
        UserService.register(account,nickName,password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(LoginEvent event){
        DialogMaker.dismissProgressDialog();
        UserModel userModel = OkHttpUtil.getData(this,event);
        if(userModel != null){
            UserHelper.saveUserModel(userModel);
            // 进入主界面
            MainActivity.start(LoginActivity.this, null);
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegister(RegisterEvent event){
        DialogMaker.dismissProgressDialog();
        UserModel userModel = OkHttpUtil.getData(this,event);
        if(userModel != null){

        }
    }

    public static void start(WelcomeActivity welcomeActivity) {
        Intent intent = new Intent(welcomeActivity, LoginActivity.class);
        welcomeActivity.startActivity(intent);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // 更新右上角按钮状态
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add("完成"), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if("完成".equals(item.getTitle())){
            if(isLogin){
                doLogin();
            }else {
                doRegister();
            }
            ToastUtil.show(isLogin?"登录成功":"注册成功");
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private boolean checkRegisterContentValid() {
        // 帐号检查
        String account = registerAccountEdit.getText().toString().trim();
        if (account.length() <= 0 || account.length() > 20) {
            Toast.makeText(this, R.string.register_account_tip, Toast.LENGTH_SHORT).show();
            return false;
        }

        // 昵称检查
        String nick = registerNickNameEdit.getText().toString().trim();
        if (nick.length() <= 0 || nick.length() > 10) {
            Toast.makeText(this, R.string.register_nick_name_tip, Toast.LENGTH_SHORT).show();
            return false;
        }

        // 密码检查
        String password = registerPasswordEdit.getText().toString().trim();
        if (password.length() < 6 || password.length() > 20) {
            Toast.makeText(this, R.string.register_password_tip, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        unregisterEventBus();
        super.onDestroy();
    }
}
