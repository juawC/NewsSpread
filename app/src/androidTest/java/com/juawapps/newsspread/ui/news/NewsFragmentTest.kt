package com.juawapps.newsspread.ui.news

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasChildCount
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withChild
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

import com.juawapps.newsspread.R
import com.juawapps.newsspread.TestApp
import com.juawapps.newsspread.categories.NewsCategory
import com.juawapps.newsspread.data.Resource
import com.juawapps.newsspread.data.objects.Article
import com.juawapps.newsspread.test.SingleFragmentActivity
import com.juawapps.newsspread.util.ObjectCreatorsUtilInst
import com.juawapps.newsspread.utils.ui.FormatUtils
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers.not

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`


/**
 * Fragment news test.
 */
@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @JvmField
    @Rule
    var mInstantRule = InstantTaskExecutorRule()

    private lateinit var mArticlesList: MutableLiveData<Resource<List<Article>>>
    private val mArticlesRepository = TestApp.get().articlesRepository
    private val mCustomTabHelper = TestApp.get().customTabHelper

    @Before
    @Throws(Throwable::class)
    fun init() {
        val categoryKey = "category-key"

        reset(mArticlesRepository)
        mArticlesList = MutableLiveData()
        `when`(mArticlesRepository.getArticles(categoryKey)).thenReturn(mArticlesList)

        mActivityRule.activity.replaceFragment(NewsFragment.newInstance(NewsCategory(categoryKey, R.string.general_label)))
    }

    @Test
    fun loading() {
        mArticlesList.postValue(Resource.loading<List<Article>>(null))
        onView(withId(R.id.spin_kit)).check(matches(isDisplayed()))
        onView(withId(R.id.error_retry_button)).check(matches(not(isDisplayed())))
    }


    @Test
    @Throws(InterruptedException::class)
    fun error() {
        val article = ObjectCreatorsUtilInst.createArticle()
        val articleList = listOf(article)

        mArticlesList.postValue(Resource.error<List<Article>>("error", null))

        onView(withId(R.id.spin_kit)).check(matches(not(isDisplayed())))
        verify(mArticlesRepository).getArticles(eq("category-key"))
        onView(withId(R.id.error_text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.error_retry_button)).check(matches(isDisplayed()))

        var mArticlesListGood = MutableLiveData<Resource<List<Article>>>()
        mArticlesListGood.postValue(Resource.success(articleList))
        `when`(mArticlesRepository.getArticles("category-key")).thenReturn(mArticlesListGood)
        onView(withId(R.id.error_retry_button)).perform(click())

        onView(withId(R.id.news_item_title)).check(matches(withText(article.title)))
    }

    @Test
    fun loadedArticle() {
        val article = ObjectCreatorsUtilInst.createArticle()
        val articleList = listOf(article)
        mArticlesList.postValue(Resource.success(articleList))
        onView(withId(R.id.news_item_title)).check(matches(withText(article.title)))
        onView(withId(R.id.news_item_subtitle)).check(matches(withText(article.description)))
        onView(withId(R.id.news_item_source)).check(matches(withText(article.source!!.name)))
        onView(withId(R.id.news_item_age)).check(
                matches(withText(FormatUtils.dateToAge(
                        mActivityRule.activity, article.publishedAt))))
        onView(withId(R.id.spin_kit)).check(matches(not(isDisplayed())))
    }

    @Test
    fun clickArticle() {
        val article = ObjectCreatorsUtilInst.createArticle()
        val articleList = listOf(article)
        mArticlesList.postValue(Resource.success(articleList))
        onView(withChild(withText(article.title))).perform(click())
        verify(mCustomTabHelper).openNewTab(eq(article.url)!!, any())
    }

    @Test
    fun nullArticleList() {
        mArticlesList.postValue(null)
        onView(withId(R.id.list)).check(matches(hasChildCount(0)))
    }
}
