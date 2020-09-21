package org.zsy.libs.ext

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout


fun LinearLayout.addButton(text: String, onclick: (view: View) -> Unit) {
    val layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    val button = Button(context).apply {
        isAllCaps = false
        this.text = text
        setOnClickListener(onclick)
    }
    addView(button, layoutParams)


}
