package com.juawapps.newsspread.di.news

import android.support.v4.app.Fragment

import com.juawapps.newsspread.ui.news.NewsFragment

import dagger.Binds
import dagger.Module

@Module
abstract class NewsFragmentModule {
    @Binds
    internal abstract fun bindFragment(fragment: NewsFragment): Fragment
}
