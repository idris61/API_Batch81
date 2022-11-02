package Api_Tekrar;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Q02a {

    /* Given
            https://restful-booker.herokuapp.com/booking/1
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void testQ02a(){

        // 1- Set the Url
        String url = "https://restful-booker.herokuapp.com/booking/1";

        // 2- Set the Expected Data (Put, Post, Delete)

        // 3- Send the Request Get the Response
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4- Do Assertion
                // 1. yöntem
        response.then().assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");
                // 2. yöntem
        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());

        assertTrue(response.asString().contains("Not Found"));
        assertFalse(response.asString().contains("TechProEd"));
        assertEquals("Cowboy",response.getHeader("Server"));



    }

}
