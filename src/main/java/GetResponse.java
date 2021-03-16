import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetResponse {
    String baseURL = "http://www.omdbapi.com";

    public Response getResponseBody(HashMap<String, String> params) {
        Response response = given()
                .params(params)
                .get(baseURL);
        response.then().log().body();
        return response;
    }

    public int getResponseStatus(Response response) {
        return response.getStatusCode();
    }

    public void checkParameterIsNull(Response response, String parameter) {
        response.then().body(parameter, notNullValue());
    }

    public String getParameterWithPath(Response response, String path) {
        return response.then().extract().path(path);
    }
}
