package com.juawapps.newsspread.di

import com.juawapps.newsspread.di.news.NewsFragmentBuildersModule
import com.juawapps.newsspread.test.SingleFragmentActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class TestActivityModule {

    @ContributesAndroidInjector(modules = [NewsFragmentBuildersModule::class])
    internal abstract fun bindMainActivity(): SingleFragmentActivity
}