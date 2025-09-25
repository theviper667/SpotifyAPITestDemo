import core.APIClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TrackTest {
    @Test(description = "Get track by valid ID")
    public void getTrackValidId() {
        String trackId = "6habFhsOp2NvshLv26DqMb";
        Response r = APIClient.get("/tracks/" + trackId);
        Assert.assertEquals(r.statusCode(), 200);
        Assert.assertEquals(r.jsonPath().getString("id"), trackId);
    }

    @Test(description = "Get track by invalid ID")
    public void getTrackInvalidId() {
        Response r = APIClient.get("/tracks/badID123");
        Assert.assertTrue(r.statusCode() == 400 || r.statusCode() == 404);
    }
}
