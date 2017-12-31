package com.juawapps.newsspread;

import android.app.Application;

/**
 * Used to prevent initializing dependency injection.
 */

public class TestApp extends Application {
    @SuppressWarnings("EmptyMethod")
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
