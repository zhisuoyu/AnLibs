package org.zsy.libs.framework.app.fb

import android.app.Activity

/**
 * 应用进入前台或后台回调接口
 */
interface FBListener {

    fun onFront(activity: Activity?)

    fun onBack(activity: Activity?)
}