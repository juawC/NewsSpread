package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.SimpleArrayMap;

import com.juawapps.newsspread.data.ArticlesRepository;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.data.objects.Article;

import java.util.List;

/**
 * Created by joaocevada on 15/12/2017.
 */

public class NewsViewModel extends ViewModel {

    private final SimpleArrayMap<String, ArticlesLiveData> mArticlesMap =
            new SimpleArrayMap<>();

    private final ArticlesRepository mArticlesRepository;

    public NewsViewModel (ArticlesRepository articlesRepository) {
        mArticlesRepository = articlesRepository;
    }

    LiveData<Resource<List<Article>>> getArticleByCategory(String category) {

        ArticlesLiveData articlesLiveData;

        if (mArticlesMap.containsKey(category) ) {
            articlesLiveData = mArticlesMap.get(category);
        } else {
            articlesLiveData = new ArticlesLiveData(mArticlesRepository);
            mArticlesMap.put(category, articlesLiveData);
        }
        articlesLiveData.setCategory(category);
        return articlesLiveData.getResultStream();
    }

    private static class ArticlesLiveData {

        private final ArticlesRepository mArticlesRepository;
        private final MutableLiveData<String> mRequestStream;
        private final LiveData<Resource<List<Article>>> mResultStream ;

        public ArticlesLiveData(ArticlesRepository articlesRepository) {
            mArticlesRepository = articlesRepository;
            mRequestStream = new MutableLiveData<>();
            mResultStream =
                    Transformations.switchMap(mRequestStream,
                            mArticlesRepository::getArticles);

        }

        public LiveData<Resource<List<Article>>> getResultStream() {
            return mResultStream;
        }

        public void setCategory(String category) {
            mRequestStream.postValue(category);
        }

    }
}
