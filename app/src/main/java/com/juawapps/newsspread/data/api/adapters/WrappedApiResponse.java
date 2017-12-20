package com.juawapps.newsspread.data.api.adapters;

import com.google.gson.annotations.SerializedName;

/**
 * Default object response from the api.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
class WrappedApiResponse<Object> {

    private String status;
    @SerializedName(value="articles", alternate={"sources"})
    private
    Object content;

    public String getStatus() {
        return status;
    }

    public Object getContent() {
        return content;
    }
}
