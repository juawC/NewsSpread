package com.juawapps.newsspread.utils.biding;

import android.databinding.DataBindingComponent;
import android.support.v4.app.Fragment;

import com.juawapps.newsspread.image.ImageLoaderWrapper;

import javax.inject.Inject;

/**
 * Component to set fragment binding adapters.
 */

public class FragmentDataBindingComponent implements DataBindingComponent {
    private final FragmentBindingAdapters adapter;

    @Inject
    public FragmentDataBindingComponent(Fragment fragment, ImageLoaderWrapper imageLoaderWrapper) {
        this.adapter = new FragmentBindingAdapters(fragment, imageLoaderWrapper);
    }

    @Override
    public FragmentBindingAdapters getFragmentBindingAdapters() {
        return adapter;
    }
}