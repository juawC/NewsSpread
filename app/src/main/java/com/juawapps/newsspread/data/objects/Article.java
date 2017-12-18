package com.juawapps.newsspread.data.objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by joaocevada on 10/12/2017.
 */
@SuppressWarnings("NullableProblems")
@Entity(tableName = "articles", primaryKeys = {"author", "title"})
public class Article {

    /**
     * source : {"id":"bbc-news","name":"BBC News"}
     * author : BBC News
     * title : Iraq declares war with IS is over
     * description : PM Haider al-Abadi says Iraqi troops are in now complete control of the country.
     * url : http://www.bbc.co.uk/news/world-middle-east-42291985
     * urlToImage : https://ichef-1.bbci.co.uk/news/1024/cpsprodpb/11EED/production/_99135437_043072258-1.jpg
     * publishedAt : 2017-12-09T11:34:27Z
     */

    @Embedded
    private ArticleSource source;
    @NonNull private String author;
    @NonNull private String title;
    private String description;
    private String url;
    @ColumnInfo(name = "url_to_image")
    private String urlToImage;
    @ColumnInfo(name = "published_at")
    private Date publishedAt;
    private String category;

    public ArticleSource getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setSource(ArticleSource source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
