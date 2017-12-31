package com.juawapps.newsspread.db;

import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.juawapps.newsspread.data.db.ArticleDao;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.util.AppDatabaseResource;
import com.juawapps.newsspread.util.LiveDataTestUtil;
import com.juawapps.newsspread.util.ObjectCreatorsUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Article Dao tests.
 * TODO: add tests to test date field.
 */

@RunWith(AndroidJUnit4.class)
public class ArticleDaoTest {

    @Rule
    public AppDatabaseResource mAppDatabaseResource = new AppDatabaseResource();

    @Test
    @MediumTest
    public void insertAndRead() throws Exception {
        String articleTitle = "Penguins use geisers to go to space!";

        ArticleDao articleInsert = mAppDatabaseResource.getDb().articleDao();

        // Insert article
        Article newArticle = ObjectCreatorsUtil.createArticle(articleTitle);
        articleInsert.insertArticles(new ArrayList<>(Collections.singletonList(newArticle)));

        // Read article
        List<Article> articleList = LiveDataTestUtil.getValue(articleInsert.loadAllArticles());

        assertThat(articleList.size(), is(1));
        Article articleRead = articleList.get(0);
        assertThat(articleRead, notNullValue());
        assertThat(articleRead.getTitle(), is(articleTitle));
        assertThat(articleRead.getSource(), notNullValue());
        assertThat(articleRead.getSource().getId(), is(ObjectCreatorsUtil.DEFAULT_SOURCE_ID));
        assertThat(articleRead.getSource().getName(), is(ObjectCreatorsUtil.DEFAULT_SOURCE_NAME));
        assertThat(articleRead.getAuthor(), is(ObjectCreatorsUtil.DEFAULT_ARTICLE_AUTHOR));
        assertThat(articleRead.getCategory(), is(ObjectCreatorsUtil.DEFAULT_ARTICLE_CATEGORY));
        assertThat(articleRead.getDescription(), is(ObjectCreatorsUtil.DEFAULT_ARTICLE_DESCRIPTION));
        assertThat(articleRead.getUrl(), is(ObjectCreatorsUtil.DEFAULT_ARTICLE_URL));
        assertThat(articleRead.getUrlToImage(), is(ObjectCreatorsUtil.DEFAULT_ARTICLE_URL_TO_IMAGE));
        assertThat(articleRead.getPublishedAt(), is(ObjectCreatorsUtil.DEFAULT_ARTICLE_PUBLISHED_AT));
    }

    @Test
    @MediumTest
    public void insertDuplicate() throws Exception {
        String articleTitle = "Vodka price drops!";

        ArticleDao articleInsert = mAppDatabaseResource.getDb().articleDao();

        // Insert article twice
        Article newArticle = ObjectCreatorsUtil.createArticle(articleTitle);
        articleInsert.insertArticles(new ArrayList<>(Collections.singletonList(newArticle)));
        articleInsert.insertArticles(new ArrayList<>(Collections.singletonList(newArticle)));

        // Read article
        List<Article> articleList = LiveDataTestUtil.getValue(articleInsert.loadAllArticles());

        assertThat(articleList.size(), is(1));
    }

    @Test
    @MediumTest
    public void loadCategory() throws Exception {
        String techCategory = "tech";
        String worldCategory = "world";

        String articleTechTitle = "Android is the best.";
        String articleWorldTitle = " ";

        ArticleDao articleInsert = mAppDatabaseResource.getDb().articleDao();

        // Insert articles
        Article techArticle = ObjectCreatorsUtil.createArticle(articleTechTitle, techCategory);
        Article worldArticle = ObjectCreatorsUtil.createArticle(articleWorldTitle, worldCategory);
        articleInsert.insertArticles(new ArrayList<>(Collections.singletonList(techArticle)));
        articleInsert.insertArticles(new ArrayList<>(Collections.singletonList(worldArticle)));

        // Read articles
        List<Article> articleTechList =
                LiveDataTestUtil.getValue(articleInsert.loadArticlesByCategory(techCategory));
        List<Article> articleWorldList =
                LiveDataTestUtil.getValue(articleInsert.loadArticlesByCategory(worldCategory));

        assertThat(articleTechList.size(), is(1));
        assertThat(articleWorldList.size(), is(1));

        assertThat(articleTechList.get(0).getTitle(), is(articleTechTitle));
        assertThat(articleWorldList.get(0).getTitle(), is(articleWorldTitle));
    }
}
