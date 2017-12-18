package com.juawapps.newsspread.data.api.adapters;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaocevada on 10/12/2017.
 */

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
