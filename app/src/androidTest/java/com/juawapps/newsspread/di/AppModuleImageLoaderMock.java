package com.juawapps.newsspread.di;

import com.juawapps.newsspread.image.ImageLoaderWrapper;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;

/**
 * Image loader module
 */
@Module
public class AppModuleImageLoaderMock {
    @Singleton
    @Provides
    ImageLoaderWrapper provideImageLoaderWrapper() {

        ImageLoaderWrapper imageLoaderWrapper = Mockito.mock(ImageLoaderWrapper.class);

        doNothing().when(imageLoaderWrapper).fetchImageToView(anyObject(), anyString(), anyObject());

        return imageLoaderWrapper;
    }
}
