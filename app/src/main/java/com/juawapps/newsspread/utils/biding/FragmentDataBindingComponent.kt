package com.juawapps.newsspread.utils.biding


import android.databinding.DataBindingComponent
import android.support.v4.app.Fragment

import com.juawapps.newsspread.image.ImageLoaderWrapper

import javax.inject.Inject

/**
 * Component to set fragment binding adapters.
 */

class FragmentDataBindingComponent @Inject
constructor(fragment: Fragment, imageLoaderWrapper: ImageLoaderWrapper) : DataBindingComponent {


    private val fragmentBindingAdapters: FragmentBindingAdapters = FragmentBindingAdapters(fragment, imageLoaderWrapper)


    override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
        return fragmentBindingAdapters
    }

}