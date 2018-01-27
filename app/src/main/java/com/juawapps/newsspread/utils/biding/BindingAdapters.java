package com.juawapps.newsspread.utils.biding;

import android.databinding.BindingAdapter;
import android.databinding.adapters.ListenerUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.ui.common.DataBoundRecyclerAdapter;
import com.juawapps.newsspread.ui.common.ItemDecorationFactory;
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

    @BindingAdapter(value = "recyclerResource")
    public static void setResource(RecyclerView recyclerView, Resource resource){

        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter instanceof DataBoundRecyclerAdapter){
            ((DataBoundRecyclerAdapter)adapter).setDataResource(resource);
        }
    }

    @BindingAdapter("recyclerItemDecoration")
    public static void addItemDecoration(RecyclerView view, int decorationType,
                                         int decorationTypeOld ) {

        if (decorationType != decorationTypeOld) {
            RecyclerView.ItemDecoration decoration = ItemDecorationFactory.create(decorationType, view);
            RecyclerView.ItemDecoration decorationOld = ListenerUtil.trackListener(view,
                    decoration, R.id.decoration_type);
            if (decorationOld != null) {
                view.removeItemDecoration(decorationOld);
            }
            if (decoration != null) view.addItemDecoration(decoration);
        }
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