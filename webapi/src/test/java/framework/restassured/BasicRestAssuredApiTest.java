package framework.restassured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssuredApiTest {

    @Test
    public void getStatusCodeIs200() {
        RestAssured.get("https://api.github.com")
                .then()
                .statusCode(200);
    }

    @Test
    public void headersContainCorrectValues() {
        RestAssured.get("https://api.github.com")
                .then()
                .assertThat()
                .header("content-type", "application/json; charset=utf-8")
                .header("X-RateLimit-Limit", "60");
    }

    @Test
    public void bodyContainsCorrectValue() {
        RestAssured.get("https://api.github.com/users/cherepanovadr")
                .then()
                .assertThat()
                .body("login", equalTo("cherepanovadr"))
                .body("type", equalTo("User"))
                .body("events_url", equalTo("https://api.github.com/users/cherepanovadr/events{/privacy}"));
    }

    @Test
    public void postFails() {
        JSONObject request = new JSONObject();
        request.put("some", "body");
        given()
                .body(request.toJSONString())
                .when()
                .post("https://api.github.com/user/repos")
                .then()
                .statusCode(401)
                .assertThat()
                .body("message", Matchers.equalTo("Requires authentication"));

    }
    @Test
    public void postFails2() {
        RestAssured.post("https://api.github.com/user/repos")
                .then()
                .statusCode(401)
                .assertThat()
                .body("message", Matchers.equalTo("Requires authentication"));
    }

}
