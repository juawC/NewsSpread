package com.juawapps.newsspread.ui.news;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.juawapps.newsspread.data.ArticlesRepository;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.data.objects.Article;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;


@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class NewsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private NewsViewModel mNewsViewModel;
    private ArticlesRepository articlesRepository;

    @Before
    public void setup() {
        articlesRepository = mock(ArticlesRepository.class);
        mNewsViewModel = new NewsViewModel(articlesRepository);
    }



    @Test
    public void testNull() {
        assertThat(mNewsViewModel.getArticles(), notNullValue());
        Mockito.verify(articlesRepository, never()).getArticles(anyString());
    }

    @Test
    public void dontFetchWithoutObservers() {
        mNewsViewModel.setCategory("Magic");
        Mockito.verify(articlesRepository, never()).getArticles(anyString());
    }

    @Test
    public void fetchWhenObserved() {

        mNewsViewModel.setCategory("Magic");
        mNewsViewModel.getArticles().observeForever(mock(Observer.class));
        Mockito.verify(articlesRepository).getArticles("Magic");
    }

    @Test
    public void changeWhileObserved() {
        ArgumentCaptor<String> categoryCapture = ArgumentCaptor.forClass(String.class);
        mNewsViewModel.getArticles().observeForever(mock(Observer.class));

        mNewsViewModel.setCategory("Magic");
        mNewsViewModel.setCategory("MagicRepeat");

        Mockito.verify(articlesRepository, times(2)).getArticles(categoryCapture.capture());
        assertThat(categoryCapture.getAllValues(), is(Arrays.asList("Magic", "MagicRepeat")));
    }


    @Test
    public void retry() {
        mNewsViewModel.onRetry();
        Mockito.verifyNoMoreInteractions(articlesRepository);
        mNewsViewModel.setCategory("Magic");
        Mockito.verifyNoMoreInteractions(articlesRepository);
        Observer<Resource<List<Article>>> observer = mock(Observer.class);
        mNewsViewModel.getArticles().observeForever(observer);
        Mockito.verify(articlesRepository).getArticles("Magic");
        Mockito.reset(articlesRepository);
        mNewsViewModel.onRetry();
        Mockito.verify(articlesRepository).getArticles("Magic");
    }

}
