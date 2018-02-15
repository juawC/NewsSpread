package com.juawapps.newsspread.data.api.interceptors

import java.io.IOException

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import com.juawapps.newsspread.data.api.ApiConfigs.API_KEY

/**
 * Interceptor for setting the api key
 */

class ApiKeyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder().addQueryParameter("apiKey", API_KEY).build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder().url(url)

        return chain.proceed(requestBuilder.build())
    }
}
