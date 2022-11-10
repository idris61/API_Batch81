package A2_post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonplaceholderBaseUrl {

     /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)
           When
               I send POST Request to the Url
           Then
               Status code is 201 {
                    "userId": 55,
                    "title": "Tidy your room",
                    "completed": false
                                 }
           And
               response body is like {
                                       "userId": 55,
                                       "title": "Tidy your room",
                                       "completed": false,
                                       "id": 201
                                       }
        */

    @Test
    public void post01() {

        // 1- Set the Url
        spec.pathParam("first", "todos");

        // 2- Set the Expected Data (Put, Post, Delete)
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(55,"Tidy your room",false);

        // 3- Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4- Do assertions
        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("UserId"), actualData.get("UserId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
    }
}