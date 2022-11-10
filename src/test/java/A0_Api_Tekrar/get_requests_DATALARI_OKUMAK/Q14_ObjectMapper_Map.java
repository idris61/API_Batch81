package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q14_ObjectMapper_Map extends JsonplaceholderBaseUrl {
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
    public void testQ14(){
        //get request yapıyoruz. expected datayı convert methoda string olarak koy. map e çevir
        //responce yi de map e çevir ve karşılaştır.

        // 1- Set the Url
        spec.pathParams("first","todos","second", 198);

        // 2- Set the Expected Data
        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);
        Map expectedData = ObjectMapperUtils.convertJsonToJava(expectedDataInString, Map.class);// Map<String,Object> expectedData = ObjectMapperUtils.convertJsonToJava(expectedDataInString, Map.class);
        // GET REQUEST YAPACAĞIMIZ DATAYI, JSONPLACEHOLDERTESTDATA CLASSINDA BULUNAN METHOD İLE STRING'E ÇEVİRİP,
        // CONVERTJSONTOJAVA METHODUNA KOYDUK. ==> EXPECTED DATAMIZI MAP OLARAK OLUŞTURDUK.
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertions
        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
