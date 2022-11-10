package A5_delete_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonplaceholderBaseUrl {
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
    public void testDelete01(){

        // Set the Url
        spec.pathParams("first","todos","second",198);

        // Set the Expected Data
        Map<String,String> expectedData = new HashMap<>(); // Delete sonrası veri olmayacağı için boş bir map oluşturarak doğrulamada kullanabiliriz.

        // Send the request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        // Do assertion
        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData, actualData); // ==> 1. yol
        assertTrue(actualData.isEmpty()); // ==> 2. yol
        assertEquals(0,actualData.size()); // ==> 3. yol
    }
}
