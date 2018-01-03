package com.juawapps.newsspread.di;

import com.juawapps.newsspread.data.ArticlesRepository;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Repository module
 */
@Module
public class AppModuleRepositoryMock {

    @Singleton
    @Provides
    ArticlesRepository provideArticleRepository() {
        return Mockito.mock(ArticlesRepository.class);
    }
}
