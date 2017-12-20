package com.juawapps.newsspread;

import android.app.Application;

import timber.log.Timber;

public class NewSpreadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
