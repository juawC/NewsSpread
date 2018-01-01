package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.juawapps.newsspread.data.ArticlesRepository;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.ui.common.RetryCallback;

import java.util.List;

import javax.inject.Inject;


public class NewsViewModel extends ViewModel implements RetryCallback {

    private final MutableLiveData<String> mRequestStream = new MutableLiveData<>();
    private final LiveData<Resource<List<Article>>> mResultStream;

    private String mCategory;

    @Inject
    public NewsViewModel (ArticlesRepository articlesRepository) {
        mResultStream = Transformations.switchMap(mRequestStream, articlesRepository::getArticles);
    }

    @VisibleForTesting
    public void setCategory(String category) {
        mRequestStream.postValue(category);
        mCategory = category;
    }

    @VisibleForTesting
    public LiveData<Resource<List<Article>>> getArticles() {
        return mResultStream;
    }

    @VisibleForTesting
    @Override
    public void onRetry() {
        setCategory(mCategory);
    }
}
