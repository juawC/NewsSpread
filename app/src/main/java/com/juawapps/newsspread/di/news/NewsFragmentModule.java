package com.juawapps.newsspread.di.news;

import android.support.v4.app.Fragment;

import com.juawapps.newsspread.ui.news.NewsFragment;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NewsFragmentModule {
    @Binds
    abstract Fragment bindFragment(NewsFragment fragment);
}
