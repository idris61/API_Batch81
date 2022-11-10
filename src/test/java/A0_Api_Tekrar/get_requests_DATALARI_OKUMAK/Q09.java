package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q09 extends RestfulBaseUrl {

        /*
      Given
          https://restful-booker.herokuapp.com/booking/91
      When
          I send GET Request to the url
      Then
          Response body should be like that;
        {
            "firstname": "Carlos",
            "lastname": "Vera",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }
   */
    @Test
    public void testQ09(){

        // 1- Set the Url
        spec.pathParams("first","booking", "second", 91);

        // 2- Set the Expected Data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");
        System.out.println("bookingdatesMap = " + bookingdatesMap);

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Carlos");
        expectedData.put("lastname","Vera");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println("expectedData = " + expectedData);


        // 3- Send the Request and the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); // actualData'mızı Map'e çevirdik.
        System.out.println("actualDataMap = " + actualData);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        /*
        actualData.get("bookingdates") ==> object olduğu için (Map<String,Object>) .get("checkin") yapmamıza izin vermiyor.
        (Map)actualData.get("bookingdates")).get("checkin") ==> objeyi Map'e çevirdikten sonra get yapmamıza izin verdi.
        Key-Value ikilileri String-Object şeklinde olduğundan, Bookingdata value kısmını casting ile Map yaptık.
         */
        assertEquals(bookingdatesMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
    }
}
