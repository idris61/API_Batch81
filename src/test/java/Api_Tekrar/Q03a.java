package Api_Tekrar;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Q03a extends JsonplaceholderBaseUrl {

        /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response format should be "application/json"
        And
            "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
        And
            "completed" is false
        And
            "userId" is 2
     */

    @Test
    public void testQ03a(){

        // 1- Set the Url
        spec.pathParams("first","todos","second",23);

        // 2- Set the Expected Data (Put, Post, Delete)

        // 3- Send the Request Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4- Do assertions
        // 1. Yol ( Hard Assert)
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));

        // 2. Yol Sadece body içerisinde geçerli bir (Soft Assert)
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));
        // -----
        JsonPath jsonPath = response.jsonPath(); // response ı jsonPath'e dönüştürüyor.

        assertEquals(200,response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());

        assertEquals("et itaque necessitatibus maxime molestiae qui quas velit", jsonPath.getString("title"));
        assertEquals("false", jsonPath.getString("completed"));
        assertEquals("2", jsonPath.getString("userId"));

        // Soft Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(200,response.getStatusCode(),"status code yanlis");
        softAssert.assertEquals("application/json; charset=utf-8", response.getContentType());
        softAssert.assertEquals(jsonPath.getString("title"),"et itaque necessitatibus maxime molestiae qui quas velit");
        softAssert.assertEquals(jsonPath.getString("completed"),"false");
        softAssert.assertEquals(jsonPath.getString("userId"),"2");

        softAssert.assertAll(); // !!! UNUTMUYORUZ...


    }

}
