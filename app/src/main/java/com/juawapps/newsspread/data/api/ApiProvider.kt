package com.juawapps.newsspread.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.juawapps.newsspread.data.api.adapters.LiveDataCallAdapterFactory
import com.juawapps.newsspread.data.api.adapters.UnwrapConverterFactory
import com.juawapps.newsspread.data.api.interceptors.ApiKeyInterceptor
import com.juawapps.newsspread.data.api.interceptors.LanguageInterceptor

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.juawapps.newsspread.data.api.ApiConfigs.DATE_FORMAT
import com.juawapps.newsspread.data.api.ApiConfigs.HOST


/**
 * Provides an api service object.
 */

object ApiProvider {

    val apiService: NewsapiService
        get() {

            val httpClient = OkHttpClient.Builder()
                    .addInterceptor(ApiKeyInterceptor())
                    .addInterceptor(LanguageInterceptor())

            val gson = GsonBuilder()
                    .setDateFormat(ApiConfigs.DATE_FORMAT)
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConfigs.HOST)
                    .client(httpClient.build())
                    .addConverterFactory(UnwrapConverterFactory(GsonConverterFactory.create(gson)))
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()

            return retrofit.create(NewsapiService::class.java)
        }

}
