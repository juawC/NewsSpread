package com.juawapps.newsspread.di

import android.content.Context

import com.juawapps.newsspread.TestApp

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Database module
 */
@Module
class AppModuleApplicationTest {

    @Provides
    @Singleton
    internal fun provideContext(application: TestApp): Context {
        return application
    }
}
