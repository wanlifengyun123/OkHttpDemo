package com.yajun.yunxin.util;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * 系统工具箱
 */
public class SystemUtil {

    /**
     * 获取当前进程名
     * @param context
     * @return 进程名
     */
    public static final String getProcessName(Context context) {
        String processName = null;

        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;

                    break;
                }
            }

            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }

            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean stackResumed(Context context) {
        ActivityManager manager = (ActivityManager) context
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();
        List<ActivityManager.RunningTaskInfo> recentTaskInfos = manager.getRunningTasks(1);
        if (recentTaskInfos != null && recentTaskInfos.size() > 0) {
            ActivityManager.RunningTaskInfo taskInfo = recentTaskInfos.get(0);
            if (taskInfo.baseActivity.getPackageName().equals(packageName) && taskInfo.numActivities > 1) {
                return true;
            }
        }

        return false;
    }

}
