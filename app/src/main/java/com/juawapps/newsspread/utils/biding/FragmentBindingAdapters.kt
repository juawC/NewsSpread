package com.juawapps.newsspread.utils.biding

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.widget.ImageView

import com.juawapps.newsspread.image.ImageLoaderWrapper

import timber.log.Timber


/**
 * Fragment view binders.
 */
class FragmentBindingAdapters(private val fragment: Fragment, private val imageLoaderWrapper: ImageLoaderWrapper) {
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String) {
        Timber.d("Loading image with glide: %s", url)
        imageLoaderWrapper.fetchImageToView(fragment, url, imageView)
    }
}