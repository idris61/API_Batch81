package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q05a extends RestfulBaseUrl {

        /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Halinka" and lastname is "Swinka"
        */

    @Test
    public void testQ05a(){

        // 1-Set the Url
        spec.pathParam("first","booking").queryParams("firstname","Halinka","lastname","Swinka");

        // 2-Set the Expected Data (Put, Post, Delete)

        // 3-Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4-Do Assertion
        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));



    }
}

