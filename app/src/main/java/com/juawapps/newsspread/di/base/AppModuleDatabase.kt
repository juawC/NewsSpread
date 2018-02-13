package com.juawapps.newsspread.di.base

import android.content.Context

import com.juawapps.newsspread.data.db.AppDatabase
import com.juawapps.newsspread.data.db.ArticleDao
import com.juawapps.newsspread.data.db.DatabaseProvider

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Database module
 */
@Module
class AppModuleDatabase {

    @Provides
    @Singleton
    internal fun provideNewsDatabase(applicationContext: Context): AppDatabase {
        return DatabaseProvider.getDatabase(applicationContext)
    }

    @Singleton
    @Provides
    internal fun provideUserDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }

}
