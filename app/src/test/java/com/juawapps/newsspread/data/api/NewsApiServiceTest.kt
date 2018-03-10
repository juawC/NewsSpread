package com.juawapps.newsspread.data.api


import android.arch.core.executor.testing.InstantTaskExecutorRule

import com.google.gson.GsonBuilder
import com.juawapps.newsspread.data.api.adapters.LiveDataCallAdapterFactory
import com.juawapps.newsspread.data.api.adapters.UnwrapConverterFactory
import com.juawapps.newsspread.util.LiveDataTestUtil
import com.juawapps.newsspread.util.MockWebServerRule

import org.junit.Before
import org.junit.Rule
import org.junit.Test

import java.text.SimpleDateFormat
import java.util.Date

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import junit.framework.Assert.assertNull
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

class NewsApiServiceTest {

    @Rule
    @JvmField
    val mInstantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mMockWebServer = MockWebServerRule()

    private lateinit var mService: NewsapiService

    @Before
    fun createService() {
        val gson = GsonBuilder()
                .setDateFormat(ApiConfigs.DATE_FORMAT)
                .create()

        mService = Retrofit.Builder()
                .baseUrl(mMockWebServer.url)
                .addConverterFactory(UnwrapConverterFactory(GsonConverterFactory.create(gson)))
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(NewsapiService::class.java)
    }


    @Test
    fun getTopHeadLines() {
        mMockWebServer.enqueueResponse("top-headlines-tech.json")

        val category = "tech"
        val headLines = LiveDataTestUtil.getValue(mService.topHeadLines(category)).body

        headLines ?: throw Throwable("Empty headlines!")

        assertThat(headLines.size, `is`(4))

        headLines[0].also {
            assertThat(it.title, `is`("The cryptocurrency bubble is strangling innovation"))
            assertThat(it.description, `is`("Sure, fine, maybe it's a bubble. OK it's definitely a bubble, but that's a good thing, a bubble brings attention and investment in infrastructure, which.."))
            assertThat(it.urlToImage, `is`("https://tctechcrunch2011.files.wordpress.com/2018/01/sorong-python.jpg"))
            assertThat(it.url, `is`("https://techcrunch.com/2018/01/07/the-cryptocurrency-bubble-is-strangling-innovation/"))
            assertThat(it.author, `is`("Jon Evans"))
            assertThat(it.publishedAt, `is`(getDateFromString("2018-01-07T14:00:16Z")))
            assertThat(it.source?.id, `is`("techcrunch"))
            assertThat(it.source?.name, `is`("TechCrunch"))
        }
    }

    @Test
    fun getTopHeadLinesError() {
        mMockWebServer.enqueueResponse("top-headlines-error.json")

        val category = "tech"
        val response = LiveDataTestUtil.getValue(mService.topHeadLines(category))

        assertNull(response.body)
        assertThat(response.code, `is`(500))
    }


    private fun getDateFromString(dateStr: String): Date {
        val formatter = SimpleDateFormat(ApiConfigs.DATE_FORMAT)
        return formatter.parse(dateStr)
    }
}
