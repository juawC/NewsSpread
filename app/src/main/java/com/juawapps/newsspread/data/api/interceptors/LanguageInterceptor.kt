package com.juawapps.newsspread.data.api.interceptors

import java.io.IOException

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import com.juawapps.newsspread.data.api.ApiConfigs.API_KEY
import com.juawapps.newsspread.data.api.ApiConfigs.LANGUAGE

/**
 * Interceptor for setting the articles language.
 */

class LanguageInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder().addQueryParameter("language", LANGUAGE).build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder().url(url)

        return chain.proceed(requestBuilder.build())
    }
}
