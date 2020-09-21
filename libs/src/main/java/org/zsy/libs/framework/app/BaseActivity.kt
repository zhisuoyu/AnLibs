package org.zsy.libs.framework.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.zsy.libs.framework.app.ActManager

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActManager.getInstance().addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        ActManager.getInstance().removeActivity(this)
    }


}
