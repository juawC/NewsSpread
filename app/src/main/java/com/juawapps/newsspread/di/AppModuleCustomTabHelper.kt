package com.juawapps.newsspread.di

import android.content.Context

import com.juawapps.newsspread.web.CustomTabHelper

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Initialization of objects used throughout the app.
 */

@Module
class AppModuleCustomTabHelper {

    @Singleton
    @Provides
    internal fun providesCustomTabHelper(context: Context): CustomTabHelper {
        return CustomTabHelper(context)
    }
}
