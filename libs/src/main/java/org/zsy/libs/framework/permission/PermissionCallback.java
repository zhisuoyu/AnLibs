package org.zsy.libs.framework.permission;

import java.util.Set;

public interface PermissionCallback {

    void onAllNecessaryGranted();

    void onNecessaryShouldNot(Set<String> set);

}
