package com.yajun.yunxin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.yajun.yunxin.R;
import com.yajun.yunxin.common.ActivityManager;
import com.yajun.yunxin.net.event.LogoutEvent;
import com.yajun.yunxin.permission.MPermission;
import com.yajun.yunxin.permission.annotation.OnMPermissionDenied;
import com.yajun.yunxin.permission.annotation.OnMPermissionGranted;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by yajun on 2016/9/21.
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private final int BASIC_PERMISSION_REQUEST_CODE = 1100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initBundle(getIntent(),savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        EventBus.getDefault().register(this);

        initView();
    }

    //布局文件ID
    protected abstract int getContentViewId();

    protected void initBundle(Intent intent, Bundle savedInstanceState){}

    protected abstract void initView();

    public void initToolBar(Toolbar toolbar, boolean isBack){
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        if(isBack){
            hideSoftKeyboard();
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    /**
     * hide inputMethod
     */
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager != null ) {
            View localView = getCurrentFocus();
            if(localView != null && localView.getWindowToken() != null ) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    /**
     * 基本权限管理
     * Manifest.permission.WRITE_EXTERNAL_STORAGE,
     * Manifest.permission.READ_EXTERNAL_STORAGE
     */
    public void requestBasicPermission(String... permissions) {
        MPermission.with(this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(permissions)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess(){
        Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed(){
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }

    protected boolean isCompatible(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }

    protected void registerEventBus(){
    }

    protected void unregisterEventBus(){

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogout(LogoutEvent event){

    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().removeActivity(this);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
