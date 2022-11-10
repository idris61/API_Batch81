package A0_Api_Tekrar.get_requests_DATALARI_OKUMAK;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Q01 {
        /*
       Given
           https://reqres.in/api/users/3
       When
           User sends a GET Request to the url
       Then
           HTTP Status Code should be 200
       And
           Content Type should be JSON
       And
           Status Line should be HTTP/1.1 200 OK
    */

    @Test
    public void test01(){

        // 1-Set The Url
        String url = "https://reqres.in/api/users/3";

        // 2-Set The Expected Data (Put, Post, Delete)

        // 3-Type code to send request
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4-Do assertions
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");



    }

}
