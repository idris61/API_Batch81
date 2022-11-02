package Api_Tekrar;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q06a extends RestfulBaseUrl {
        /*
        Given
            https://restful-booker.herokuapp.com/booking/1556
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
            "firstname": "Edgar",
            "lastname": "Vera",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }
     */

    @Test
    public void testQ06a(){

        // 1-Set the Url
        spec.pathParams("first","booking","second","1556");

        // 2-Set the Expected Data (Put, Post, Delete)

        // 3-Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4-Do Assertion
                // 1. yöntem
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Edgar"),
                        "lastname", equalTo("Vera"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo( "2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Breakfast"));

                // 2. yöntem
        JsonPath jsonPath = response.jsonPath();

        assertEquals("Edgar",jsonPath.getString("firstname"));
        assertEquals("Vera",jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

                // 3. yöntem
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("firstname"),"Edgar");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Vera");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111);
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"));
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast");

        softAssert.assertAll(); // !!! UNUTMUYORUZ...

    }

}
