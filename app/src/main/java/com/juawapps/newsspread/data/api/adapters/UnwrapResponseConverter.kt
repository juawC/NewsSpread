package com.juawapps.newsspread.data.api.adapters

import com.juawapps.newsspread.data.api.ApiStatus

import java.io.IOException

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Unwraps the object response from the api to the actual object.
 */

internal class UnwrapResponseConverter(private val converter: Converter<ResponseBody?, *>?) : Converter<ResponseBody, Any> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): Any? {
        val response = converter?.convert(value)

        if (response is WrappedApiResponse<*> && response.status.equals(ApiStatus.STATUS_OK, ignoreCase = true)) {
            return response.content
        }

        throw ApiError("")
    }

}

