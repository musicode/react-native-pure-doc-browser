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
import java.util.HashMap

class RNTDocBrowserModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    companion object {
        private const val ERROR_CODE_FILE_NOT_FOUND = "1"
        private const val ERROR_CODE_APP_NOT_FOUND = "2"
    }

    override fun getName(): String {
        return "RNTDocBrowser"
    }

    override fun getConstants(): Map<String, Any>? {

        val constants: MutableMap<String, Any> = HashMap()

        constants["ERROR_CODE_FILE_NOT_FOUND"] = ERROR_CODE_FILE_NOT_FOUND
        constants["ERROR_CODE_APP_NOT_FOUND"] = ERROR_CODE_APP_NOT_FOUND

        return constants

    }

    @ReactMethod
    fun open(options: ReadableMap, promise: Promise) {

        val activity = reactContext.currentActivity ?: return

        val path = options.getString("path")
        val mimeType = options.getString("mimeType")

        val file = File(path)
        if (!checkFileExisted(file, promise)) {
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
        }
        else {
            promise.reject(ERROR_CODE_APP_NOT_FOUND, "app is not found.")
        }

    }

    private fun checkFileExisted(file: File, promise: Promise): Boolean {

        if (!file.exists()) {
            promise.reject(ERROR_CODE_FILE_NOT_FOUND, "file is not found.")
            return false
        }

        return true

    }

}