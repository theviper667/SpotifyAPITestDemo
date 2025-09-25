package core;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import java.util.Base64;

public class APIClient {
    public static Response get(String path) {
        return RestAssured.given()
                .baseUri(ConfigReader.get("BASE_URL"))
                .auth().oauth2(TokenManager.getToken())
                .when()
                .get(path)
                .then().extract().response();
    }

    public static Response search(String query, String type, int limit) {
        return RestAssured.given()
                .baseUri(ConfigReader.get("BASE_URL"))
                .auth().oauth2(TokenManager.getToken())
                .param("q", query)
                .param("type", type)
                .param("limit", limit)
                .when()
                .get("/search")
                .then().extract().response();
    }

    public static Response searchWithOffset(String query, String type, int limit, int offset) {
        return RestAssured.given()
                .baseUri(ConfigReader.get("BASE_URL"))
                .auth().oauth2(TokenManager.getToken())
                .param("q", query)
                .param("type", type)
                .param("limit", limit)
                .param("offset", offset)
                .when()
                .get("/search")
                .then().extract().response();
    }

    public static Response getInvalidToken(String clientId, String clientSecret) {
        String auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        return RestAssured.given()
                .baseUri(ConfigReader.get("token.url"))
                .contentType("application/x-www-form-urlencoded")
                .header("Authorization", "Basic " + auth)
                .formParam("grant_type", "client_credentials")
                .when()
                .post()
                .then().extract().response();
    }
}
