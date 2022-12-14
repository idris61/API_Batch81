package A0_Api_Tekrar.post_request_DATA_OLUSTURMAK;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q04Pojo extends RestfulBaseUrl {
        /*
        Given
         1)  https://restful-booker.herokuapp.com/booking
         2)   {
               "firstname": "Ali",
               "lastname": "Can",
               "totalprice": 999,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2021-09-21",
                   "checkout": "2021-12-21"
                },
                "additionalneeds": "Breakfast"
            }
        When
            I send POST Request to the URL
        Then
            Status code is 200
        And
            Response body is like {
                                 "bookingid": 16,
                                 "booking" :{
                                       "firstname": "Ali",
                                       "lastname": "Can",
                                       "totalprice": 999,
                                       "depositpaid": true,
                                       "bookingdates": {
                                           "checkin": "2021-09-21",
                                           "checkout": "2021-12-21"
                                       },
                                       "additionalneeds": "Breakfast"
                                    }
                                 }
    */

    @Test
    public void testQ04(){

        // 1- Set the Url
        spec.pathParam("first", "booking");

        // 2- Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo expectedData = new BookingPojo("Ali","Can",999,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        // 3- Send the Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        //response.prettyPrint();

        // 4- Do assertion
        BookingResponseBodyPojo actualData = response.as(BookingResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);
        /*
        {"bookingid": 16,
         "booking" :{     ==>   verileri farkl?? level da oldu??u i??in;
         BookingPojo actualData = response.as(BookingPojo.class); ==> bu obje ??al????mad??, sonu??lar null d??nd??.
         BookingResponseBodyPojo class'??n?? olu??turarak response body'i bu class'a s????d??rd??k.(bu ??ekilde "bookingid": 16
         datas?? pojo class'??m??za dahil oldu).
         actualData da 2 level olmu?? oldu.
         */

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
    }
}
