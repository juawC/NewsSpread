package com.juawapps.newsspread.di


import com.juawapps.newsspread.TestApp

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    TestActivityModule::class,
    AppModuleBase::class,
    AppModuleApplicationTest::class,
    AppModuleImageLoaderMock::class,
    AppModuleRepositoryMock::class,
    AppModuleCustomTabHelperMock::class])
interface AppTestComponent : AndroidInjector<TestApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApp>()
}

