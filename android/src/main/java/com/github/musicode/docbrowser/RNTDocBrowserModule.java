package com.github.musicode.docbrowser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.io.File;
import java.util.List;

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
    public void open(ReadableMap options, final Promise promise) {

        String path = options.getString("path");
        String mimeType = options.getString("mimeType");

        File file = new File(path);

        Activity activity = reactContext.getCurrentActivity();
        if (activity != null) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            // https://developer.android.com/reference/android/support/v4/content/FileProvider.html
            // rb-fetch-blob 实现了一个 fileprovider，直接用它的
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Uri uri = FileProvider.getUriForFile(activity, getReactApplicationContext().getPackageName() + ".provider", file);
                intent.setDataAndType(uri, mimeType);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            else {
                Uri uri = Uri.fromFile(file);
                intent.setDataAndType(uri, mimeType);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            List list = reactContext.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (list.size() > 0) {
                activity.startActivity(intent);
                promise.resolve(Arguments.createArray());
            }
            else {
                promise.reject("1", "no activity for the intent.");
            }

        }
    }

}