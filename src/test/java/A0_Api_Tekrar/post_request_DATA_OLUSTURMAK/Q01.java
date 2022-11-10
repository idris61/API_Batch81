package A0_Api_Tekrar.post_request_DATA_OLUSTURMAK;

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
    public void testQ01() {

        // 1- Set the Url
        spec.pathParam("first","todos");

        // 2- Set the Ected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(55,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4- Do assertions
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));


    }
}
