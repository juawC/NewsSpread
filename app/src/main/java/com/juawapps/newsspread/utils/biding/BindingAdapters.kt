package com.juawapps.newsspread.utils.biding

import android.databinding.BindingAdapter
import android.databinding.adapters.ListenerUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import com.juawapps.newsspread.R
import com.juawapps.newsspread.data.Resource
import com.juawapps.newsspread.ui.common.DataBoundRecyclerAdapter
import com.juawapps.newsspread.ui.common.ItemDecorationFactory
import com.juawapps.newsspread.utils.ui.FormatUtils

import java.util.Date

/**
 * Data Binding adapters specific to the app.
 */


@BindingAdapter("recyclerAdapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("recyclerResource")
fun <T> setResource(recyclerView: RecyclerView, resource: T) {

    val adapter = recyclerView.adapter
    (adapter as? DataBoundRecyclerAdapter<*, *>)?.setDataResource(resource as Resource<*>?)
}

@BindingAdapter("recyclerItemDecoration")
fun addItemDecoration(view: RecyclerView, decorationType: Int,
                      decorationTypeOld: Int) {

    if (decorationType != decorationTypeOld) {
        val decoration = ItemDecorationFactory.create(decorationType, view)
        val decorationOld = ListenerUtil.trackListener<RecyclerView.ItemDecoration>(view,
                decoration, R.id.decoration_type)
        if (decorationOld != null) {
            view.removeItemDecoration(decorationOld)
        }
        if (decoration != null) view.addItemDecoration(decoration)
    }
}

@BindingAdapter("setAgeFromDate")
fun setAgeFromDate(view: TextView, date: Date) {

    view.text = FormatUtils.dateToAge(view.context, date)
}

@BindingAdapter("setVisible")
fun setVisible(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}