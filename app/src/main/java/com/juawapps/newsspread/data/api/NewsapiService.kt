package com.juawapps.newsspread.data.api

import android.arch.lifecycle.LiveData

import com.juawapps.newsspread.data.objects.Article
import com.juawapps.newsspread.data.objects.Source

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * News api service.
 */
interface NewsapiService {

    @GET("v2/top-headlines")
    fun topHeadLines(@Query("category") category: String): LiveData<ApiResponseWrapper<List<Article>>>

    @GET("v2/sources")
    fun sources(): LiveData<ApiResponseWrapper<List<Source>>>
}
