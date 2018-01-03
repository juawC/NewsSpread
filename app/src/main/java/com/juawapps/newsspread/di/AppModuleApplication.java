package com.juawapps.newsspread.di;

import android.content.Context;

import com.juawapps.newsspread.NewSpreadApplication;
import com.juawapps.newsspread.data.api.ApiProvider;
import com.juawapps.newsspread.data.api.NewsapiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Database module
 */
@Module
public class AppModuleApplication {

    @Provides
    @Singleton
    Context provideContext(NewSpreadApplication application) {
        return application;
    }
}
