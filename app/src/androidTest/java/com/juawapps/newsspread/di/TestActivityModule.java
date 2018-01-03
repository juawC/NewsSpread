package com.juawapps.newsspread.di;

import com.juawapps.newsspread.di.news.NewsFragmentBuildersModule;
import com.juawapps.newsspread.test.SingleFragmentActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@SuppressWarnings("unused")
@Module
abstract class TestActivityModule {

    @ContributesAndroidInjector(modules = {NewsFragmentBuildersModule.class})
    abstract SingleFragmentActivity bindMainActivity();
}