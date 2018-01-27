package com.juawapps.newsspread.ui.common;

import android.support.annotation.IntDef;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.annotation.Retention;

import timber.log.Timber;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class ItemDecorationFactory {


    @Retention(SOURCE)
    @IntDef({DIVIDER_LIST_ITEM})
    public @interface DecorationType {}
    public static final int DIVIDER_LIST_ITEM = 1;


    public static RecyclerView.ItemDecoration create(@DecorationType int type,
                                                     RecyclerView recyclerView) {
        switch (type) {
            case DIVIDER_LIST_ITEM:
            default:
                if (!(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
                    Timber.d("Added decoration without a LinearLayoutManager");
                    return null;
                }
                int orientation = ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation();
                return new DividerItemDecoration(recyclerView.getContext(), orientation);
        }
    }
}
