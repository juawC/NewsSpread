package com.juawapps.newsspread.data;

/*
  Created by joaocevada on 13/12/2017.
 */


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.juawapps.newsspread.data.Resource.Status.ERROR;
import static com.juawapps.newsspread.data.Resource.Status.LOADING;
import static com.juawapps.newsspread.data.Resource.Status.SUCCESS;

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
@SuppressWarnings("WeakerAccess")
public class Resource<T> {

    /**
     * Status of a resource that is provided to the UI.
     * <p>
     * These are usually created by the Repository classes where they return
     * {@code LiveData<Resource<T>>} to pass back the latest data to the UI with its fetch status.
     */
    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    @NonNull
    public final Status status;

    @Nullable
    private final String message;

    @Nullable
    public final T data;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        return status == resource.status && (message != null ? message.equals(resource.message) : resource.message == null) && (data != null ? data.equals(resource.data) : resource.data == null);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
