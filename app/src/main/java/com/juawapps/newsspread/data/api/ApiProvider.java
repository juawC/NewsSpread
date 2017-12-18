package com.juawapps.newsspread.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juawapps.newsspread.data.api.adapters.LiveDataCallAdapterFactory;
import com.juawapps.newsspread.data.api.adapters.UnwrapConverterFactory;
import com.juawapps.newsspread.data.api.interceptors.ApiKeyInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.juawapps.newsspread.data.api.ApiConfigs.HOST;


/**
 * Created by joaocevada on 10/12/2017.
 */

public class ApiProvider {

    private static ApiProvider sInstance;

    private final NewsapiService mNewsapiService;

    private ApiProvider() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor());

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(httpClient.build())
                .addConverterFactory(new UnwrapConverterFactory(GsonConverterFactory.create(gson)))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();

        mNewsapiService = retrofit.create(NewsapiService.class);
    }

    private static ApiProvider getInstance() {
        if (sInstance == null) {
            synchronized (ApiProvider.class) {
                if (sInstance == null) {
                    sInstance = new ApiProvider();
                }
            }
        }
        return sInstance;
    }

    public static NewsapiService getNewsapiService () {
        return getInstance().mNewsapiService;
    }

}
