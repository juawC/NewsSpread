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

    private final MutableLiveData<String> mCategory = new MutableLiveData<>();
    private final LiveData<Resource<List<Article>>> mArticles;


    @Inject
    public NewsViewModel (ArticlesRepository articlesRepository) {
        mArticles = Transformations.switchMap(mCategory, articlesRepository::getArticles);
    }

    public void setCategory(String category) {

        if (!Objects.equals(category, mCategory.getValue())) {
            mCategory.postValue(category);
        }
    }

    public LiveData<Resource<List<Article>>> getArticles() {
        return mArticles;
    }

    @VisibleForTesting
    @Override
    public void onRetry() {

        String current = mCategory.getValue();
        if (current != null && !current.isEmpty()) {
            mCategory.setValue(current);
        }
    }
}
