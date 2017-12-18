package com.juawapps.newsspread.utils.biding;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.juawapps.newsspread.glide.GlideApp;

import timber.log.Timber;


/**
 * Created by joaocevada on 16/12/2017.
 */

public class FragmentBindingAdapters {
    private final Fragment fragment;

    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }
    @BindingAdapter("imageUrl")
    public void bindImage(ImageView imageView, String url) {
        Timber.d("Loading image with glide: %s", url);
        GlideApp.with(fragment).load(url).centerCrop().into(imageView);
    }
}