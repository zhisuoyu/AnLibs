package org.zsy.libs.framework.manager;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import org.zsy.libs.framework.app.ApplicationUtils;


//<receiver
//            android:name=".app.api.MyAdminReceiver"
//                    android:permission="android.permission.BIND_DEVICE_ADMIN">
//<meta-data
//        android:name="android.app.device_admin"
//        android:resource="@xml/admin" />
//
//<intent-filter>
//<action android:name="android.app.action.DEVICE_ADMIN_ENABLED" />
//</intent-filter>
//</receiver>

public class LockManager {

    //    private Context appContext;
    private DevicePolicyManager devicePolicyManager;
    private ComponentName componentName;

    private static LockManager lockManager = new LockManager();

    public static LockManager getInstance() {
        return lockManager;
    }

    private LockManager() {
        Context context = ApplicationUtils.getAppContext();
        devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(context, MyAdminReceiver.class);
    }

//    public void init(@NonNull Class<?> cls) {
////        appContext = context instanceof Application ? context : context.getApplicationContext();
//        Context context = ContextHolder.getInstance().get();
//        devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
//        componentName = new ComponentName(context, cls);
//    }

    public boolean isAdminActive() {
        return devicePolicyManager.isAdminActive(componentName);
    }

    public void activeAdmin(Activity activity, int requestCode, String description) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, description);
        activity.startActivityForResult(intent, requestCode);
    }

    public void inactiveAdmin() {
        devicePolicyManager.removeActiveAdmin(componentName);
    }

    public void lock() {
        devicePolicyManager.lockNow();
    }


    public static class MyAdminReceiver extends DeviceAdminReceiver {

    }

}
