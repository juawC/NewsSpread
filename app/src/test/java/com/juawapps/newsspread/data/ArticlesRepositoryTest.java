package com.juawapps.newsspread.data;


import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.juawapps.newsspread.data.api.ApiResponseWrapper;
import com.juawapps.newsspread.data.api.NewsapiService;
import com.juawapps.newsspread.data.db.ArticleDao;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.util.ObjectCreatorsUtil;
import com.juawapps.newsspread.utils.AppExecutors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class ArticlesRepositoryTest {

    private NewsapiService mNewsapiService;
    private ArticleDao mArticleDao;

    private Observer<Resource<List<Article>>> mObserver;

    private final Executor mInstantExecutor = Runnable::run;
    private final AppExecutors mAppExecutors = new AppExecutors(mInstantExecutor,
            mInstantExecutor,
            mInstantExecutor);

    private ArticlesRepository mArticlesRepository;

    MutableLiveData<ApiResponseWrapper<List<Article>>> mLiveDataService;
    MutableLiveData<List<Article>> mLiveDataDb;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        mNewsapiService = Mockito.mock(NewsapiService.class);
        mArticleDao = Mockito.mock(ArticleDao.class);

        mObserver = Mockito.mock(Observer.class);

        mArticlesRepository = new ArticlesRepository(mNewsapiService, mArticleDao, mAppExecutors);

        mLiveDataDb = new MutableLiveData<>();
        mLiveDataService = new MutableLiveData<>();
    }

    @Test
    public void loadArticlesNetwork() {

        Article testFirst = ObjectCreatorsUtil.createArticle("test_first");
        Article testSecond = ObjectCreatorsUtil.createArticle("test_second");
        List<Article> articleList = new ArrayList<>(Arrays.asList(testFirst, testSecond));

        mLiveDataService.postValue(new ApiResponseWrapper<>(Response.success(articleList)));
        String category = "category";

        Mockito.when(mNewsapiService.topHeadLines(category)).thenReturn(mLiveDataService);
        Mockito.when(mArticleDao.loadArticlesByCategory(category)).thenReturn(mLiveDataDb);

        LiveData<Resource<List<Article>>> result = mArticlesRepository.getArticles(category);
        Mockito.verify(mArticleDao).loadArticlesByCategory(category);
        Mockito.verifyNoMoreInteractions(mNewsapiService);

        result.observeForever(mObserver);

        Mockito.verifyNoMoreInteractions(mNewsapiService);
        Mockito.verify(mObserver).onChanged(Resource.loading(null));

        mLiveDataDb.postValue(null);
        Mockito.verify(mNewsapiService).topHeadLines(category);
        Mockito.verify(mArticleDao).insertArticles(articleList);
        mLiveDataDb.postValue(articleList);

        Mockito.verify(mObserver).onChanged(Resource.success(articleList));
        Mockito.verifyNoMoreInteractions(mNewsapiService);
        assertThat(result.getValue().data.get(0).getCategory(), is(category));
    }

}
