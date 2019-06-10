package com.namget.camera2.util

import android.app.Activity
import android.content.Intent


fun imageSelect(context: Activity, requestCode: Int) {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    context.startActivityForResult(intent, requestCode)
}