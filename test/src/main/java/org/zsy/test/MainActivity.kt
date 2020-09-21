package org.zsy.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.act_ll.*
import org.jetbrains.anko.intentFor
import org.zsy.libs.ext.addButton
import org.zsy.test.ui.JavaAct
import org.zsy.test.ui.KtAct

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_ll)
        ll.addButton("Java") {
            startActivity(intentFor<JavaAct>())
        }
        ll.addButton("Ktx") {
            startActivity(intentFor<KtAct>())
        }
    }

}
