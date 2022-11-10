package A1_get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Map extends JsonplaceholderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void test14Map(){
        //get request yapıyoruz. expected datayı convert methoda string olarak koy. map e çevir
        //responce yi de map e çevir ve karşılaştır.

        // Set the Url
        spec.pathParams("first","todos","second",198);

        // Set the Expected Data
        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);
        Map expectedData = ObjectMapperUtils.convertJsonToJava(expectedDataInString, Map.class); // Map<String,Object> expectedData = ObjectMapperUtils.convertJsonToJava(expectedDataInString, Map.class);
        // GET REQUEST YAPACAĞIMIZ DATAYI, JSONPLACEHOLDERTESTDATA CLASSINDA BULUNAN METHOD İLE STRING'E ÇEVİRİP,
        // CONVERTJSONTOJAVA METHODUNA KOYDUK. ==> EXPECTED DATAMIZI MAP OLARAK OLUŞTURDUK.
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do assertions
        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Map.class);
        // RESPONSE BODY MİZİ DE MAP'E ÇEVİREREK ACTUAL DATAMIZI OLUŞTURDUK.
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
