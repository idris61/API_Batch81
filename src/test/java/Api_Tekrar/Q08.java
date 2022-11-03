package Api_Tekrar;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q08 extends JsonplaceholderBaseUrl {

      /*
        Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void test08(){

        // 1-Set the Url
        spec.pathParams("first","todos","second",2);

        // 2-Set the Expected Data (Put, Post, Delete)
        // MAP YÖNTEMİ İLE EXPECTED DATA OLUŞTURARAK, SONUÇLARI KARŞILAŞTIRACAĞIZ.Daha önce body ile assertion yapmıştık.
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);

        // 3-Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4-Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); //De-Serialization - Json'ı --> Java'ya çevirdik.
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));
        assertEquals(200, response.getStatusCode());


    }

}
