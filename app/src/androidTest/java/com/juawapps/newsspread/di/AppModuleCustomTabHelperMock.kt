package com.juawapps.newsspread.di


import com.juawapps.newsspread.web.CustomTabHelper
import com.nhaarman.mockito_kotlin.any

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import org.mockito.Mockito


/**
 * Initialization of objects used throughout the app.
 */

@Module
class AppModuleCustomTabHelperMock {

    @Singleton
    @Provides
    internal fun providesCustomTabHelper() : CustomTabHelper = Mockito.mock(CustomTabHelper::class.java).also {
        Mockito.doNothing().`when`(it).openNewTab(any(), any())}
}
