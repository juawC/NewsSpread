package com.juawapps.newsspread.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Util to get a live data value with a timeout of 2 seconds.
 */

object LiveDataTestUtil {
    fun <T> getValue(liveData: LiveData<T>): T {
        val latch = CountDownLatch(1)
        var data : T? = null
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data!!
    }
}
