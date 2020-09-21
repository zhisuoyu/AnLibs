package org.zsy.libs.framework.app.fb

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle
import org.zsy.libs.lg.Lg

open class FBCallbacks : Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    val TAG = "FBCallbacks"

    override fun onActivityPaused(activity: Activity?) {
        Lg.i(TAG, "onActivityPaused")
    }

    override fun onActivityResumed(activity: Activity?) {
        Lg.i(TAG, "onActivityResumed")
    }

    override fun onActivityStarted(activity: Activity?) {
        Lg.i(TAG, "onActivityStarted")
//        activity?.let { Lg.i(TAG, "start:${FBManager.INSTANCE.isFront(it)}") }

    }

    override fun onActivityDestroyed(activity: Activity?) {
        Lg.i(TAG, "onActivityDestroyed")

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        Lg.i(TAG, "onActivitySaveInstanceState")

    }

    override fun onActivityStopped(activity: Activity?) {
        Lg.i(TAG, "onActivityStopped")

    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        Lg.i(TAG, "onActivityCreated")

    }


    override fun onLowMemory() {
        Lg.i(TAG, "onLowMemory")

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        Lg.i(TAG, "onConfigurationChanged")

    }

    override fun onTrimMemory(level: Int) {
        Lg.i(TAG, "onTrimMemory:$level")

    }


}