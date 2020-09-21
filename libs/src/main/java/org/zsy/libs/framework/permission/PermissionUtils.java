package org.zsy.libs.framework.permission;

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


    public static void openSetting(Activity activity, int resultCode) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, resultCode);
    }

}
