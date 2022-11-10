package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q10 extends GoRestBaseUrl {
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

        // 2- Set the Expected Data (Put, Post, Delete)
        Map<String,String> dataKeyMap = new HashMap<>();
        dataKeyMap.put("name","Triloki Jha");
        dataKeyMap.put("email","jha_triloki@paucek-gottlieb.net");
        dataKeyMap.put("gender","female");
        dataKeyMap.put("status","inactive");

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("meta",null);
        expectedData.put("data",dataKeyMap);
        System.out.println("expectedData = " + expectedData);

        // 3- Send the request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map)actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map)actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map)actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map)actualData.get("data")).get("status"));
        /*
        actualData.get("data") ==> object olduğu için (Map<String,Object>) .get("name") yapmamıza izin vermiyor.
        (Map)actualData.get("data")).get("name") ==> objeyi Map'e çevirdikten sonra get yapmamıza izin verdi.
        Key-Value ikilileri String-Object şeklinde olduğundan, Bookingdata value kısmını casting ile Map yaptık.
         */

    }
}
