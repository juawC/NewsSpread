package com.juawapps.newsspread.data.api.adapters;

import com.juawapps.newsspread.data.api.ApiStatus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by joaocevada on 14/12/2017.
 */

class UnwrapResponseConverter<T>
        implements Converter<ResponseBody, T> {

    private final Converter<ResponseBody, WrappedApiResponse<T>> converter;

    public UnwrapResponseConverter(Converter<ResponseBody,
            WrappedApiResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        WrappedApiResponse<T> response = converter.convert(value);

        if (response.getStatus().equalsIgnoreCase(ApiStatus.STATUS_OK)) {
            return response.getContent();
        }

        throw new ApiError("");
    }

}

