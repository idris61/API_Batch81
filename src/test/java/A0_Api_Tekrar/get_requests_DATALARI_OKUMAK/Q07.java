package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q07 extends JsonplaceholderBaseUrl {

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
    public void test07(){

        // 1-Set the Url
        spec.pathParam("first","todos");

        // 2-Set the Expected Data (Put, Post, Delete)

        // 3-Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        // 4-Do Assertion
            // 1) Status code is 200 == > Status kodu 200 olmali
            // 1. yol
        response.then().assertThat().statusCode(200);
            // 2. yol
        assertEquals(200, response.statusCode());
            // 3. yol
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();

            // 2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("id'si 190 dan buyuk olanlar = " + ids);

            // Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
        assertEquals("id'si 190 dan buyuk olan eslesmedi", 10, ids.size());

            // 3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
        List<Integer> userIds = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("id'si 5 den kucuk olan userIds = " + userIds);

            // Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
        assertEquals("id si 5 den kucuk olan useridler 4 adet degil",4, userIds.size());

            // 4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
        List<String> titles = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("id si 5 ten kucuk olan title lar = " + titles);

            // Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
            // basliginin "delectus aut autem" icerdigini dogrulayin
        assertTrue("id'si 5 den kucuk olan title lardan herhangi bir tanesi deletctu aut autem icermemektedir."
                             ,titles.contains("delectus aut autem"));



    }

}
