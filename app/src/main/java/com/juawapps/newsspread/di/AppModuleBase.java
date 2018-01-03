package com.juawapps.newsspread.di;

import com.juawapps.newsspread.di.base.AppModuleApi;
import com.juawapps.newsspread.di.base.AppModuleDatabase;
import com.juawapps.newsspread.di.base.ViewModelModule;

import dagger.Module;

/**
 * Initialization of objects used throughout the app.
 */

@Module(includes = {ViewModelModule.class, AppModuleApi.class, AppModuleDatabase.class})
public class AppModuleBase {

}
