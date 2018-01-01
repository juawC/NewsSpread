package com.juawapps.newsspread.image;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * This is a wrapper class to use with an image loading and caching library.
 * <p>
 * This class was created in order to enable glide to be easily mocked and in the future to easily
 * change the image loading lib.
 */

public class ImageLoaderWrapper {

    public ImageLoaderWrapper() {}

    public void fetchImageToView(Fragment fragment, String url,  ImageView imageView) {
        GlideApp.with(fragment).load(url).centerCrop().into(imageView);
    }
}
