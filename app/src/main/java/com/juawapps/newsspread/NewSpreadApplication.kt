package com.juawapps.newsspread


import com.juawapps.newsspread.di.DaggerAppProductionComponent

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class NewSpreadApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppProductionComponent.builder().create(this)
    }
}
