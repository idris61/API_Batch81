package Api_Tekrar;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q03 extends ReqresBaseUrl {
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “email” is “janet.weaver@reqres.in”,
       And
           “first_name” is "Janet"
       And
           “last_name” is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

    @Test
    public void test03(){

        // 1-Set The Url
        spec.pathParams("first", "users","second",2);

        // 2-Set The Expected Data (Put, Post, Delete)

        // 3-Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
       // response.prettyPrint();

        // 4-Do Assertion
        // Hard Assertion
        response.
                then().
                assertThat().
                statusCode(200 ).
                contentType(ContentType.JSON).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        // Soft Assertion (sadece body içerisinde doğrulama)
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.email", equalTo("janet.weaver@reqres.in"),
                        "data.first_name", equalTo("Janet"),
                        "data.last_name", equalTo("Weaver"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        // -----
        JsonPath jsonPath = response.jsonPath(); // response ı jsonPath'e dönüştürüyor.

        assertEquals(200,response.getStatusCode());
        assertEquals("application/json; charset=utf-8",response.getContentType());

        assertEquals("janet.weaver@reqres.in",jsonPath.getString("data.email"));
        System.out.println("data.email = "  + jsonPath.getString("data.email"));

        assertEquals("Janet", jsonPath.getString("data.first_name"));
        System.out.println("data.first_name = " + jsonPath.getString("data.first_name"));

        assertEquals("Weaver", jsonPath.getString("data.last_name"));
        System.out.println("data.last_name = " + jsonPath.getString("data.last_name"));

        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", jsonPath.getString("support.text"));
        System.out.println("support.text = " + jsonPath.getString("support.text"));

        // Soft Assertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(200,response.getStatusCode(),"status code degeri yanlis");
        softAssert.assertEquals("application/json; charset=utf-8",response.getContentType(), "content type degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("data.email"),"janet.weaver@reqres.in","email degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("data.first_name"),"Janet", "first name degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("data.last_name"),"Weaver", "last name degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text degeri yanlis");

        softAssert.assertAll(); // !!! UNUTMUYORUZ...
    /* iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini
            sagliyoruz.
            Eger islemin sonunda softAssert.assertAll() kullanmaz isek taleplerimiz hatalı bile olsa testimiz pass olacaktir.
     */

    }
}
