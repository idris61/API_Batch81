package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q15 extends RestfulBaseUrl {
        /*
            Given
                    https://restful-booker.herokuapp.com/booking/874
            When
                    I send GET Request to the URL
            Then
                    Status code is 200
                    {
                        "firstname": "Sally",
                        "lastname": "Brown",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2013-02-23",
                            "checkout": "2014-10-23"
                        },
                        "additionalneeds": "Breakfast"
                    }
         */

    @Test
    public void testQ15(){

        // 1- Set the Url
        spec.pathParams("first","booking","second",874);

        // 2- Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2013-02-23","2014-10-23");
        BookingPojo expectedData = new BookingPojo("Sally","Brown",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);
        System.out.println("actualData = " + actualData);

                // Soft Assertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),expectedData.getBookingdates().getCheckin());
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),expectedData.getBookingdates().getCheckout());
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());

        softAssert.assertAll(); // !!! UNUTMUYORUZ...

                // Hard Assertion
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}
