package com.wangxingxing.widget;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

public class App extends Application {

    public static final String TAG = "wxx";

    @Override
    public void onCreate() {
        super.onCreate();

        initUtils();
    }

    private void initUtils() {
        Utils.init(this);
        LogUtils.getConfig().setGlobalTag(TAG);
    }
}
