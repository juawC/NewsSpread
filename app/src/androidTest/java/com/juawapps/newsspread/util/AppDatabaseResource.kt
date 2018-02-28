package com.juawapps.newsspread.util

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry

import com.juawapps.newsspread.data.db.AppDatabase

import org.junit.rules.ExternalResource

/**
 * Rule for instantiating a room DB.
 */

class AppDatabaseResource : ExternalResource() {
    
    lateinit var db: AppDatabase

    override fun before() {

        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase::class.java).build()
    }

    override fun after() {
        db.close()
    }
}
