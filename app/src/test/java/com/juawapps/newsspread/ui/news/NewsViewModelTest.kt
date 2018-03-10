package com.juawapps.newsspread.ui.news

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer

import com.juawapps.newsspread.data.ArticlesRepository
import com.juawapps.newsspread.data.Resource
import com.juawapps.newsspread.data.objects.Article
import com.nhaarman.mockito_kotlin.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import java.util.Arrays

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.notNullValue


@RunWith(JUnit4::class)
class NewsViewModelTest {

    @JvmField
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mNewsViewModel: NewsViewModel
    private lateinit var articlesRepository: ArticlesRepository

    @Before
    fun setup() {
        articlesRepository = mock()
        mNewsViewModel = NewsViewModel(articlesRepository)
    }


    @Test
    fun testNull() {
        assertThat(mNewsViewModel.articles, notNullValue())
        verify(articlesRepository, never()).getArticles(any())
    }

    @Test
    fun dontFetchWithoutObservers() {
        mNewsViewModel.setCategory("Magic")
        verify(articlesRepository, never()).getArticles(any())
    }

    @Test
    fun fetchWhenObserved() {

        mNewsViewModel.setCategory("Magic")
        mNewsViewModel.articles.observeForever(mock<Observer<Resource<List<Article>>>>())
        verify(articlesRepository).getArticles("Magic")
    }

    @Test
    fun changeWhileObserved() {
        val categoryCapture = argumentCaptor<String>()
        mNewsViewModel.articles.observeForever(mock<Observer<Resource<List<Article>>>>())

        mNewsViewModel.setCategory("Magic")
        mNewsViewModel.setCategory("MagicRepeat")

        verify(articlesRepository, times(2)).getArticles(categoryCapture.capture())
        assertThat(categoryCapture.allValues, `is`(Arrays.asList("Magic", "MagicRepeat")))
    }


    @Test
    fun retry() {
        mNewsViewModel.onRetry()
        verifyNoMoreInteractions(articlesRepository)
        mNewsViewModel.setCategory("Magic")
        verifyNoMoreInteractions(articlesRepository)
        val observer = mock<Observer<Resource<List<Article>>>>()
        mNewsViewModel.articles.observeForever(observer)
        verify(articlesRepository).getArticles("Magic")
        reset(articlesRepository)
        mNewsViewModel.onRetry()
        verify(articlesRepository).getArticles("Magic")
    }

}
