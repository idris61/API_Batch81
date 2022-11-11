package Odev;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev2_Pojo extends ReqresBaseUrl {
    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
    /*
            Given
                1) https://reqres.in/api/users
                2) {
                    "name": "morpheus",
                    "job": "leader"
                    }
            When
                I send POST Request to the Url
            Then
                Status code is 201
                And response body should be like {
                                                        "name": "morpheus",
                                                        "job": "leader",
                                                        "id": "395",
                                                        "createdAt": "2022-11-10T14:38:43.780Z"
                                                    }
    */

    @Test
    public void test02(){

        // 1- Set the Url
        spec.pathParam("first","users");

        // 2- Set the Expected Data
        ReqresPojo expectedData = new ReqresPojo("morpheus","leader");
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4- Do Assertion
        ReqresPojo actualData = response.as(ReqresPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());

    }
}

