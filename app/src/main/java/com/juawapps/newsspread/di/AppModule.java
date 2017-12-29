package com.juawapps.newsspread.di;

import android.content.Context;

import com.juawapps.newsspread.NewSpreadApplication;
import com.juawapps.newsspread.data.api.ApiProvider;
import com.juawapps.newsspread.data.api.NewsapiService;
import com.juawapps.newsspread.data.db.AppDatabase;
import com.juawapps.newsspread.data.db.ArticleDao;
import com.juawapps.newsspread.data.db.DatabaseProvider;
import com.juawapps.newsspread.di.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Initialization of objects used throughout the app.
 */

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(NewSpreadApplication application) {
        return application;
    }

    @Provides
    @Singleton
    NewsapiService provideNewsApiService() {
        return ApiProvider.getApiService();
    }

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
