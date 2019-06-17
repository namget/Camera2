package com.namget.camera2.util

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.namget.camera2.BuildConfig

fun Activity.e(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message)
    }
}

fun Activity.e(TAG: String, message: String, e: Throwable) {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, message, e)
    }
}

fun Activity.d(TAG: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message)
    }
}

fun Activity.d(TAG: String, message: String, e: Throwable) {
    if (BuildConfig.DEBUG) {
        Log.d(TAG, message, e)
    }
}

fun Activity.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()