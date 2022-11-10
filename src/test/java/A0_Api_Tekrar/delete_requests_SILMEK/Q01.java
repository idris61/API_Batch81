package A0_Api_Tekrar.delete_requests_SILMEK;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q01 extends JsonplaceholderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void testQ01(){

        // 1- Set the Url
        spec.pathParams("first","todos","second",198);

        // 2- Set the Expected Data
        Map<String,String> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        // 4- Do Assertions
        // 1.yol ==> as() methoduyla; json datayı java objesine çevirerek doğrulama yapıyoruz.
        Map<String, String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData,actualData);

        // 2.yol ==> ObjectMapper() methoduyla; readValue() içerisine koyduğumuz String bir datayı
                   // (String data/ Response.asString) Map'e (HashMap.class) çevirerek doğrulama yapıyoruz
        Map actualData2 = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData2 = " + actualData2);

        assertEquals(expectedData, actualData2); // 1.yol
        assertTrue(actualData2.isEmpty()); // 2.yol
        assertEquals(0,actualData2.size()); // 3.yol
    }
}
