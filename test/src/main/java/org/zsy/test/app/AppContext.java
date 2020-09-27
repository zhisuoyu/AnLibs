package org.zsy.test.app;

import android.app.Application;

import org.zsy.libs.utils.Utils;

public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
