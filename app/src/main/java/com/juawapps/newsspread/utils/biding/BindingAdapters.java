package com.juawapps.newsspread.utils.biding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.juawapps.newsspread.utils.ui.FormatUtils;

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

        view.setText(FormatUtils.dateToAge(view.getContext(), date));
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