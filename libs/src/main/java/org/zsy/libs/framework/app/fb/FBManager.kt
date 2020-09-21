package org.zsy.libs.framework.app.fb

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE


/**
 * 应用进入前台或后台回调管理
 */
class FBManager {
    val TAG = "FBManager"

    companion object {
        @SuppressLint("StaticFieldLeak")
        val INSTANCE = FBManager()
    }

    private var isFront = false
    private var fbCallbacks: FBCallbacks? = null
    private var context: Context? = null


    fun setFBListener(
            application: Application,
            fbListener: FBListener,
            filterFirst: Boolean = true
    ) {
//        removeFBListener(application)
        context = application
        if (filterFirst) {
            isFront = true
        }
        fbCallbacks = object : FBCallbacks() {

            var currentActivity: Activity? = null

            override fun onActivityResumed(activity: Activity?) {
                super.onActivityResumed(activity)
                currentActivity = activity
                if (!isFront) {
                    isFront = true
                    fbListener.onFront(activity)
                }
            }

            override fun onTrimMemory(level: Int) {
                super.onTrimMemory(level)
                if (isBack(level, context)) {
                    isFront = false
                    fbListener.onBack(currentActivity)
                }
            }
        }
        application.registerActivityLifecycleCallbacks(fbCallbacks)
        application.registerComponentCallbacks(fbCallbacks)

    }


    private fun isBack(level: Int, context: Context?): Boolean {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN || level == ComponentCallbacks2.TRIM_MEMORY_BACKGROUND) {
            return true
        } else if (level == ComponentCallbacks2.TRIM_MEMORY_COMPLETE) {
            if (context != null) {
                return !isFront(context)
            }
        }
        return false
    }

//    fun isCurAppTop(context: Context): Boolean {
//        val curPackageName = context.packageName
//        val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
//        val list = am.getRunningTasks(1)
//        if (list != null && list.size > 0) {
//            val info = list[0]
//            val topPackageName = info.topActivity.packageName
//            val basePackageName = info.baseActivity.packageName
//            if (topPackageName == curPackageName && basePackageName == curPackageName) {
//                return true
//            }
//        }
//        return false
//    }

    fun isFront(context: Context): Boolean {
        val am: ActivityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = am.runningAppProcesses ?: return false
        appProcesses.forEach {
            if (it.processName == context.packageName
                    && it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            ) {
                return true
            }
        }
        return false
    }

    fun removeFBListener(application: Application) {
        if (fbCallbacks != null) {
            application.unregisterActivityLifecycleCallbacks(fbCallbacks)
            application.unregisterComponentCallbacks(fbCallbacks)
        }
    }


//    fun(){
//        application.registerActivityLifecycleCallbacks(object :
//            Application.ActivityLifecycleCallbacks {
//            override fun onActivityPaused(activity: Activity?) {
//
//            }
//
//            override fun onActivityResumed(activity: Activity?) {
//                if (!isFront) {
//                    isFront = true
//                    FBListener.onFront()
//                }
//            }
//
//            override fun onActivityStarted(activity: Activity?) {
//
//            }
//
//            override fun onActivityDestroyed(activity: Activity?) {
//
//            }
//
//            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
//
//            }
//
//            override fun onActivityStopped(activity: Activity?) {
//
//            }
//
//            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
//
//            }
//
//        })
//        application.registerComponentCallbacks(object : ComponentCallbacks2 {
//            override fun onLowMemory() {
//
//            }
//
//            override fun onConfigurationChanged(newConfig: Configuration?) {
//
//            }
//
//            override fun onTrimMemory(level: Int) {
//                if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
//                    isFront = false
//                    FBListener.onBack()
//                }
//            }
//
//        })
//    }


}
