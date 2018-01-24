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
import java.util.Objects;

import javax.inject.Inject;


public class NewsViewModel extends ViewModel implements RetryCallback {

    private final MutableLiveData<String> mRequestStream = new MutableLiveData<>();
    private final LiveData<Resource<List<Article>>> mResultStream;


    @Inject
    public NewsViewModel (ArticlesRepository articlesRepository) {
        mResultStream = Transformations.switchMap(mRequestStream, articlesRepository::getArticles);
    }

    public void setCategory(String category) {

        if (!Objects.equals(category, mRequestStream.getValue())) {
            mRequestStream.postValue(category);
        }
    }

    public LiveData<Resource<List<Article>>> getArticles() {
        return mResultStream;
    }

    @VisibleForTesting
    @Override
    public void onRetry() {

        String current = mRequestStream.getValue();
        if (current != null && !current.isEmpty()) {
            mRequestStream.setValue(current);
        }
    }
}
