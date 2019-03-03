package com.github.musicode.docbrowser;

import android.content.Intent;
import android.net.Uri;

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

        if (mimeType != null && !mimeType.isEmpty()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, mimeType);
        }

    }

}