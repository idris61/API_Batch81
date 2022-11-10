package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q08a extends JsonplaceholderBaseUrl {
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
    public void test(){

        // 1-Set the url
        spec.pathParams("first","todos","second",2);

        // 2-Set the Expected data (put, post, delete)
        // MAP YÖNTEMİ İLE EXPECTED DATA OLUŞTURARAK, SONUÇLARI KARŞILAŞTIRACAĞIZ.Daha önce body ile assertion yapmıştık.
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);

        // 3-Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4-Do assertion
        Map<String, Object> actualData = response.as(HashMap.class); //De-Serialization - Json'ı --> Java'ya çevirdik.
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));

        assertEquals(200,response.getStatusCode());
        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));

    }
}
