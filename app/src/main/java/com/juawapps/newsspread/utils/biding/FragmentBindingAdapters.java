package com.juawapps.newsspread.utils.biding;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.juawapps.newsspread.image.ImageLoaderWrapper;

import timber.log.Timber;


/**
 * Fragment view binders.
 */
@SuppressWarnings("WeakerAccess")
public class FragmentBindingAdapters {
    private final Fragment fragment;
    final ImageLoaderWrapper imageLoaderWrapper;

    public FragmentBindingAdapters(Fragment fragment, ImageLoaderWrapper imageLoaderWrapper) {
        this.fragment = fragment;
        this.imageLoaderWrapper = imageLoaderWrapper;
    }
    @BindingAdapter("imageUrl")
    public void bindImage(ImageView imageView, String url) {
        Timber.d("Loading image with glide: %s", url);
        imageLoaderWrapper.fetchImageToView(fragment, url, imageView);
    }
}