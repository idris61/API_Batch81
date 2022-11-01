package Api_Tekrar;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Q05 extends ReqresBaseUrl {

         /*
        Given
            https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
        }
      */

    @Test
    public void test05(){

        // 1-Set The Url
        spec.pathParams("first", "unknown","second",3);

        // 2-Set The Expected Data (Put, Post, Delete)

        // 3-Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4-Do Assertion
        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath = response.jsonPath();
            // JsonPath ile body içerisindeki verileri;
            // jsonPath.getInt() ==> ile integer olarak
            // jsonPath.getString() ==> ile string olarak alıyoruz

        softAssert.assertEquals(jsonPath.getInt("data.id"),3,"id degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("data.name"),"true red","name degeri yanlis");
        softAssert.assertEquals(jsonPath.getInt("data.year"), 2002, "yil degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","color degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664","pantone_value degeri yanlis");
        softAssert.assertEquals(jsonPath.getInt("support.url"),"https://reqres.in/#support-heading", "url degeri yanlis");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!", "text degeri yanlis");

        softAssert.assertAll(); // !!! UNUTMUYORUZ...
     /* iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini
             sagliyoruz.
             Eger islemin sonunda softAssert.assertAll() kullanmaz isek taleplerimiz hatalı bile olsa testimiz pass olacaktir.
     */

    }

}
