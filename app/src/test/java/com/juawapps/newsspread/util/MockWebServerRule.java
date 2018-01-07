package com.juawapps.newsspread.util;

import android.arch.persistence.room.Room;

import com.juawapps.newsspread.data.db.AppDatabase;

import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;

/**
 * Test rule to start and shutdown MockWebServer.
 */

public class MockWebServerRule extends ExternalResource {
    private MockWebServer mockWebServer;

    @Override
    protected void before() throws Throwable {

        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @Override
    protected void after() {
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpUrl getUrl() {
        return mockWebServer.url("/");
    }


    public void enqueueResponse(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("api-response/" + fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)));
    }
}
