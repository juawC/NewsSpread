package com.juawapps.newsspread.di;

import com.juawapps.newsspread.image.ImageLoaderWrapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Image loader module
 */
@Module
public class AppModuleImageLoader {
    @Singleton
    @Provides
    ImageLoaderWrapper provideImageLoaderWrapper() {
        return new ImageLoaderWrapper();
    }
}
