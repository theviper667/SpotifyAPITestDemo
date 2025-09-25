import core.APIClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArtistTest {
    @Test(description = "Get artist by valid ID")
    public void getArtistValidId() {
        String adeleId = "4dpARuHxo51G3z768sgnrY";
        Response r = APIClient.get("/artists/" + adeleId);
        Assert.assertEquals(r.statusCode(), 200);
        Assert.assertEquals(r.jsonPath().getString("id"), adeleId);
    }

    @Test(description = "Get artist by invalid ID")
    public void getArtistInvalidId() {
        Response r = APIClient.get("/artists/invalid123");
        Assert.assertTrue(r.statusCode() == 400 || r.statusCode() == 404);
    }
}
