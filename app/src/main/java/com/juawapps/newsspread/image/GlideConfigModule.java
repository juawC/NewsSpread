package com.juawapps.newsspread.image;


import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Glide module with glide configurations.
 * <p>
 * Todo: save to disc cache
 */
@SuppressWarnings("WeakerAccess")
@GlideModule
public final class GlideConfigModule extends AppGlideModule {

    private static final int CACHE_MB = 100;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, 1024 * 1024 * CACHE_MB));
    }
}
