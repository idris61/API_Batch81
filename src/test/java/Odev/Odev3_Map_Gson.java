package Odev;

import base_urls.ReqresBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev3_Map_Gson extends ReqresBaseUrl {

    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.

    /*
            Given
                1) https://reqres.in/api/users/2
                2) {
                    "name": "morpheus",
                    "job": "zion president"
                    }
            When
                I send Put Request to the Url
            Then
                Status code is 200
                And response body should be like {
                                                    "name": "morpheus",
                                                    "job": "zion president",
                                                    "updatedAt": "2022-11-10T15:24:39.979Z"
                                                }
    */
    @Test
    public void test03(){

        // 1- Set the Url
        spec.pathParams("first","users","second",2);

        // 2- Set the Expected Data
        ReqresTestData obj = new ReqresTestData();
        Map<String,String> expectedData = obj.expectedDataMethod("morpheus","zion president");
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertion
        Gson gson = new Gson();
        Map<String,String> actualData = gson.fromJson(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));
    }
}
