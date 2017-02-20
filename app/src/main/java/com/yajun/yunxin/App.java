package com.yajun.yunxin;

import android.app.Application;
import android.content.Context;

import com.yajun.yunxin.util.SystemUtil;

/**
 * Created by yajun on 2016/9/21.
 *
 */
public class App extends Application {

    static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (inMainProcess(this)) {

        }
    }

    public static boolean inMainProcess(Context context) {
        String packageName = context.getPackageName();
        String processName = SystemUtil.getProcessName(context);
        return packageName.equals(processName);
    }
}
