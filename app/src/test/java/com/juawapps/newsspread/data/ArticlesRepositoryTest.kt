package com.juawapps.newsspread.data


import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

import com.juawapps.newsspread.data.api.ApiResponseWrapper
import com.juawapps.newsspread.data.api.NewsapiService
import com.juawapps.newsspread.data.db.ArticleDao
import com.juawapps.newsspread.data.objects.Article
import com.juawapps.newsspread.util.ObjectCreatorsUtil
import com.juawapps.newsspread.utils.AppExecutors

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever

import java.util.ArrayList
import java.util.Arrays
import java.util.concurrent.Executor

import retrofit2.Response

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

@RunWith(JUnit4::class)
class ArticlesRepositoryTest {

    private lateinit var mNewsapiService: NewsapiService
    private lateinit var mArticleDao: ArticleDao

    private lateinit var mObserver: Observer<Resource<List<Article>>>

    private val mInstantExecutor = Executor { it.run() }
    private val mAppExecutors = AppExecutors(mInstantExecutor,
            mInstantExecutor,
            mInstantExecutor)

    private lateinit var mArticlesRepository: ArticlesRepository

    private lateinit var mLiveDataService: MutableLiveData<ApiResponseWrapper<List<Article>>>
    private lateinit var mLiveDataDb: MutableLiveData<List<Article>>

    @JvmField
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        mNewsapiService = mock()
        mArticleDao = mock()

        mObserver = mock()

        mArticlesRepository = ArticlesRepository(mNewsapiService, mArticleDao, mAppExecutors)

        mLiveDataDb = MutableLiveData()
        mLiveDataService = MutableLiveData()
    }

    @Test
    fun loadArticlesNetwork() {

        val testFirst = ObjectCreatorsUtil.createArticle("test_first")
        val testSecond = ObjectCreatorsUtil.createArticle("test_second")
        val articleList = ArrayList(Arrays.asList(testFirst, testSecond))

        mLiveDataService.postValue(ApiResponseWrapper(Response.success<List<Article>>(articleList)))
        val category = "category"

        whenever(mNewsapiService.topHeadLines(category)).thenReturn(mLiveDataService)
        whenever(mArticleDao.loadArticlesByCategory(category)).thenReturn(mLiveDataDb)

        val result = mArticlesRepository.getArticles(category)
        verify(mArticleDao).loadArticlesByCategory(category)
        verifyNoMoreInteractions(mNewsapiService)

        result.observeForever(mObserver)

        verifyNoMoreInteractions(mNewsapiService)
        verify(mObserver).onChanged(Resource.loading<List<Article>>(null))

        mLiveDataDb.postValue(null)
        verify(mNewsapiService).topHeadLines(category)
        verify(mArticleDao).insertArticles(articleList)
        mLiveDataDb.postValue(articleList)

        verify(mObserver).onChanged(Resource.success<List<Article>>(articleList))
        verifyNoMoreInteractions(mNewsapiService)
        assertThat(result.value?.data!![0].category, `is`(category))
    }

}
