package com.juawapps.newsspread.data.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;


@RunWith(JUnit4.class)
public class ApiResponseWrapperTest {

    @Test
    public void exception() {
        String exceptionStr = "Puff";
        Exception exception = new Exception(exceptionStr);
        ApiResponseWrapper<String> apiResponse = new ApiResponseWrapper<>(exception);
        assertThat(apiResponse.getBody(), nullValue());
        assertThat(apiResponse.getCode(), is(500));
        assertThat(apiResponse.getErrorMessage(), is(exceptionStr));
        assertFalse(apiResponse.isSuccessful());
    }

    @Test
    public void success() {
        String successStr = "Yay";

        ApiResponseWrapper<String> apiResponse = new ApiResponseWrapper<>(Response.success(successStr));
        assertThat(apiResponse.getErrorMessage(), nullValue());
        assertThat(apiResponse.getCode(), is(200));
        assertThat(apiResponse.getBody(), is(successStr));
        assertTrue(apiResponse.isSuccessful());
    }

    @Test
    public void error() {
        String errorStr = "Not found!";

        ApiResponseWrapper<String> apiResponse = new ApiResponseWrapper<>(Response.error(404,
                ResponseBody.create(MediaType.parse("application/json"), errorStr)));
        assertThat(apiResponse.getErrorMessage(), equalTo(errorStr));
        assertThat(apiResponse.getCode(), is(404));
        assertFalse(apiResponse.isSuccessful());
    }

}
