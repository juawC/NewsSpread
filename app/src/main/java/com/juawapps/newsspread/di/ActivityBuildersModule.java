package com.juawapps.newsspread.di;

import com.juawapps.newsspread.di.news.NewsActivityModule;
import com.juawapps.newsspread.di.news.NewsFragmentBuildersModule;
import com.juawapps.newsspread.ui.news.NewsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * App activity builders
 */

@SuppressWarnings("unused")
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {NewsActivityModule.class, NewsFragmentBuildersModule.class})
    abstract NewsActivity bindMainActivity();
}
