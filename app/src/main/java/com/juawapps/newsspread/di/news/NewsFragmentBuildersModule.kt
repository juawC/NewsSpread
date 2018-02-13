package com.juawapps.newsspread.di.news

import com.juawapps.newsspread.ui.news.NewsFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * News fragment builders.
 */

@Module
abstract class NewsFragmentBuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(NewsFragmentModule::class))
    internal abstract fun contributeNewsFragment(): NewsFragment
}
