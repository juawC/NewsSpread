package com.juawapps.newsspread.di;

import com.juawapps.newsspread.NewSpreadApplication;

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
        AppModuleBase.class,
        AppModuleApplication.class,
        AppModuleImageLoader.class,
        AppModuleRepository.class,
        AppModuleCustomTabHelper.class
      })
public interface AppProductionComponent extends AndroidInjector<NewSpreadApplication> {
      @Component.Builder
      abstract class Builder extends AndroidInjector.Builder<NewSpreadApplication> {}
}