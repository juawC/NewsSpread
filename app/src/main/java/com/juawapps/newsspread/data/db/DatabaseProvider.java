package com.juawapps.newsspread.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Provides a database service.
 */

public class DatabaseProvider {

    private final AppDatabase mAppDatabase;
    private static DatabaseProvider sInstance;


    private DatabaseProvider(Context applicationContext) {
        mAppDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase.class, "shout_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    private static DatabaseProvider getInstance(Context applicationContext) {
        if (sInstance == null) {
            synchronized (DatabaseProvider.class) {
                if (sInstance == null) {
                    sInstance = new DatabaseProvider(applicationContext);
                }
            }
        }
        return sInstance;
    }

    public static AppDatabase getAppDatabase(Context applicationContext) {
        return getInstance(applicationContext).mAppDatabase;
    }
}
