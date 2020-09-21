package org.zsy.libs.framework.permission;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import org.zsy.libs.dialog.Dg;
import org.zsy.libs.dialog.IDg;
import org.zsy.libs.utils.ArrayUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PermissionManager implements PermissionCallback {


    public final int CODE_REQUEST_NECESSARY_PERMISSIONS = 0;
    public final int CODE_REQUEST_OTHER_PERMISSIONS = 1;
    public final int CODE_REQUEST_CONCAT_PERMISSIONS = 2;
    public final int CODE_REQUEST_SETTING = 10;

    private int requestCount = 0;
    private Activity activity;
    private String[] necessaryPermissions;
    private Map<String, String> permissionMap;
    private String[] otherPermissions;

    public PermissionManager(Activity activity, String[] necessaryPermissions, Map<String, String> permissionMap, String[] otherPermissions) {
        this.activity = activity;
        this.necessaryPermissions = necessaryPermissions;
        this.permissionMap = permissionMap;
        this.otherPermissions = otherPermissions;
    }

    public void start() {
        if (isPermissionsGranted(necessaryPermissions)) {
            onAllNecessaryGranted();
        } else {
            ActivityCompat.requestPermissions(activity, ArrayUtils.concat(String.class, necessaryPermissions, otherPermissions), CODE_REQUEST_CONCAT_PERMISSIONS);
        }
    }

    private void checkOrRequest() {
        if (isPermissionsGranted(necessaryPermissions)) {
            onAllNecessaryGranted();
            return;
        }
        Set<String> should = new HashSet<>();
        HashSet<String> shouldNot = new HashSet<>();
        for (String p : necessaryPermissions) {
            if (!PermissionUtils.isPermissionGranted(activity, p)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, p)) {
                    should.add(p);
                } else {
                    shouldNot.add(p);
                }
            }
        }
        if (should.size() > 0) {
            Dg.showConfirmDg(activity, getNecessaryDes(should), "确定", new IDg.OnClickListener() {
                @Override
                public void onClick(IDg dialog, int which) {
                    ActivityCompat.requestPermissions(activity, necessaryPermissions, CODE_REQUEST_CONCAT_PERMISSIONS);
                }
            }, "取消", new IDg.OnClickListener() {
                @Override
                public void onClick(IDg dialog, int which) {
                    activity.finish();
                }
            });
        } else {
            onNecessaryShouldNot(shouldNot);
        }
    }


    @Override
    public void onAllNecessaryGranted() {

    }

    protected String getNecessaryDes(Set<String> permissions) {
        StringBuilder sb = new StringBuilder();
        for (String p : permissions) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append(permissionMap.get(p));
        }
        sb.append("是必要权限");
        return sb.toString();
    }

    @Override
    public void onNecessaryShouldNot(Set<String> shouldNot) {
        Dg.showConfirmDg(activity, getNecessaryDes(shouldNot), "确定", new IDg.OnClickListener() {
            @Override
            public void onClick(IDg dialog, int which) {
                PermissionUtils.openSetting(activity, CODE_REQUEST_SETTING);
            }
        }, "取消", new IDg.OnClickListener() {
            @Override
            public void onClick(IDg dialog, int which) {
                activity.finish();
            }
        });
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        checkOrRequest();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        checkOrRequest();
    }


    private boolean isPermissionsGranted(String[] permissions) {
        return permissions == null || permissions.length == 0
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || PermissionUtils.isPermissionsGranted(activity, permissions);
    }


}