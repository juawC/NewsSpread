package com.juawapps.newsspread;



import com.juawapps.newsspread.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class NewSpreadApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends NewSpreadApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
