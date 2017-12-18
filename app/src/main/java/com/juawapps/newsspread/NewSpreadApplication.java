package com.juawapps.newsspread;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import timber.log.Timber;

/**
 * Created by joaocevada on 16/12/2017.
 */

public class NewSpreadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            }
        }
    }
}
