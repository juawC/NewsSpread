package com.juawapps.newsspread.data

import android.arch.lifecycle.LiveData
import android.text.format.DateUtils

import com.juawapps.newsspread.data.api.ApiResponseWrapper
import com.juawapps.newsspread.data.db.ArticleDao
import com.juawapps.newsspread.data.objects.Article
import com.juawapps.newsspread.data.api.NewsapiService
import com.juawapps.newsspread.utils.AppExecutors

import java.util.Calendar

import com.juawapps.newsspread.data.db.MAX_ARTICLE_AGE_DAYS


class ArticlesRepository(private val mNewsApiService: NewsapiService,
                         private val mArticleDao: ArticleDao,
                         private val mAppExecutors: AppExecutors) {


    private val oldestArticleAllowedDate: Long
        get() = Calendar.getInstance().timeInMillis - MAX_ARTICLE_AGE_DAYS * DateUtils.DAY_IN_MILLIS

    fun getArticles(categoryKey: String): LiveData<Resource<List<Article>>> {
        return object : NetworkBoundResource<List<Article>, List<Article>>(mAppExecutors) {

            override fun saveCallResult(item: List<Article>) {
                // Set category
                item.forEach { it.category = categoryKey }
                mArticleDao.insertArticles(item)
                // Delete old articles
                mArticleDao.deleteOlderArticles(oldestArticleAllowedDate)
            }

            override fun shouldFetch(data: List<Article>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Article>> {
                return mArticleDao.loadArticlesByCategory(categoryKey)
            }

            override fun createCall(): LiveData<ApiResponseWrapper<List<Article>>> {
                return mNewsApiService.topHeadLines(categoryKey)
            }
        }.asLiveData()
    }

}
