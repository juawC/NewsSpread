package com.juawapps.newsspread.util;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.juawapps.newsspread.data.db.AppDatabase;

import org.junit.rules.ExternalResource;

/**
 * Rule for instantiating a room DB.
 */

public class AppDatabaseResource extends ExternalResource {
    private AppDatabase mDb;

    @Override
    protected void before() throws Throwable {

        mDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class).build();
    }

    @Override
    protected void after() {
        mDb.close();
    }

    public AppDatabase getDb() {
        return mDb;
    }
}
