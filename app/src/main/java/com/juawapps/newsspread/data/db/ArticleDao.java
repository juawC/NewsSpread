package com.juawapps.newsspread.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.juawapps.newsspread.data.objects.Article;

import java.util.List;

/**
 * Created by joaocevada on 13/12/2017.
 */
@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertArticles(List<Article> articles);

    @Delete
    void deleteArticles(Article... articles);

    @Query("SELECT * FROM articles")
    LiveData<List<Article>> loadAllArticles();

    @Query("SELECT * FROM articles WHERE category LIKE :category ORDER BY published_at DESC")
    LiveData<List<Article>> loadArticlesByCategory(String category);
}
