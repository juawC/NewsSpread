package com.juawapps.newsspread.data;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.juawapps.newsspread.data.api.ApiResponseWrapper;
import com.juawapps.newsspread.utils.AppExecutors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Function;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class NetworkBoundResourceTest {

    @Rule
    public InstantTaskExecutorRule mInstantExecutorRule = new InstantTaskExecutorRule();

    private final Executor mInstantExecutor = Runnable::run;

    private NetworkBoundResource mNetworkBoundResource;

    private Function<TestValue, Void> mSaveCallResult;
    private Function<TestValue, Boolean> mShouldFetch;
    private MutableLiveData<TestValue> mDbData;
    private MutableLiveData<ApiResponseWrapper<TestValue>> mApiData;

    Observer<Resource<TestValue>> mObserver;

    @Before
    public void  init() {
        mObserver= Mockito.mock(Observer.class);
        mApiData = new MutableLiveData<>();
        mDbData = new MutableLiveData<>();

        mNetworkBoundResource = new NetworkBoundResource<TestValue, TestValue>(
                new AppExecutors(mInstantExecutor, mInstantExecutor, mInstantExecutor)) {

            @Override
            protected void saveCallResult(@NonNull TestValue item) {
                mSaveCallResult.apply(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable TestValue data) {
                return mShouldFetch.apply(data);
            }

            @NonNull
            @Override
            protected LiveData<TestValue> loadFromDb() {
                return mDbData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponseWrapper<TestValue>> createCall() {
                return mApiData;
            }
        };

    }

    @Test
    public void getFromNetwork() {
        TestValue netWorkResult = new TestValue(1);

        mShouldFetch = Objects::isNull;

        mSaveCallResult = testValue -> {
            mDbData.setValue(testValue);
            return null;
        };


        mNetworkBoundResource.asLiveData().observeForever(mObserver);
        verify(mObserver).onChanged(Resource.loading(null));
        reset(mObserver);
        mDbData.setValue(null);
        mApiData.setValue(new ApiResponseWrapper<>(Response.success(netWorkResult)));
        verify(mObserver).onChanged(Resource.success(netWorkResult));
        verifyNoMoreInteractions(mObserver);
    }

    @Test
    public void getFromNetworkFail() {

        ResponseBody body = ResponseBody.create(MediaType.parse("application/json"), "error");

        mShouldFetch = Objects::isNull;

        mSaveCallResult = Mockito.mock(Function.class);

        mNetworkBoundResource.asLiveData().observeForever(mObserver);

        verify(mObserver).onChanged(Resource.loading(null));
        reset(mObserver);

        mDbData.setValue(null);
        mApiData.setValue(new ApiResponseWrapper<>(Response.error(500, body)));
        verify(mSaveCallResult, never()).apply(any());
        verify(mObserver).onChanged(Resource.error("error", null));
        verifyNoMoreInteractions(mObserver);
    }

    @Test
    public void getFromDbWithoutNetwork() {
        TestValue dbTestValue = new TestValue(1);
        TestValue dbTestValueNew = new TestValue(1);

        mShouldFetch = Objects::isNull;

        mSaveCallResult = Mockito.mock(Function.class);

        mNetworkBoundResource.asLiveData().observeForever(mObserver);

        verify(mObserver).onChanged(Resource.loading(null));
        reset(mObserver);

        mDbData.setValue(dbTestValue);
        verify(mSaveCallResult, never()).apply(any());
        verify(mObserver).onChanged(Resource.success(dbTestValue));
        reset(mObserver);

        mDbData.setValue(dbTestValueNew);
        verify(mObserver).onChanged(Resource.success(dbTestValueNew));
        verifyNoMoreInteractions(mObserver);
    }

    @Test
    public void getFromDbWithNetworkFail() {
        TestValue dbTestValue = new TestValue(1);
        TestValue apiTestValue = new TestValue(1);

        mShouldFetch = (__) -> true;

        mSaveCallResult = testValue -> {
            mDbData.postValue(testValue);
            return null;
        };

        mNetworkBoundResource.asLiveData().observeForever(mObserver);

        verify(mObserver).onChanged(Resource.loading(null));
        reset(mObserver);

        mDbData.setValue(dbTestValue);
        verify(mObserver).onChanged(Resource.loading(dbTestValue));
        reset(mObserver);

        mApiData.setValue(new ApiResponseWrapper<>(Response.success(apiTestValue)));
        verify(mObserver).onChanged(Resource.success(apiTestValue));
        verifyNoMoreInteractions(mObserver);
        assertThat(mDbData.getValue(), is(apiTestValue));
    }



    static public class TestValue {

        int testyInteger;

        public TestValue(int testyInteger) {
            this.testyInteger = testyInteger;
        }
    }
}
