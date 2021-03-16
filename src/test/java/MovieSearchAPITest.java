import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class MovieSearchAPITest {
    String firstRequestParam = "s";
    String searchKey = "Harry Potter";
    String secondRequestParam = "i";
    String authParam = "apiKey";
    String authKey = "13960ca0";
    String path = "Search.find {it.Title == \"Harry Potter and the Sorcerer's Stone\"}.imdbID";

    @Test
    public void MovieSearch() {
        GetResponse getResponse = new GetResponse();

        HashMap<String, String> firstRequestParams = new HashMap<>();
        firstRequestParams.put(firstRequestParam, searchKey);
        firstRequestParams.put(authParam, authKey);

        Response responseWithTitle = getResponse
                .getResponseBody(firstRequestParams);

        Assert.assertEquals(getResponse.getResponseStatus(responseWithTitle), 200);

        String imdbID = getResponse
                .getParameterWithPath(responseWithTitle, path);

        HashMap<String, String> secondRequestParams = new HashMap<>();
        secondRequestParams.put(secondRequestParam, imdbID);
        secondRequestParams.put(authParam, authKey);

        Response responseWithID = getResponse
                .getResponseBody(secondRequestParams);

        Assert.assertEquals(getResponse.getResponseStatus(responseWithID), 200);

        getResponse.checkParameterIsNull(responseWithID, "Title");
        getResponse.checkParameterIsNull(responseWithID, "Year");
        getResponse.checkParameterIsNull(responseWithID, "Released");
    }
}
