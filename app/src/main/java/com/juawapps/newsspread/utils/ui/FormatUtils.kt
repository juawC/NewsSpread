package com.juawapps.newsspread.utils.ui

import android.content.Context
import android.text.format.DateUtils

import com.juawapps.newsspread.R

import java.util.Calendar
import java.util.Date

/**
 * Text format utils
 */

object FormatUtils {

    /**
     * Formats a Date object to a age String
     * @param context context
     * @param date date
     * @return age string
     */
    fun dateToAge(context: Context, date: Date?): String? {
        if (date == null) {
            return null
        }

        val currentTimeMs = Calendar.getInstance().timeInMillis
        val dateTimeMs = date.time
        val age = currentTimeMs - dateTimeMs

        // In case of an ahead timezone timestamp
        if (age < 0) {
            return null
        }

        val minutes = (age / DateUtils.MINUTE_IN_MILLIS).toInt()
        val hours = (age / DateUtils.HOUR_IN_MILLIS).toInt()
        val days = (age / DateUtils.DAY_IN_MILLIS).toInt()

        return context.resources.run {
            when {
                days > 0 -> getQuantityString(R.plurals.days, days, days)
                hours > 0 -> getQuantityString(R.plurals.hours, hours, hours)
                else -> getQuantityString(R.plurals.minutes, minutes, minutes)
            }
        }
    }
}
