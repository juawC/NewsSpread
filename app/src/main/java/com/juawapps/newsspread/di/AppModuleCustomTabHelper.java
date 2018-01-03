package com.juawapps.newsspread.di;

import android.content.Context;

import com.juawapps.newsspread.di.base.AppModuleApi;
import com.juawapps.newsspread.di.base.AppModuleDatabase;
import com.juawapps.newsspread.di.base.ViewModelModule;
import com.juawapps.newsspread.web.CustomTabHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Initialization of objects used throughout the app.
 */

@Module
public class AppModuleCustomTabHelper {

    @Singleton
    @Provides
    CustomTabHelper providesCustomTabHelper(Context context) {
        return new CustomTabHelper(context);
    }
}
