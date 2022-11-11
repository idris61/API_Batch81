package Odev;

import base_urls.AutomationexerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev1 extends AutomationexerciseBaseUrl {
     /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void test01(){

        // 1- Set the Url
        spec.pathParams("first","api","second","brandsList");

        // 2- Set the Expected Data

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertions
        assertEquals(200,response.getStatusCode());
        assertEquals("text/html; charset=utf-8", response.contentType());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());

        JsonPath jsonPath = response.jsonPath();
        response.prettyPrint();
        List<String> brands = jsonPath.getList("brands.brand");
        System.out.println("brands = " + brands);

        int HMIndex =0;
        int PoloIndex=0;

        for (String w : brands) {
            if(w.equals("H&M")){
                HMIndex ++;
            }
            if(w.equals("Polo")){
                PoloIndex ++;
            }
        }
        System.out.println("HMIndex = " + HMIndex);
        System.out.println("PoloIndex = " + PoloIndex);
        assertEquals(HMIndex,PoloIndex);

    }


}
