package com.juawapps.newsspread.utils.biding;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.juawapps.newsspread.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Data Binding adapters specific to the app.
 */

@SuppressWarnings("WeakerAccess")
public class BindingAdapters {

    @BindingAdapter("recyclerAdapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("recyclerItemDecoration")
    public static void addItemDecoration(RecyclerView view, RecyclerView.ItemDecoration decoration) {
        view.addItemDecoration(decoration);
    }

    @BindingAdapter("setAgeFromDate")
    public static void setAgeFromDate(TextView view, Date date) {

        if (date == null) {
            view.setText(null);
            return;
        }

        long currentTimeMs = Calendar.getInstance().getTimeInMillis();
        long dateTimeMs = date.getTime();
        long age = currentTimeMs - dateTimeMs;

        int minutes = (int) (age / DateUtils.MINUTE_IN_MILLIS);
        int hours = (int) (age / DateUtils.HOUR_IN_MILLIS);
        int days = (int) (age / DateUtils.DAY_IN_MILLIS);

        Resources resources = view.getResources();
        if (days > 0) {
            view.setText(resources.getQuantityString(R.plurals.days, days, days));
        } else if (hours > 0) {
            view.setText(resources.getQuantityString(R.plurals.hours, hours, hours));
        } else if (minutes > 0){
            view.setText(resources.getQuantityString(R.plurals.minutes, minutes, minutes));
        } else {
            // In case of an ahead timezone timestamp
            view.setText(null);
        }
    }

    @BindingAdapter("setVisible")
    public static void setVisible(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}