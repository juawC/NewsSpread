package com.juawapps.newsspread.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Provides a database service.
 */

public class DatabaseProvider {

    public static AppDatabase getDatabase(Context applicationContext) {
        return Room.databaseBuilder(applicationContext,
                AppDatabase.class, "shout_database")
                .fallbackToDestructiveMigration()
                .build();
    }
}
