package com.juawapps.newsspread.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;

import com.juawapps.newsspread.data.api.ApiResponseWrapper;
import com.juawapps.newsspread.data.db.ArticleDao;
import com.juawapps.newsspread.data.db.DbConfigs;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.data.api.NewsapiService;
import com.juawapps.newsspread.utils.AppExecutors;

import java.util.Calendar;
import java.util.List;


public class ArticlesRepository {

    private final NewsapiService mNewsapiService;
    private final ArticleDao mArticleDao;
    private final AppExecutors mAppExecutors;

    public ArticlesRepository(NewsapiService newsapiService, ArticleDao articleDao,
                              AppExecutors appExecutors) {
        mNewsapiService = newsapiService;
        mArticleDao = articleDao;
        mAppExecutors = appExecutors;
    }

    public LiveData<Resource<List<Article>>> getArticles(String categoryKey) {
        return new NetworkBoundResource<List<Article>, List<Article>>(mAppExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<Article> items) {
                // TODO port this to streams when porting to kot
                for (Article article : items) {
                    article.setCategory(categoryKey);
                }
                mArticleDao.insertArticles(items);
                // Delete old articles
                mArticleDao.deleteOlderArticles(getOldestArticleAllowedDate());
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


    private static long getOldestArticleAllowedDate() {
        return Calendar.getInstance().getTimeInMillis() -
                DbConfigs.MAX_ARTICLE_AGE_DAYS * DateUtils.DAY_IN_MILLIS;
    }

}
