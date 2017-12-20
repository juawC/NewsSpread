package com.juawapps.newsspread.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.juawapps.newsspread.data.api.ApiProvider;
import com.juawapps.newsspread.data.api.ApiResponseWrapper;
import com.juawapps.newsspread.data.db.ArticleDao;
import com.juawapps.newsspread.data.db.DatabaseProvider;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.data.api.NewsapiService;

import java.util.List;


public class ArticlesRepository {
    private final NewsapiService mNewsapiService;
    private final ArticleDao mArticleDao;
    private static ArticlesRepository sInstance;

    private ArticlesRepository (Context applicationContext) {
        mNewsapiService = ApiProvider.getNewsapiService();
        mArticleDao = DatabaseProvider.getAppDatabase(applicationContext).articleDao();
    }

    public static ArticlesRepository getInstance(Context applicationContext) {
        if (sInstance == null) {
            synchronized (ArticlesRepository.class) {
                if (sInstance == null) {
                    sInstance = new ArticlesRepository(applicationContext);
                }
            }
        }
        return sInstance;
    }

    public LiveData<Resource<List<Article>>> getArticles(String categoryKey) {
        return new NetworkBoundResource<List<Article>, List<Article>>() {

            @Override
            protected void saveCallResult(@NonNull List<Article> items) {
                // TODO port this to streams when porting to kot
                for (Article article : items) {
                    article.setCategory(categoryKey);
                }
                mArticleDao.insertArticles(items);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Article> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Article>> loadFromDb() {
                return mArticleDao.loadArticlesByCategory(categoryKey);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponseWrapper<List<Article>>> createCall() {
                return mNewsapiService.topHeadLines(categoryKey);
            }
        }.asLiveData();
}

}
