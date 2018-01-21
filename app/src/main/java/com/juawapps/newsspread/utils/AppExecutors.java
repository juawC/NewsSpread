package com.juawapps.newsspread.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppExecutors {


    private Executor mDiskIO;

    private Executor mNetworkIO;

    private Executor mMainThread;

    public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.mDiskIO = diskIO;
        this.mNetworkIO = networkIO;
        this.mMainThread = mainThread;
    }

    @Inject
    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                new MainThreadExecutor());
    }


    public Executor diskIO() {
        return mDiskIO;
    }

    @SuppressWarnings("unused")
    public Executor networkIO() {
        return mNetworkIO;
    }

    public Executor mainThread() {
        return mMainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
