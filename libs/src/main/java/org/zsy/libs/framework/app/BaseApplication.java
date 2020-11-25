package org.zsy.libs.framework.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;


public class BaseApplication extends Application {

    private static BaseApplication instance;


    private Handler handler;


    public static BaseApplication getInstance() {
        return instance;
    }


    public Handler getHandler() {
        return handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        instance = this;
        handler = new Handler(Looper.getMainLooper());
    }
}
