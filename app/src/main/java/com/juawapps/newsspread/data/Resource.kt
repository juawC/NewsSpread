package com.juawapps.newsspread.data

/*
  Created by joaocevada on 13/12/2017.
 */


import com.juawapps.newsspread.data.Resource.Status.ERROR
import com.juawapps.newsspread.data.Resource.Status.LOADING
import com.juawapps.newsspread.data.Resource.Status.SUCCESS

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T> constructor(private val status: Status,
                                       val data: T?,
                                       private val message: String?) {

    val isError: Boolean
        get() = status == ERROR

    val isSuccess: Boolean
        get() = status == SUCCESS


    val isLoading: Boolean
        get() = status == LOADING

    /**
     * Status of a resource that is provided to the UI.
     *
     *
     * These are usually created by the Repository classes where they return
     * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
     */
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    fun hasData(): Boolean {
        return data != null && data is Collection<*> && !(data as Collection<*>).isEmpty() || data != null && data !is Collection<*>
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
