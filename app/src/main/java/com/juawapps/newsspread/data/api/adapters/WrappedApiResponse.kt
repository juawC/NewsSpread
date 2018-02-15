package com.juawapps.newsspread.data.api.adapters

import com.google.gson.annotations.SerializedName

/**
 * Default object response from the api.
 */
internal class WrappedApiResponse<T> {

    var status: String? = null
    @SerializedName(value = "articles", alternate = ["sources"])
    var content: T? = null
}
