package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.SimpleArrayMap;

import com.juawapps.newsspread.data.ArticlesRepository;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.ui.common.RetryCallback;

import java.util.List;


public class NewsViewModel extends ViewModel implements RetryCallback {

    private final MutableLiveData<String> mRequestStream = new MutableLiveData<>();
    private final LiveData<Resource<List<Article>>> mResultStream;
    private final ArticlesRepository mArticlesRepository;

    private String mCategory;

    public NewsViewModel (ArticlesRepository articlesRepository) {
        mArticlesRepository = articlesRepository;
        mResultStream = Transformations.switchMap(mRequestStream, mArticlesRepository::getArticles);
    }

    public void setCategory(String category) {
        mRequestStream.postValue(category);
        mCategory = category;
    }
    LiveData<Resource<List<Article>>> getArticles() {

        return mResultStream;
    }

    @Override
    public void onRetry() {
        setCategory(mCategory);
    }
}
