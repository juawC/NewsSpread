package com.juawapps.newsspread.data.db

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Provides a database service.
 */

object DatabaseProvider {

    fun getDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "shout_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}
