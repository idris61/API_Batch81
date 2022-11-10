package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q10_TestDataIleCozum extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
            {
        "meta": null,
        "data": {
            "id": 2986,
            "name": "Triloki Jha",
            "email": "jha_triloki@paucek-gottlieb.net",
            "gender": "female",
            "status": "inactive"
              }
         }
        */
    @Test
    public void testQ10(){

        // 1- Set the Url
        spec.pathParams("first","users","second",2986);

        // 2- Set the Expected Data
        GoRestTestData obj = new GoRestTestData();
        Map<String,String> dataKeyMap = obj.dataKeyMap("Triloki Jha","jha_triloki@paucek-gottlieb.net","female","inactive");
        System.out.println("dataKeyMap = " + dataKeyMap);
        Map<String,Object> expectedData = obj.expectedDataMethod(null, dataKeyMap);
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)(actualData.get("data"))).get("name"));
        assertEquals(dataKeyMap.get("email"),((Map)(actualData.get("data"))).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)(actualData.get("data"))).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)(actualData.get("data"))).get("status"));




    }
}
