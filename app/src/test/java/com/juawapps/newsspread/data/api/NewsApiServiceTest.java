package com.juawapps.newsspread.data.api;


import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juawapps.newsspread.data.api.adapters.LiveDataCallAdapterFactory;
import com.juawapps.newsspread.data.api.adapters.UnwrapConverterFactory;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.util.LiveDataTestUtil;
import com.juawapps.newsspread.util.MockWebServerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.juawapps.newsspread.data.api.ApiConfigs.DATE_FORMAT;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NewsApiServiceTest {

    @Rule
    public InstantTaskExecutorRule mInstantExecutorRule = new InstantTaskExecutorRule();

    @Rule
    public MockWebServerRule mMockWebServer = new MockWebServerRule();

    NewsapiService mService;

    @Before
    public void createService() throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();

        mService = new Retrofit.Builder()
                .baseUrl(mMockWebServer.getUrl())
                .addConverterFactory(new UnwrapConverterFactory(GsonConverterFactory.create(gson)))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(NewsapiService.class);
    }


    @Test
    public void getTopHeadLines() throws IOException, InterruptedException, ParseException {

        String category = "tech";
        mMockWebServer.enqueueResponse("top-headlines-tech.json");

        List<Article> headLines = LiveDataTestUtil.getValue(mService.topHeadLines(category)).body;

        assertThat(headLines.size(), is(4));

        Article article = headLines.get(0);
        assertThat(article.getTitle(), is("The cryptocurrency bubble is strangling innovation"));
        assertThat(article.getDescription(), is("Sure, fine, maybe it's a bubble. OK it's definitely a bubble, but that's a good thing, a bubble brings attention and investment in infrastructure, which.."));
        assertThat(article.getUrlToImage(), is("https://tctechcrunch2011.files.wordpress.com/2018/01/sorong-python.jpg"));
        assertThat(article.getUrl(), is("https://techcrunch.com/2018/01/07/the-cryptocurrency-bubble-is-strangling-innovation/"));
        assertThat(article.getAuthor(), is("Jon Evans"));
        assertThat(article.getPublishedAt(), is(getDateFromString("2018-01-07T14:00:16Z")));
        assertThat(article.getSource().getId(), is("techcrunch"));
        assertThat(article.getSource().getName(), is("TechCrunch"));
    }

    @Test
    public void getTopHeadLinesError() throws IOException, InterruptedException, ParseException {

        String category = "tech";
        mMockWebServer.enqueueResponse("top-headlines-error.json");
        ApiResponseWrapper<List<Article>> response = LiveDataTestUtil.getValue(mService.topHeadLines(category));
        List<Article> responseBody = response.body;
        int responseCode = response.code;

        assertNull(responseBody);
        assertThat(responseCode, is(500));
    }



    private Date getDateFromString(String dateStr) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date = formatter.parse(dateStr);
        return date;
    }
}
