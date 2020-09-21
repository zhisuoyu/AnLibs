package org.zsy.test.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.act_ll.*
import org.zsy.libs.ext.addButton
import org.zsy.libs.lg.Lg
import org.zsy.test.R

class KtAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_ll)
        ll.addButton("Test") {
            Lg.i("Hello Kt")
        }
    }
}