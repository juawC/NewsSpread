package com.juawapps.newsspread.util

import com.juawapps.newsspread.data.objects.Article
import com.juawapps.newsspread.data.objects.ArticleSource
import com.juawapps.newsspread.data.objects.Source

import java.util.Date

/**
 * Util class to create objects.
 */

object ObjectCreatorsUtilInst {

    const val DEFAULT_ARTICLE_TITLE = "Pointless title."
    const val  DEFAULT_ARTICLE_AUTHOR = "NotPutin"
    const val  DEFAULT_ARTICLE_DESCRIPTION = "news stuff"
    const val  DEFAULT_ARTICLE_URL = "https://www.google.com"
    const val  DEFAULT_ARTICLE_URL_TO_IMAGE = "https://www.wikipedia.org/portal/wikipedia.org/assets/img/Wikipedia-logo-v2@2x.png"
    val  DEFAULT_ARTICLE_PUBLISHED_AT = Date(0)
    const val  DEFAULT_ARTICLE_CATEGORY = "news"

    const val  DEFAULT_SOURCE_ID = "randomSourceID"
    const val  DEFAULT_SOURCE_NAME = "fakeNewsInc"
    const val  DEFAULT_SOURCE_DESCRIPTION = "serious description..."
    const val  DEFAULT_SOURCE_URL = "https://www.google.com"
    const val  DEFAULT_SOURCE_CATEGORY = "news"
    const val  DEFAULT_SOURCE_LANGUAGE = "en"
    const val  DEFAULT_SOURCE_COUNTRY = "global"


    fun createSource(sourceName: String): Source {

        return Source(DEFAULT_SOURCE_ID, sourceName, DEFAULT_SOURCE_DESCRIPTION,
                DEFAULT_SOURCE_URL, DEFAULT_SOURCE_CATEGORY, DEFAULT_SOURCE_LANGUAGE,
                DEFAULT_SOURCE_COUNTRY)
    }

    fun createArticleSource(sourceName: String): ArticleSource {

        return ArticleSource(DEFAULT_SOURCE_ID, sourceName)
    }

    fun createArticle(): Article {
        return Article(createArticleSource(DEFAULT_SOURCE_NAME), DEFAULT_ARTICLE_AUTHOR,
                DEFAULT_ARTICLE_TITLE, DEFAULT_ARTICLE_DESCRIPTION, DEFAULT_ARTICLE_URL,
                DEFAULT_ARTICLE_URL_TO_IMAGE, DEFAULT_ARTICLE_PUBLISHED_AT, DEFAULT_ARTICLE_CATEGORY)
    }

    fun createArticle(title: String): Article {
        return Article(createArticleSource(DEFAULT_SOURCE_NAME), DEFAULT_ARTICLE_AUTHOR, title,
                DEFAULT_ARTICLE_DESCRIPTION, DEFAULT_ARTICLE_URL, DEFAULT_ARTICLE_URL_TO_IMAGE,
                DEFAULT_ARTICLE_PUBLISHED_AT, DEFAULT_ARTICLE_CATEGORY)
    }

    fun createArticle(title: String, category: String): Article {
        return Article(createArticleSource(DEFAULT_SOURCE_NAME), DEFAULT_ARTICLE_AUTHOR, title,
                DEFAULT_ARTICLE_DESCRIPTION, DEFAULT_ARTICLE_URL, DEFAULT_ARTICLE_URL_TO_IMAGE,
                DEFAULT_ARTICLE_PUBLISHED_AT, category)
    }
}
