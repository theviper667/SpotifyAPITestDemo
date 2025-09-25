import core.APIClient;
import core.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest {
    @Test(description = "Fetch access token with valid credentials")
    public void getAccessToken_validCredentials() {
        String token = TokenManager.getToken();
        Assert.assertNotNull(token, "Access token should not be null");
    }

    @Test(description = "Fetch token with invalid credentials returns 400")
    public void getAccessToken_invalidCredentials() {
        Response r = APIClient.getInvalidToken("wrongId", "wrongSecret");
        Assert.assertEquals(r.statusCode(), 400, "Expected 400 with invalid credentials");
    }
}
