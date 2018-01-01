package com.juawapps.newsspread.di;

import com.juawapps.newsspread.data.api.ApiProvider;
import com.juawapps.newsspread.data.api.NewsapiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Database module
 */
@Module
public class AppModuleApi {

    @Provides
    @Singleton
    NewsapiService provideNewsApiService() {
        return ApiProvider.getApiService();
    }

}
