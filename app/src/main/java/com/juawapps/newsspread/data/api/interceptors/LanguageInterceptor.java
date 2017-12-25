package com.juawapps.newsspread.data.api.interceptors;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.juawapps.newsspread.data.api.ApiConfigs.API_KEY;
import static com.juawapps.newsspread.data.api.ApiConfigs.LANGUAGE;

/**
 * Interceptor for setting the articles language.
 */

public class LanguageInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("language", LANGUAGE)
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
