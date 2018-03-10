package com.juawapps.newsspread.ui.news

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.juawapps.newsspread.R
import com.juawapps.newsspread.TestApp
import com.juawapps.newsspread.categories.NewsCategoriesManager

import com.juawapps.newsspread.data.Resource
import com.juawapps.newsspread.data.objects.Article

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.juawapps.newsspread.util.ObjectCreatorsUtil
import org.hamcrest.CoreMatchers.allOf
import org.mockito.Mockito.reset
import org.mockito.Mockito.`when`

/**
 * News activity test.
 */
@RunWith(AndroidJUnit4::class)
class NewsActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(NewsActivity::class.java, true, false)

    @JvmField
    @Rule
    var mInstantRule = InstantTaskExecutorRule()

    private lateinit var mArticlesListFrag0 :MutableLiveData<Resource<List<Article>>>
    private lateinit var mArticlesListFrag1 :MutableLiveData<Resource<List<Article>>>

    private val mArticlesRepository = TestApp.get().articlesRepository
    private val mNewsCategories = NewsCategoriesManager().categories

    @Before
    fun init() {
        mArticlesListFrag0 = MutableLiveData()
        mArticlesListFrag1 = MutableLiveData()
        reset(mArticlesRepository)
        `when`(mArticlesRepository.getArticles(mNewsCategories[0].key)).thenReturn(mArticlesListFrag0)
        `when`(mArticlesRepository.getArticles(mNewsCategories[1].key)).thenReturn(mArticlesListFrag1)
    }

    @Test
    fun loadedTabs() {
        mActivityRule.launchActivity(Intent())

        onView(withText(mNewsCategories[0].label)).check(matches(isDisplayed()))
        onView(withText(mNewsCategories[1].label)).check(matches(isDisplayed()))
        onView(withText(mNewsCategories[2].label)).check(matches(isDisplayed()))
    }

    @Test
    fun loadedFragment() {
        val article = ObjectCreatorsUtil.createArticle()
        mArticlesListFrag0.postValue(Resource.success(listOf(article)))
        mActivityRule.launchActivity(Intent())

        onView(allOf(withId(R.id.news_item_title), withText(article.title))).check(matches(isDisplayed()))
    }

    @Test
    fun switchFragment() {
        val articleFirst = ObjectCreatorsUtil.createArticle("First")
        mArticlesListFrag0.postValue(Resource.success(listOf(articleFirst)))
        val articleSecond = ObjectCreatorsUtil.createArticle("Second")
        mArticlesListFrag1.postValue(Resource.success(listOf(articleSecond)))
        mActivityRule.launchActivity(Intent())

        onView(allOf(withId(R.id.news_item_title), withText(articleFirst.title))).check(matches(isDisplayed()))

        onView(withText(mNewsCategories[1].label)).perform(click())

        onView(allOf(withId(R.id.news_item_title), withText(articleSecond.title))).check(matches(isDisplayed()))
    }

}
