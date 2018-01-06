package com.juawapps.newsspread;



import android.support.test.InstrumentationRegistry;

import com.juawapps.newsspread.data.ArticlesRepository;
import com.juawapps.newsspread.di.DaggerAppTestComponent;
import com.juawapps.newsspread.web.CustomTabHelper;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

/**
 * Used to change the dependencies.
 */

public class TestApp extends DaggerApplication {

    @Inject
    ArticlesRepository mArticlesRepository;

    @Inject
    CustomTabHelper mCustomTabHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppTestComponent.builder().create(this);
    }

    public ArticlesRepository getArticlesRepository() {
        return mArticlesRepository;
    }

    public CustomTabHelper getCustomTabHelper() {
        return mCustomTabHelper;
    }

    public static TestApp get() {
        return (TestApp) InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();
    }
}
