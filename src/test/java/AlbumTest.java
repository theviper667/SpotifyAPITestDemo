import core.APIClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlbumTest {
    @Test(description = "Get album by valid ID")
    public void getAlbumValidId() {
        String albumId = "4aawyAB9vmqN3uQ7FjRGTy"; // Example album
        Response r = APIClient.get("/albums/" + albumId);
        Assert.assertEquals(r.statusCode(), 200);
        Assert.assertEquals(r.jsonPath().getString("id"), albumId);
    }

    @Test(description = "Get album by invalid ID")
    public void getAlbumInvalidId() {
        Response r = APIClient.get("/albums/invalidXYZ");
        Assert.assertTrue(r.statusCode() == 400 || r.statusCode() == 404);
    }

    @Test(description = "Get album tracks")
    public void getAlbumTracks() {
        String albumId = "4aawyAB9vmqN3uQ7FjRGTy";
        Response r = APIClient.get("/albums/" + albumId + "/tracks");
        Assert.assertEquals(r.statusCode(), 200);
        Assert.assertFalse(r.jsonPath().getList("items").isEmpty());
    }
}
