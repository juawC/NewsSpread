package com.juawapps.newsspread.data.api;

import android.arch.lifecycle.LiveData;

import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.data.objects.Source;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * News api service.
 */
@SuppressWarnings("unused")
public interface NewsapiService {

    @GET("v2/top-headlines")
    LiveData<ApiResponseWrapper<List<Article>>> topHeadLines(@Query("category") String category);

    @GET("v2/sources")
    LiveData<ApiResponseWrapper<List<Source>>> sources();
}
