package com.juawapps.newsspread.util;

import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.data.objects.ArticleSource;
import com.juawapps.newsspread.data.objects.Source;

import java.util.Date;

/**
 * Util class to create objects.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class ObjectCreatorsUtil {

        public static final String DEFAULT_ARTICLE_AUTHOR = "NotPutin";
        public static final String DEFAULT_ARTICLE_DESCRIPTION = "news stuff";
        public static final String DEFAULT_ARTICLE_URL = "https://www.google.com";
        public static final String DEFAULT_ARTICLE_URL_TO_IMAGE = "https://www.wikipedia.org/portal/wikipedia.org/assets/img/Wikipedia-logo-v2@2x.png";
        public static final Date DEFAULT_ARTICLE_PUBLISHED_AT = new Date(0);
        public static final String DEFAULT_ARTICLE_CATEGORY = "news";

        public static final String DEFAULT_SOURCE_ID = "randomSourceID";
        public static final String DEFAULT_SOURCE_NAME = "fakeNewsInc";
        public static final String DEFAULT_SOURCE_DESCRIPTION = "serious description...";
        public static final String DEFAULT_SOURCE_URL = "https://www.google.com";
        public static final String DEFAULT_SOURCE_CATEGORY = "news";
        public static final String DEFAULT_SOURCE_LANGUAGE = "en";
        public static final String DEFAULT_SOURCE_COUNTRY = "global";


    public static Source createSource(String sourceName) {

        return new Source(DEFAULT_SOURCE_ID, sourceName, DEFAULT_SOURCE_DESCRIPTION,
                DEFAULT_SOURCE_URL, DEFAULT_SOURCE_CATEGORY, DEFAULT_SOURCE_LANGUAGE,
                DEFAULT_SOURCE_COUNTRY);
    }

    public static ArticleSource createArticleSource(String sourceName) {

        return new ArticleSource(DEFAULT_SOURCE_ID, sourceName);
    }

    public static Article createArticle(String title) {
        return new Article(createArticleSource(DEFAULT_SOURCE_NAME), DEFAULT_ARTICLE_AUTHOR, title,
                DEFAULT_ARTICLE_DESCRIPTION, DEFAULT_ARTICLE_URL, DEFAULT_ARTICLE_URL_TO_IMAGE,
                DEFAULT_ARTICLE_PUBLISHED_AT, DEFAULT_ARTICLE_CATEGORY);
    }

    public static Article createArticle(String title, String category) {
        return new Article(createArticleSource(DEFAULT_SOURCE_NAME), DEFAULT_ARTICLE_AUTHOR, title,
                DEFAULT_ARTICLE_DESCRIPTION, DEFAULT_ARTICLE_URL, DEFAULT_ARTICLE_URL_TO_IMAGE,
                DEFAULT_ARTICLE_PUBLISHED_AT, category);
    }
}
