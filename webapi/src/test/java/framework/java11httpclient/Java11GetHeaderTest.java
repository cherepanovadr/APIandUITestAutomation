package framework.java11httpclient;

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

public class Java11GetHeaderTest {
    private static final String BASE_URL = "https://api.github.com/";
    static HttpClient httpClient = HttpClient.newBuilder().build();
    static HttpResponse<Void> response;

    @BeforeAll
    static void sendGetToBaseEndpoint() throws IOException, InterruptedException {
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .GET()
                .setHeader("User-Agent", "Java 11 http bot")
                .build();

        response = httpClient.send(get, HttpResponse.BodyHandlers.discarding());
    }

    @Test
    void getReturns200() throws IOException, InterruptedException {
        int actualCode = response.statusCode();
        Assertions.assertEquals(200, actualCode);
    }

    @ParameterizedTest
    @CsvSource({"X-Ratelimit-Limit,60",
            "content-type, application/json; charset=utf-8",
            "server,GitHub.com",
            "x-frame-options,deny"})

    void parametrizedTestForHeaders(String header, String expectedResult) throws IOException, InterruptedException {
        String actualResult = response.headers().firstValue(header).get();
        Assertions.assertEquals(expectedResult, actualResult);
    }


}
