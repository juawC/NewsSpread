package com.juawapps.newsspread.util


import org.junit.rules.ExternalResource

import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.BufferedSource
import okio.Okio

/**
 * Test rule to start and shutdown MockWebServer.
 */

class MockWebServerRule : ExternalResource() {
    private lateinit var mockWebServer: MockWebServer

    val url: HttpUrl
        get() = mockWebServer.url("/")

    override fun before() {

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    override fun after() {
        try {
            mockWebServer.shutdown()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/" + fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }
}
