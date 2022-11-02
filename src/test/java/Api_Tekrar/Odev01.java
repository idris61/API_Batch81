package Api_Tekrar;

import base_url.AutomationexerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev01 extends AutomationexerciseBaseUrl {

   /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be "text/html; charset=utf-8"
    And
        Status Line should be HTTP/1.1 200 OK
    And
         There must be 12 Women, 9 Men, 13 Kids usertype in products
   */

    @Test
    public void test01(){

        // 1-Set the Url
        spec.pathParams("first","api","second","productsList");

        // 2-Set the Expected Data (Put, Post, Delete)

        // 3-Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4-Do Assertion
        assertEquals(200,response.getStatusCode());
        assertEquals("text/html; charset=utf-8",response.contentType());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

        // 1. yöntem
        JsonPath jsonPath = response.jsonPath();
        response.prettyPrint();

        List<String> womens = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("womens = " + womens);

        List<String> Men = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        System.out.println("Men = " + Men);

        List<String> Kids = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        System.out.println("Kids = " + Kids);

        assertEquals(12,womens.size());
        assertEquals(9,Men.size());
        assertEquals(13,Kids.size());

        // 2. yöntem
        List<String> usertype = jsonPath.getList("products.category.usertype.usertype");
        System.out.println("usertype = " + usertype);
        System.out.println("usertype.size() = " + usertype.size());

        int womenIndex=0;
        int menIndex=0;
        int kidsIndex=0;
        for (String w:usertype
        ) {
            if (w.equals("Women")){
                womenIndex++;
            }else if (w.equals("Men")){
                menIndex++;
            }else if (w.equals("Kids")){
                kidsIndex++;
            }
        }
        System.out.println("kidsIndex = " + kidsIndex);
        System.out.println("menIndex = " + menIndex);
        System.out.println("womenIndex = " + womenIndex);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(womenIndex,12);
        softAssert.assertEquals(menIndex,9);
        softAssert.assertEquals(kidsIndex,13);
        softAssert.assertAll();

        // Alternatif doğrulama
        assertEquals(12, usertype.stream().filter(t -> t.equals("Women")).count());
        assertEquals(9, usertype.stream().filter(t -> t.equals("Men")).count());
        assertEquals(13, usertype.stream().filter(t -> t.equals("Kids")).count());



    }

}
