package com.juawapps.newsspread.di;

import com.juawapps.newsspread.NewSpreadApplication;
import com.juawapps.newsspread.di.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Application DI main component
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class,
        AppModuleApi.class,
        AppModuleDatabase.class,
        AppModuleImageLoader.class,
        AppModuleRepository.class
      })
public interface AppComponent extends AndroidInjector<NewSpreadApplication> {
      @Component.Builder
      abstract class Builder extends AndroidInjector.Builder<NewSpreadApplication> {}
}