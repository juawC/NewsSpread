package com.juawapps.newsspread.utils.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;

import com.juawapps.newsspread.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Text format utils
 */

public class FormatUtils {

    /**
     * Formats a Date object to a age String
     * @param context context
     * @param date date
     * @return age string
     */
    public static String dateToAge(@NonNull Context context, @Nullable Date date) {
        if (date == null) {
            return null;
        }

        long currentTimeMs = Calendar.getInstance().getTimeInMillis();
        long dateTimeMs = date.getTime();
        long age = currentTimeMs - dateTimeMs;

        int minutes = (int) (age / DateUtils.MINUTE_IN_MILLIS);
        int hours = (int) (age / DateUtils.HOUR_IN_MILLIS);
        int days = (int) (age / DateUtils.DAY_IN_MILLIS);

        Resources resources = context.getResources();
        if (days > 0) {
            return resources.getQuantityString(R.plurals.days, days, days);
        } else if (hours > 0) {
            return resources.getQuantityString(R.plurals.hours, hours, hours);
        } else if (minutes > 0){
            return resources.getQuantityString(R.plurals.minutes, minutes, minutes);
        } else {
            // In case of an ahead timezone timestamp
            return null;
        }
    }
}
