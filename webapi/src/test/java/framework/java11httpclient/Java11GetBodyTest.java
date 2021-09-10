package framework.java11httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testframework.entitites.User;
import testframework.handlers.JsonBodyHandler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11GetBodyTest {
    private static final String BASE_URL = "https://api.github.com/";
    static HttpResponse<String> response;

    @Test
    void bodyContainsCurrentUserUrl_StringParsing() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL + "users/cherepanovadr"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        Assertions.assertTrue(body.contains("\"login\":\"cherepanovadr\""));
    }

    @Test
    void bodyContainsCurrentUserUrl_ObjectMapping() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL + "users/cherepanovadr"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        HttpResponse<User> responseUser = httpClient.send(get, JsonBodyHandler.jsonBodyHandler(User.class));
        String actualLogin = responseUser.body().getLogin();
        Assertions.assertEquals("cherepanovadr", actualLogin);
    }


}


