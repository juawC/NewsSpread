package com.juawapps.newsspread.di

import android.content.Context

import com.juawapps.newsspread.NewSpreadApplication

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Database module
 */
@Module
class AppModuleApplication {

    @Provides
    @Singleton
    internal fun provideContext(application: NewSpreadApplication): Context {
        return application
    }
}
