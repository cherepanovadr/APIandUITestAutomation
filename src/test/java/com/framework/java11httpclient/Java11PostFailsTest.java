package com.framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11PostFailsTest {
    private static final String BASE_URL = "https://api.github.com/";

    static HttpResponse<Void> response;

    @Test
    void postWithoutAuthorizationFails() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest post = HttpRequest.newBuilder(URI.create(BASE_URL + "user/repos"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        response = httpClient.send(post, HttpResponse.BodyHandlers.discarding());
        int actualStatus = response.statusCode();
        Assertions.assertEquals(401, actualStatus);
    }

}
