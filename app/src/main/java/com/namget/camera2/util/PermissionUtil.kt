package com.namget.camera2.util

import android.Manifest
import android.content.Context
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class PermissionUtil {

    companion object {
        fun CameraPermissionCheck(context: Context, permissionListener: PermissionListener) {
            TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
                .check()
        }
    }
}