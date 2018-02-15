package com.juawapps.newsspread.di.base

import com.juawapps.newsspread.data.api.ApiProvider
import com.juawapps.newsspread.data.api.NewsapiService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Database module
 */
@Module
class AppModuleApi {

    @Provides
    @Singleton
    internal fun provideNewsApiService(): NewsapiService {
        return ApiProvider.apiService
    }

}
