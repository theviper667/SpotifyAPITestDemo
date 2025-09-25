import core.APIClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest {
    @Test(description = "Search for an artist")
    public void searchArtist() {
        Response r = APIClient.search("Adele", "artist", 5);
        Assert.assertEquals(r.statusCode(), 200);
        Assert.assertTrue(r.jsonPath().getInt("artists.total") > 0);
    }

    @Test(description = "Search for a track")
    public void searchTrack() {
        Response r = APIClient.search("Hello", "track", 3);
        Assert.assertEquals(r.statusCode(), 200);
        String trackName = r.jsonPath().getString("tracks.items[0].name");
        Assert.assertTrue(trackName.toLowerCase().contains("hello"));
    }

    @Test(description = "Invalid search type returns 400")
    public void searchInvalidType() {
        Response r = APIClient.search("Adele", "foobar", 3);
        Assert.assertEquals(r.statusCode(), 400);
    }

    @Test(description = "Search with limit=50 returns 50 results")
    public void searchLimitBoundary() {
        Response r = APIClient.search("love", "track", 50);
        Assert.assertEquals(r.statusCode(), 200);
        Assert.assertEquals(r.jsonPath().getList("tracks.items").size(), 50);
    }

    @Test(description = "Search pagination with offset")
    public void searchOffsetPagination() {
        Response first = APIClient.searchWithOffset("love", "track", 5, 0);
        Response second = APIClient.searchWithOffset("love", "track", 5, 5);

        String firstTrack = first.jsonPath().getString("tracks.items[0].id");
        String secondTrack = second.jsonPath().getString("tracks.items[0].id");

        Assert.assertNotEquals(firstTrack, secondTrack, "Results should differ with offset");
    }
}
