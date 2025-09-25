package core;

import io.qameta.allure.Allure;
import utils.ConfigReader;

import java.time.Instant;
import java.util.Base64;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenManager {
    private static String token;
    private static Instant expiry = Instant.EPOCH;

    public static synchronized String getToken() {
        if (token == null || Instant.now().isAfter(expiry.minusSeconds(10))) {
            fetchToken();
        }
        return token;
    }

    private static void fetchToken() {
        String clientId = ConfigReader.get("CLIENT_ID");
        String clientSecret = ConfigReader.get("CLIENT_SECRET");
        if (clientId == null || clientSecret == null) {
            throw new IllegalStateException("CLIENT_ID and CLIENT_SECRET must be set to retrieve auth token");
        }
        String auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        Response response = RestAssured.given()
                .baseUri(ConfigReader.get("AUTHORIZATION_URL"))
                .contentType(ContentType.URLENC)
                .header("Authorization", "Basic " + auth)
                .formParams(Map.of("grant_type", "client_credentials"))
                .when()
                .post("api/token")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.jsonPath().getString("access_token");
        int expiresIn = response.jsonPath().getInt("expires_in");
        expiry = Instant.now().plusSeconds(expiresIn);
        Allure.step("Retrieved token successfully");
    }
}
