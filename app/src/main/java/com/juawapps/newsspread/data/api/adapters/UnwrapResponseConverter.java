package com.juawapps.newsspread.data.api.adapters;

import android.support.annotation.NonNull;

import com.juawapps.newsspread.data.api.ApiStatus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Unwraps the object response from the api to the actual object.
 */

class UnwrapResponseConverter<T>
        implements Converter<ResponseBody, T> {

    private final Converter<ResponseBody, WrappedApiResponse<T>> converter;

    UnwrapResponseConverter(Converter<ResponseBody,
            WrappedApiResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        WrappedApiResponse<T> response = converter.convert(value);

        if (response.getStatus().equalsIgnoreCase(ApiStatus.STATUS_OK)) {
            return response.getContent();
        }

        throw new ApiError("");
    }

}

