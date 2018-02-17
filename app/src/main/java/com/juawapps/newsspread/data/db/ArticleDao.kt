package com.juawapps.newsspread.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.juawapps.newsspread.data.objects.Article


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticles(articles: List<Article>)

    @Query("DELETE FROM articles WHERE published_at < :oldestDate")
    fun deleteOlderArticles(oldestDate: Long)

    @Query("SELECT * FROM articles")
    fun loadAllArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM articles WHERE category LIKE :category ORDER BY published_at DESC")
    fun loadArticlesByCategory(category: String): LiveData<List<Article>>
}
