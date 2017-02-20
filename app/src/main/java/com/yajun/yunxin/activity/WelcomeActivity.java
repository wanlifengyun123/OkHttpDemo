package com.yajun.yunxin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.yajun.yunxin.R;
import com.yajun.yunxin.helper.UserHelper;
import com.yajun.yunxin.login.LoginActivity;
import com.yajun.yunxin.util.LogUtil;
import com.yajun.yunxin.util.SystemUtil;


/**
 * Created by yajun on 2016/9/21.
 *
 */
public class WelcomeActivity extends BaseActivity {

    private static final String TAG = "WelcomeActivity";

    private boolean customSplash = false;

    private static boolean firstEnter = true; // 是否首次进入

    @Override
    protected int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initBundle(Intent intent, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            setIntent(new Intent()); // 从堆栈恢复，不再重复解析之前的intent
        }
    }

    @Override
    protected void initView() {
        if (!firstEnter) {
            onIntent();
        } else {
            showSplashView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (firstEnter) {
            firstEnter = false;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (canAutoLogin()) {
                        onIntent();
                    } else {
                        LoginActivity.start(WelcomeActivity.this);
                        finish();
                    }
                }
            };
            if (customSplash) {
                new Handler().postDelayed(runnable, 1000);
            } else {
                runnable.run();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        /**
         * 如果Activity在，不会走到onCreate，而是onNewIntent，这时候需要setIntent
         * 场景：点击通知栏跳转到此，会收到Intent
         */
        setIntent(intent);
        onIntent();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.clear();
    }

    // 处理收到的Intent
    private void onIntent() {
        LogUtil.i(TAG, "onIntent...");

        if (TextUtils.isEmpty(UserHelper.getUserAccount())) {
            // 判断当前app是否正在运行
            if (!SystemUtil.stackResumed(this)) {
                LoginActivity.start(this);
            }
            finish();
        } else {
            // 已经登录过了，处理过来的请求
            Intent intent = getIntent();
            if (intent != null) {

            }

            if (!firstEnter && intent == null) {
                finish();
            } else {
                showMainActivity();
            }
        }
    }

    /**
     * 已经登陆过，自动登陆
     */
    private boolean canAutoLogin() {
        String account = UserHelper.getUserAccount();
        String token = UserHelper.getUserToken();

        Log.i(TAG, "get local sdk token =" + token);
        return !TextUtils.isEmpty(account) && !TextUtils.isEmpty(token);
    }



    private void parseNormalIntent(Intent intent) {
        showMainActivity(intent);
    }

    /**
     * 首次进入，打开欢迎界面
     */
    private void showSplashView() {
        getWindow().setBackgroundDrawableResource(R.drawable.splash_bg);
        customSplash = true;
    }

    private void showMainActivity() {
        showMainActivity(null);
    }

    private void showMainActivity(Intent intent) {
        MainActivity.start(WelcomeActivity.this, intent);
        finish();
    }
}
