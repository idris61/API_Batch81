package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get04b extends RestfulBaseUrl {
    /*
     Given
         https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
     When
         User sends get request to the URL
     Then
         Status code is 200
     And
         Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

    */

    @Test
    public void test04(){

        // 1- Set The Url
        spec.pathParam("first","booking").queryParams("firstname","Brandon","lastname","Wilson");

        // 2- Set The Expected Data (Put, Post, Delete)

        // 3- Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4- Do Assertion
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));


    }

}
