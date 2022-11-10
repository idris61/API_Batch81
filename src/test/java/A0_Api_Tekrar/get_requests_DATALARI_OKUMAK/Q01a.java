package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q01a {

        /*
        Given
            https://restful-booker.herokuapp.com/booking/145
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void test01a(){

        // 1- Set the Url
        String url = "https://restful-booker.herokuapp.com/booking/145";

        // 2- Set the Expected Data (Put, Post, Delete)

        // 3- Send the Request and Get the Response
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4- Do Assertion
                // 1. yöntem
        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

                // 2. yöntem
        assertEquals(200,response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

                // 3. yöntem
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(200,response.getStatusCode());
        softAssert.assertEquals("application/json; charset=utf-8", response.getContentType());
        softAssert.assertEquals("HTTP/1.1 200 OK",response.statusLine());
        softAssert.assertAll(); // !!! UNUTMUYORUZ...






    }
}
