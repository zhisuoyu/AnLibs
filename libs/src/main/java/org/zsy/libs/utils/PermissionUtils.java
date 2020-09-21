package org.zsy.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by mzs on 2017/2/6.
 */

public class PermissionUtils {

//    public static final int CODE_SETTING_PERMISSION = 0x1000;

    public static boolean isPermissionGranted(Context context, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isPermissionsGranted(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

//    public static void openSetting(Activity activity, int requestCode) {
//        Intent intent = new Intent();
//        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.setData(Uri.parse("package:" + activity.getPackageName()));
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//        activity.startActivityForResult(intent, requestCode);
//    }

    public static void openSetting(Activity activity, int resultCode) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, resultCode);
    }

}
