package com.juawapps.newsspread.di;

import android.content.Context;

import com.juawapps.newsspread.NewSpreadApplication;
import com.juawapps.newsspread.di.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Initialization of objects used throughout the app.
 */

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(NewSpreadApplication application) {
        return application;
    }

}
