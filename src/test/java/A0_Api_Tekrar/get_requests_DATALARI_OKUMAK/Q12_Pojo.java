package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q12_Pojo extends RestfulBaseUrl {
         /*
        Given
            https://restful-booker.herokuapp.com/booking/19
        When
            I send GET Request to the URL
        Then
            Status code is 200
        And
            Response body is like:
                  {
                        "firstname": "Fabio",
                        "lastname": "Dominguez",
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
    public void testQ12(){

        // 1- Set the Url
        spec.pathParams("first","booking","second",19);

        // 2- Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Fabio","Dominguez",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        /*
         * inner pojo class'dan parametreli constructor kullanarak obje oluşturuyoruz. parametreleri constructor'a giriyoruz.("checkin", "checkout")
         * outor poje class'dan parametreli constructor kullanarak obje oluşturuyoruz. parametreleri giriyoruz. inner pojo'muzu da ilgili yere yazıyoruz.
         */

        // 3- Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        // 4- Do Assertions
        BookingPojo actualData = response.as(BookingPojo.class); ///POM.xml file'daki Gson ile; as() methodunu kullanarak, Json datayı ==> Bookingpojo data tipine çeviriyoruz.
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        // 1. yöntem
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());

        // 2. yöntem
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());



    }

}
