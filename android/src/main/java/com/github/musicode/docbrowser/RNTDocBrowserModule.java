package com.github.musicode.docbrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.io.File;
import java.net.URLConnection;

public class RNTDocBrowserModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNTDocBrowserModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNTDocBrowser";
    }

    @ReactMethod
    public void open(ReadableMap options) {

        String path = options.getString("path");

        File file = new File(path);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        Activity activity = reactContext.getCurrentActivity();
        if (activity != null && mimeType != null && !mimeType.isEmpty()) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            // https://developer.android.com/reference/android/support/v4/content/FileProvider.html
            // rb-fetch-blob 实现了一个 fileprovider，直接用它的
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Uri uri = FileProvider.getUriForFile(activity, getReactApplicationContext().getPackageName() + ".provider", file);
                intent.setDataAndType(uri, mimeType);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            else {
                Uri uri = Uri.fromFile(file);
                intent.setDataAndType(uri, mimeType);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            activity.startActivity(intent);

        }

    }

}