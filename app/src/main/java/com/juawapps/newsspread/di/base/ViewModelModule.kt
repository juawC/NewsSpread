package com.juawapps.newsspread.di.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.juawapps.newsspread.ui.news.NewsViewModel
import com.juawapps.newsspread.utils.viewmodel.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides the view model factory with view models maps and binds the ViewModelFactory type to
 * a ViewModelProvider.Factory type.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindUserViewModel(userViewModel: NewsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
