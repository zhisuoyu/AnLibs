package org.zsy.libs.ext


import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment






fun Context.tst(msg: String) =
        Toast.makeText(this, null, Toast.LENGTH_SHORT).apply {
            setText(msg)
            show()
        }


fun Context.ltst(msg: String) =
        Toast.makeText(this, null, Toast.LENGTH_LONG).apply {
            setText(msg)
            show()
        }

fun Fragment.tst(msg: String) = requireActivity().tst(msg)

fun Fragment.ltst(msg: String) = requireActivity().ltst(msg)

fun PackageInfo.compatVersionCode(): Long =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            longVersionCode
        } else {
            versionCode.toLong()
        }