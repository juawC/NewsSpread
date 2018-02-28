package com.juawapps.newsspread.di

import com.juawapps.newsspread.image.ImageLoaderWrapper
import com.nhaarman.mockito_kotlin.any

import org.mockito.Mockito

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Image loader module
 */
@Module
class AppModuleImageLoaderMock {
    @Singleton
    @Provides
    internal fun provideImageLoaderWrapper() : ImageLoaderWrapper = Mockito.mock(ImageLoaderWrapper::class.java).also {
        Mockito.doNothing().`when`(it).fetchImageToView(any(), any(), any())}
}
