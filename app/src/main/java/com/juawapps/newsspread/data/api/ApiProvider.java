package com.juawapps.newsspread.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juawapps.newsspread.data.api.adapters.LiveDataCallAdapterFactory;
import com.juawapps.newsspread.data.api.adapters.UnwrapConverterFactory;
import com.juawapps.newsspread.data.api.interceptors.ApiKeyInterceptor;
import com.juawapps.newsspread.data.api.interceptors.LanguageInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.juawapps.newsspread.data.api.ApiConfigs.DATE_FORMAT;
import static com.juawapps.newsspread.data.api.ApiConfigs.HOST;


/**
 * Provides an api service object.
 */

public class ApiProvider {

    public static NewsapiService getApiService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .addInterceptor(new LanguageInterceptor());

        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(httpClient.build())
                .addConverterFactory(new UnwrapConverterFactory(GsonConverterFactory.create(gson)))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();

        return retrofit.create(NewsapiService.class);
    }

}
