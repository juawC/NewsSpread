/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.juawapps.newsspread.data.api.adapters;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.juawapps.newsspread.data.api.ApiResponseWrapper;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
 */
class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponseWrapper<R>>> {
    private final Type mResponseType;
    LiveDataCallAdapter(Type responseType) {
        this.mResponseType = responseType;
    }

    @Override
    public Type responseType() {
        return mResponseType;
    }

    @Override
    public LiveData<ApiResponseWrapper<R>> adapt(@NonNull Call<R> call) {
        return new LiveData<ApiResponseWrapper<R>>() {
            final AtomicBoolean started = new AtomicBoolean(false);
            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                            postValue(new ApiResponseWrapper<>(response));
                        }

                        @Override
                        public void onFailure(@NonNull Call<R> call, @NonNull Throwable throwable) {
                            postValue(new ApiResponseWrapper<>(throwable));
                        }
                    });
                }
            }
        };
    }
}