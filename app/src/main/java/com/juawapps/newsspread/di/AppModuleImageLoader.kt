package com.juawapps.newsspread.di

import com.juawapps.newsspread.image.ImageLoaderWrapper

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Image loader module
 */
@Module
class AppModuleImageLoader {

    @Singleton
    @Provides
    internal fun provideImageLoaderWrapper(): ImageLoaderWrapper {
        return ImageLoaderWrapper()
    }
}
