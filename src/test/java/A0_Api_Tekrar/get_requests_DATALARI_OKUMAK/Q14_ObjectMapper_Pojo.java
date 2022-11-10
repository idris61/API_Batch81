package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q14_ObjectMapper_Pojo extends JsonplaceholderBaseUrl {
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

    // ObjectMapper + Pojo ==> En iyi yöntem

    @Test
    public void testQ14(){

        // 1- Set the Url
        spec.pathParams("first","todos","second",198);

        // 2- Set the Expected Data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).when().get(("/{first}/{second}"));
        //response.prettyPrint();

        // 4- Do Assertions
        //JsonPlaceHolderPojo actualData1 = response.as(JsonPlaceHolderPojo.class);
        //System.out.println("actualData1 = " + actualData1);

        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
    }
}
