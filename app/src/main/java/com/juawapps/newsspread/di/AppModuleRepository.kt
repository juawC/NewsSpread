package com.juawapps.newsspread.di

import com.juawapps.newsspread.data.ArticlesRepository
import com.juawapps.newsspread.data.api.NewsapiService
import com.juawapps.newsspread.data.db.ArticleDao
import com.juawapps.newsspread.utils.AppExecutors

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Repository module
 */
@Module
class AppModuleRepository {

    @Singleton
    @Provides
    internal fun provideArticleRepository(newsapiService: NewsapiService,
                                          articleDao: ArticleDao,
                                          appExecutors: AppExecutors): ArticlesRepository {
        return ArticlesRepository(newsapiService, articleDao, appExecutors)
    }
}
