package org.zsy.libs.framework.app

import android.app.Application
import org.zsy.libs.framework.app.fb.FBListener

class AppManager {

    companion object {
        val INSTANCE = AppManager()
    }

    private var isFront = true

    private var FBListener: FBListener? = null


    fun registerAppCallback(application: Application, FBListener: FBListener){
        application

    }

//    fun init(application: Application) {
//
//    }


}


//package zsy.framework.app
//
//object AppManagerDp {
//
//    val STATE_IN = 0
//    val STATE_OUT = 1
//
//    var lastMs: Long = -1
//    var state = STATE_OUT
//    var listener: AppListener? = null
//
//    fun init(appListener: AppListener) {
//        listener = appListener
//        listener?.onCreate()
//    }
//
//    fun enter() {
//        if (state == STATE_IN) {
//            return
//        }
//        state = STATE_IN
//        val currentMs = System.currentTimeMillis()
//        listener?.onFront(lastMs, currentMs)
//        updateLastMs(currentMs)
//    }
//
//    fun leave() {
//        state = STATE_OUT
//        val currentMs = System.currentTimeMillis()
//        listener?.onLeave(lastMs, currentMs)
//        updateLastMs(currentMs)
//    }
//
//    private fun updateLastMs(currentMs: Long) {
//        lastMs = currentMs
//    }
//
//}
