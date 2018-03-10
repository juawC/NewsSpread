package com.juawapps.newsspread.data

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

import com.juawapps.newsspread.data.api.ApiResponseWrapper
import com.juawapps.newsspread.utils.AppExecutors
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import java.util.Objects
import java.util.concurrent.Executor

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThat

@RunWith(JUnit4::class)
class NetworkBoundResourceTest {

    @Rule
    @JvmField
    var mInstantExecutorRule = InstantTaskExecutorRule()

    private val mInstantExecutor = Executor { it.run() }

    private lateinit var mNetworkBoundResource: NetworkBoundResource<TestValue, TestValue>

    private lateinit var mSaveCallResult: (TestValue) -> Unit
    private lateinit var mShouldFetch: (TestValue?) -> Boolean
    private lateinit var mDbData: MutableLiveData<TestValue>
    private lateinit var mApiData: MutableLiveData<ApiResponseWrapper<TestValue>>

    private lateinit var mObserver: Observer<Resource<TestValue>>

    @Before
    fun init() {
        mObserver = mock()
        mApiData = MutableLiveData()
        mDbData = MutableLiveData()

        mNetworkBoundResource = object : NetworkBoundResource<TestValue, TestValue>(
                AppExecutors(mInstantExecutor, mInstantExecutor, mInstantExecutor)) {

            override fun saveCallResult(item: TestValue) {
                mSaveCallResult(item)
            }

            override fun shouldFetch(data: TestValue?): Boolean {
                return mShouldFetch(data)
            }

            override fun loadFromDb(): LiveData<TestValue> {
                return mDbData
            }

            override fun createCall(): LiveData<ApiResponseWrapper<TestValue>> {
                return mApiData
            }
        }

    }

    @Test
    fun getFromNetwork() {
        val netWorkResult = TestValue(1)

        mShouldFetch = { Objects.isNull(it) }

        mSaveCallResult = { testValue ->
            mDbData.setValue(testValue)
        }


        mNetworkBoundResource.asLiveData().observeForever(mObserver)
        verify(mObserver).onChanged(Resource.loading<TestValue>(null))
        reset(mObserver)
        mDbData.value  = null
        mApiData.value = ApiResponseWrapper(Response.success(netWorkResult))
        verify(mObserver).onChanged(Resource.success(netWorkResult))
        verifyNoMoreInteractions(mObserver)
    }

    @Test
    fun getFromNetworkFail() {

        var isSaved = false

        val body = ResponseBody.create(MediaType.parse("application/json"), "error")

        mShouldFetch = { Objects.isNull(it) }

        mSaveCallResult = {isSaved = true}

        mNetworkBoundResource.asLiveData().observeForever(mObserver)

        verify(mObserver).onChanged(Resource.loading<TestValue>(null))
        reset(mObserver)

        mDbData.value = null
        mApiData.value = ApiResponseWrapper(Response.error(500, body))
        assertFalse(isSaved)
        verify(mObserver).onChanged(Resource.error<TestValue>("error", null))
        verifyNoMoreInteractions(mObserver)
    }

    @Test
    fun getFromDbWithoutNetwork() {

        var isSaved = false

        val dbTestValue = TestValue(1)
        val dbTestValueNew = TestValue(1)

        mShouldFetch = { Objects.isNull(it) }

        mSaveCallResult = { isSaved = true}

        mNetworkBoundResource.asLiveData().observeForever(mObserver)

        verify(mObserver).onChanged(Resource.loading<TestValue>(null))
        reset(mObserver)

        mDbData.value = dbTestValue
        assertFalse(isSaved)
        verify(mObserver).onChanged(Resource.success(dbTestValue))
        reset(mObserver)

        mDbData.value = dbTestValueNew
        verify(mObserver).onChanged(Resource.success(dbTestValueNew))
        verifyNoMoreInteractions(mObserver)
    }

    @Test
    fun getFromDbWithNetworkFail() {
        val dbTestValue = TestValue(1)
        val apiTestValue = TestValue(1)

        mShouldFetch = { true }

        mSaveCallResult = { testValue ->
            mDbData.postValue(testValue)
        }

        mNetworkBoundResource.asLiveData().observeForever(mObserver)

        verify(mObserver).onChanged(Resource.loading<TestValue>(null))
        reset(mObserver)

        mDbData.value = dbTestValue
        verify(mObserver).onChanged(Resource.loading(dbTestValue))
        reset(mObserver)

        mApiData.value = ApiResponseWrapper(Response.success(apiTestValue))
        verify(mObserver).onChanged(Resource.success(apiTestValue))
        verifyNoMoreInteractions(mObserver)
        assertThat(mDbData.value, `is`(apiTestValue))
    }


    class TestValue(internal var testyInteger: Int)
}
