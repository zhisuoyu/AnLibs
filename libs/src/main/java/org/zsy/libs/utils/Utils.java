package org.zsy.libs.utils;

import android.app.Application;
import android.content.Context;

import kotlin.UninitializedPropertyAccessException;

public class Utils {


    private static Context context;

    public static void init(Context context) {
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        Utils.context = context;
    }

    public static Context getAppContext() {
        if (context == null) {
            throw new UninitializedPropertyAccessException("Utils is not inited");
        }
        return context;
    }

}
