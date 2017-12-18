package com.juawapps.newsspread.data.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by joaocevada on 17/12/2017.
 */

class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}