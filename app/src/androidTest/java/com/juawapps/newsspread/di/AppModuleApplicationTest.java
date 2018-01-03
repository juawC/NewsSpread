package com.juawapps.newsspread.di;

import android.content.Context;

import com.juawapps.newsspread.TestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Database module
 */
@Module
public class AppModuleApplicationTest {

    @Provides
    @Singleton
    Context provideContext(TestApp application) {
        return application;
    }
}
