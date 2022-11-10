package A0_Api_Tekrar.patch_requests_KISMEN_GUNCELLEMEK;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q01 extends JsonplaceholderBaseUrl {
         /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "title": "Wash the dishes"
           }
    When
        I send PATCH Request to the Url
    Then
         Status code is 200
         And response body is like   {
                      "userId": 10,
                      "title": "Wash the dishes",
                      "completed": true,
                      "id": 198
                      }
         */
    @Test
    public void testQ01(){

        // 1- Set the Url
        spec.pathParams("first","todos","second",198);

        // 2- Set the Expected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(null,"Wash the dishes",null);
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        // 4- Do Assertions
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("title"), actualData.get("title"));
    }

}
