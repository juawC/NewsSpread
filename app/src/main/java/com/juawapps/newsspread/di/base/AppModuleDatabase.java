package com.juawapps.newsspread.di.base;

import android.content.Context;

import com.juawapps.newsspread.data.db.AppDatabase;
import com.juawapps.newsspread.data.db.ArticleDao;
import com.juawapps.newsspread.data.db.DatabaseProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Database module
 */
@Module
public class AppModuleDatabase {

    @Provides
    @Singleton
    AppDatabase provideNewsDatabase(Context applicationContext) {
        return DatabaseProvider.getDatabase(applicationContext);
    }

    @Singleton
    @Provides
    ArticleDao provideUserDao(AppDatabase database) {
        return database.articleDao();
    }

}
