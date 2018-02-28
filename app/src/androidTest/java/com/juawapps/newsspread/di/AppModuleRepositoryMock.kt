package com.juawapps.newsspread.di

import com.juawapps.newsspread.data.ArticlesRepository

import org.mockito.Mockito

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Repository module
 */
@Module
class AppModuleRepositoryMock {

    @Singleton
    @Provides
    internal fun provideArticleRepository() : ArticlesRepository = Mockito.mock(ArticlesRepository::class.java)
}
