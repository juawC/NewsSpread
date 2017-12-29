package com.juawapps.newsspread.di.news;

import com.juawapps.newsspread.ui.news.NewsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * News fragment builders.
 */

@SuppressWarnings("unused")
@Module
public abstract class NewsFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract NewsFragment contributeNewsFragment();
}
