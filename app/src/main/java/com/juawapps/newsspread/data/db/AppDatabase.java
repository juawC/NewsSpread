package com.juawapps.newsspread.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.juawapps.newsspread.data.objects.Article;

/**
 * App database.
 */
@Database(entities = {Article.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
