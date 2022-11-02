package Api_Tekrar;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q07a extends JsonplaceholderBaseUrl {
           /*
      Given
          https://jsonplaceholder.typicode.com/todos
      When
          I send GET Request to the URL
          == > URL'e Get Request gonderin
      Then
          1) Status code is 200
             == > Status kodu 200 olmali
          2) Print all ids greater than 190 on the console
             ==> id si 190 dan buyuk olanlari konsola yazdirin
             Assert that there are 10 ids greater than 190
             == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
          3) Print all userIds whose ids are less than 5 on the console
             ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
             Assert that the number of userIds whose ids are less than 5 is 4
             ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
          4) Print all titles whose ids are less than 5
             ==> ıd si 5 den kucuk olan tum basliklari yazdirin
             Assert that "delectus aut autem" is one of the titles whose id is less than 5
             ==> id si 5 den kucuk olan datalarin birinin basliginin "delectus aut autem" icerdigini dogrulayin
     */
    @Test
    public void test(){
        // 1-Set the url
        spec.pathParam("first", "todos");

        // 2-Set the Expected dat (Put, Post, Delete)

        // 3-Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // 4-do assertions
            // 1) Status code is 200
        response.then().assertThat().statusCode(200);

        assertEquals(200,response.getStatusCode());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(200,response.getStatusCode());
        softAssert.assertAll(); // !!! UNUTMUYORUZ...

            // 2) Print all ids greater than 190 on the console
        JsonPath jsonPath = response.jsonPath();
        List<Integer> allIds = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("allIds = " + allIds);
            // Assert that there are 10 ids greater than 190
        assertEquals(10, allIds.size());

            // 3) Print all userIds whose ids are less than 5 on the console
        List<Integer> userIds = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIds = " + userIds);
            // Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4, userIds.size());

            // 4) Print all titles whose ids are less than 5
        List<String> titles = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("titles = " + titles);
            // Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titles.contains("delectus aut autem"));

    }
}
