package com.juawapps.newsspread.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.juawapps.newsspread.ui.news.NewsViewModel;
import com.juawapps.newsspread.utils.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Provides the view model factory with view models maps and binds the ViewModelFactory type to
 * a ViewModelProvider.Factory type.
 */
@SuppressWarnings("unused")
@Module
abstract public class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    abstract ViewModel bindUserViewModel(NewsViewModel userViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
