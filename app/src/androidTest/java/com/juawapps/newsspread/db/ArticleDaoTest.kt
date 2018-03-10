package com.juawapps.newsspread.db

import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import com.juawapps.newsspread.util.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.util.ArrayList

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat

/**
 * Article Dao tests.
 * TODO: add tests to test date field.
 */

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    @JvmField
    @Rule
    var mAppDatabaseResource = AppDatabaseResource()

    @Test
    @MediumTest
    @Throws(Exception::class)
    fun insertAndRead() {
        val articleTitle = "Penguins use geisers to go to space!"

        val articleInsert = mAppDatabaseResource.db.articleDao()

        // Insert article
        val newArticle = ObjectCreatorsUtil.createArticle(articleTitle)
        articleInsert.insertArticles(ArrayList(listOf(newArticle)))

        // Read article
        val articleList = LiveDataTestUtil.getValue(articleInsert.loadAllArticles())

        assertThat(articleList.size, `is`(1))
        val articleRead = articleList[0]
        assertThat(articleRead, notNullValue())
        assertThat(articleRead.title, `is`(articleTitle))
        assertThat(articleRead.source, notNullValue())
        assertThat(articleRead.source?.id, `is`(ObjectCreatorsUtil.DEFAULT_SOURCE_ID))
        assertThat(articleRead.source?.name, `is`(ObjectCreatorsUtil.DEFAULT_SOURCE_NAME))
        assertThat(articleRead.author, `is`(ObjectCreatorsUtil.DEFAULT_ARTICLE_AUTHOR))
        assertThat(articleRead.category, `is`(ObjectCreatorsUtil.DEFAULT_ARTICLE_CATEGORY))
        assertThat(articleRead.description, `is`(ObjectCreatorsUtil.DEFAULT_ARTICLE_DESCRIPTION))
        assertThat(articleRead.url, `is`(ObjectCreatorsUtil.DEFAULT_ARTICLE_URL))
        assertThat(articleRead.urlToImage, `is`(ObjectCreatorsUtil.DEFAULT_ARTICLE_URL_TO_IMAGE))
        assertThat(articleRead.publishedAt, `is`(ObjectCreatorsUtil.DEFAULT_ARTICLE_PUBLISHED_AT))
    }

    @Test
    @MediumTest
    @Throws(Exception::class)
    fun insertDuplicate() {
        val articleTitle = "Vodka price drops!"

        val articleInsert = mAppDatabaseResource.db.articleDao()

        // Insert article twice
        val newArticle = ObjectCreatorsUtil.createArticle(articleTitle)
        articleInsert.insertArticles(listOf(newArticle))
        articleInsert.insertArticles(listOf(newArticle))

        // Read article
        val articleList = LiveDataTestUtil.getValue(articleInsert.loadAllArticles())

        assertThat(articleList.size, `is`(1))
    }

    @Test
    @MediumTest
    @Throws(Exception::class)
    fun loadCategory() {
        val techCategory = "tech"
        val worldCategory = "world"

        val articleTechTitle = "Android is the best."
        val articleWorldTitle = " "

        val articleInsert = mAppDatabaseResource.db.articleDao()

        // Insert articles
        val techArticle = ObjectCreatorsUtil.createArticle(articleTechTitle, techCategory)
        val worldArticle = ObjectCreatorsUtil.createArticle(articleWorldTitle, worldCategory)
        articleInsert.insertArticles(ArrayList(listOf(techArticle)))
        articleInsert.insertArticles(ArrayList(listOf(worldArticle)))

        // Read articles
        val articleTechList = LiveDataTestUtil.getValue(articleInsert.loadArticlesByCategory(techCategory))
        val articleWorldList = LiveDataTestUtil.getValue(articleInsert.loadArticlesByCategory(worldCategory))

        assertThat(articleTechList.size, `is`(1))
        assertThat(articleWorldList.size, `is`(1))

        assertThat(articleTechList[0].title, `is`(articleTechTitle))
        assertThat(articleWorldList[0].title, `is`(articleWorldTitle))
    }
}
