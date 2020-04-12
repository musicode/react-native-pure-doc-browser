package com.github.musicode.docbrowser

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap

import java.io.File

class RNTDocBrowserModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "RNTDocBrowser"
    }

    @ReactMethod
    fun open(options: ReadableMap, promise: Promise) {

        val activity = reactContext.currentActivity ?: return

        val path = options.getString("path")
        val mimeType = options.getString("mimeType")

        val file = File(path)
        if (!file.exists()) {
            promise.reject("1", "file is not existed.")
            return
        }

        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)

        // https://developer.android.com/reference/androidx.core.content.FileProvider.html
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val uri = FileProvider.getUriForFile(activity, reactApplicationContext.packageName + ".provider", file)
            intent.setDataAndType(uri, mimeType)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        } else {
            val uri = Uri.fromFile(file)
            intent.setDataAndType(uri, mimeType)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val list = reactContext.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        if (list.size > 0) {
            activity.startActivity(intent)
            promise.resolve(Arguments.createMap())
        } else {
            promise.reject("2", "no activity for the intent.")
        }

    }

}