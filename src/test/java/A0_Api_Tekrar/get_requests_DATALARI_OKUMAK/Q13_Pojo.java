package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.GoRestPojo;
import pojos.GorestDataPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q13_Pojo extends GoRestBaseUrl {
      /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                {
                    "meta": null,
                    "data": {
                        "id": 2508,
                        "name": "Rukmin Rana",
                        "email": "rana_rukmin@king.co",
                        "gender": "female",
                        "status": "active"
                    }
                }
    */

    @Test
    public void testQ12(){

        // 1- Set the Url
        spec.pathParams("first","users","second",2508);

        // 2- Set the Expected Data
        GorestDataPojo gorestDataPojo = new GorestDataPojo(2508,"Rukmin Rana","rana_rukmin@king.co","female","active");
        GoRestPojo expectedData = new GoRestPojo(null,gorestDataPojo);
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertios
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(gorestDataPojo.getId(),actualData.getData().getId());
        assertEquals(gorestDataPojo.getName(),actualData.getData().getName());
        assertEquals(gorestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(gorestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(gorestDataPojo.getStatus(),actualData.getData().getStatus());


    }
}
