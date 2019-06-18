package com.namget.camera2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*




class MainActivity : AppCompatActivity() {

    private val CAMERA_REQ = 1
    var fileUri: Uri? = null
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        record.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
        picture.setOnClickListener {
            val intent = Intent()
            //MediaStore.ACTION_IMAGE_CAPTURE = image
            intent.action = android.provider.MediaStore.ACTION_VIDEO_CAPTURE
            fileUri = getOutputMediaFile(MEDIA_TYPE_VIDEO);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // high quality
            startActivityForResult(intent, CAMERA_REQ)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == CAMERA_REQ) {
            if (resultCode == Activity.RESULT_OK) {
                Log.e(TAG, "result ok")
                videoFromCamera(data)
            }
        }

    }


    private fun videoFromCamera(data: Intent?) {

        if (fileUri != null) {
            Log.e(TAG, "Video saved to:\n$fileUri")
            Log.e(TAG, "Video path:\n $fileUri.path")
            // use uri.getLastPathSegment() if store in folder
            //use the file Uri.
        }
    }

    fun getOutputMediaFile(type: Int): Uri? {
        // To be safe, you should check that the SDCard is mounted

        Environment.getExternalStorageState()?.let {
            // this works for Android 2.2 and above
            val mediaStorageDir =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "abcdefg")

            // This location works best if you want the created images to be shared
            // between applications and persist after your app has been uninstalled.

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("test", "failed to create directory")
                    return null
                }
            }

            // Create a media file name
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val mediaFile: File
            if (type == MEDIA_TYPE_VIDEO) {
                mediaFile = File(
                    mediaStorageDir.getPath() + File.separator +
                            "namget_test_" + timeStamp + ".mp4"
                )
            } else {
                return null
            }

            return FileProvider.getUriForFile(this, "com.namget.camera2.fileprovider", mediaFile)

        }

        return null
    }

}