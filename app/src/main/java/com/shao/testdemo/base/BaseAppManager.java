package com.shao.testdemo.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.LinkedList;
import java.util.List;



public class BaseAppManager {
    private static final String TAG = BaseAppManager.class.getSimpleName();

    private static BaseAppManager instance = null;
    private static List<Activity> mActivities = new LinkedList<>();

    private BaseAppManager() {

    }

    //单例
    public static BaseAppManager getInstance() {
        if (null == instance) {
            synchronized (BaseAppManager.class) {
                if (null == instance) {
                    instance = new BaseAppManager();
                }
            }
        }
        return instance;
    }
    //获取打开的activity数量
    public int size() {
        return mActivities.size();
    }

    //获得位于前台的activity
    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    //将activity添加到集合中
    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    //移除一个activity
    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }
    //关闭所有的activity
    public synchronized void clear() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }

    //清除掉除过栈顶以外的activity
    public synchronized void clearTop() {
        for (int i = mActivities.size() - 2; i >= 0; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            clear();
            ActivityManager activityMgr =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    /**
     * @return app是否已退出
     */
    public boolean isAppExit() {
        return mActivities == null || mActivities.isEmpty();
    }
}
