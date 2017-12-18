package com.juawapps.newsspread.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.juawapps.newsspread.data.objects.Article;

/**
 * Created by joaocevada on 13/12/2017.
 */
@Database(entities = {Article.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
