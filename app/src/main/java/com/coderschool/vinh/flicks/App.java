package com.coderschool.vinh.flicks;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.facebook.stetho.Stetho;

public class App extends Application {
    @Override public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
        Stetho.initializeWithDefaults(this);
    }
}