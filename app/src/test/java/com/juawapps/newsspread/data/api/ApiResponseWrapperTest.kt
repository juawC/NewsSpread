package com.juawapps.newsspread.data.api

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.nullValue


@RunWith(JUnit4::class)
class ApiResponseWrapperTest {

    @Test
    fun exception() {
        val exceptionStr = "Puff"
        val exception = Exception(exceptionStr)
        ApiResponseWrapper<Any>(exception).also {
            assertThat(it.body, nullValue())
            assertThat(it.code, `is`(500))
            assertThat(it.errorMessage, `is`(exceptionStr))
            assertFalse(it.isSuccessful)
        }
    }

    @Test
    fun success() {
        val successStr = "Yay"
        val response = Response.success(successStr)
        ApiResponseWrapper(response).also {
            assertThat(it.errorMessage, nullValue())
            assertThat(it.code, `is`(200))
            assertThat(it.body, `is`(successStr))
            assertTrue(it.isSuccessful)
        }

    }

    @Test
    fun error() {
        val errorStr = "Not found!"
        val response = Response.error<String>(404,
                ResponseBody.create(MediaType.parse("application/json"), errorStr))
        ApiResponseWrapper(response).also {
            assertThat(it.errorMessage, equalTo(errorStr))
            assertThat(it.code, `is`(404))
            assertFalse(it.isSuccessful)
        }

    }

}
