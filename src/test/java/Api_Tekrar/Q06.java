package Api_Tekrar;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Q06 extends ReqresBaseUrl {
         /*
   Given
        https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
          ------------
          1) Durum kodu 200
          2) Tüm pantone_değerlerini yazdır
          3) Konsolda 3'ten büyük tüm kimlikleri yazdır
             3'ten büyük 3 kimlik olduğunu doğrula
          4) id leri 3'ten küçük olan tüm adları konsolda yazdır
             id leri 3'ten küçük olan isimler 2 tane olduğunu doğrula
          ------------
    */

    @Test
    public void test06(){

        // 1-Set the Url
        spec.pathParams("first","unknown");

        // 2-Set the Expected Data (Put, Post, Delete)

        // 3-Sent the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4-Do Assertion
            // 1) Durum kodu 200
        assertEquals(200,response.getStatusCode());

            // 2) Tüm pantone_değerlerini yazdır
        JsonPath jsonPath = response.jsonPath();
        System.out.println("pantone_values = " + jsonPath.getList("data.pantone_value"));

            // 3) Konsolda 3'ten büyük tüm kimlikleri yazdır
        List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("ids = " + ids); // ids = [4, 5, 6]

        // System.out.println("data.findAll{it.id>3} = " + jsonPath.getList("data.findAll{it.id>3}"));
        // (id si 3 den büyük olanların tüm bilgilerini verir.

        //    3'ten büyük 3 kimlik olduğunu doğrula
        assertEquals(3, ids.size());

            // 4) id leri 3'ten küçük olan tüm adları konsolda yazdır
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("names = " + names); // names = [cerulean, fuchsia rose]

            //    id leri 3'ten küçük olan isimler 2 tane olduğunu doğrula
        assertEquals(2,names.size());
    }

}
