package com.wangxingxing.widget;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.wangxingxing.widget.lsn8.density.DensityUtils;

public class App extends Application {

    public static final String TAG = "wxx";

    @Override
    public void onCreate() {
        super.onCreate();

        initUtils();
        initLifecycle();
    }

    private void initUtils() {
        Utils.init(this);
        LogUtils.getConfig().setGlobalTag(TAG);

        CrashUtils.init(new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(String crashInfo, Throwable e) {
                Log.e(TAG, crashInfo);
                AppUtils.exitApp();
            }
        });
    }

    private void initLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                DensityUtils.setDensity(App.this, activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
