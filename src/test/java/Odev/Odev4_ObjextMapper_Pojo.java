package Odev;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.ReqresPatchNamePojo;
import pojos.ReqresPojo;
import test_data.ReqresTestData;
import utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev4_ObjextMapper_Pojo extends ReqresBaseUrl {
    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void test04() throws IOException {

        // 1- Set the Url
        spec.pathParams("first", "users", "second", 2);

        // 2- Set the Expected Data
        ReqresPatchNamePojo expectedData = new ReqresPatchNamePojo("neo");
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        // 4- Do Assertion
        ReqresPatchNamePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),ReqresPatchNamePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());

    }
}
