package A2_post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Pojo extends JsonplaceholderBaseUrl {
    /*
        Given
          1) https://jsonplaceholder.typicode.com/todos
          2) {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
              }


           I send POST Request to the Url
       Then
           Status code is 201
       And
           response body is like  {
                                   "userId": 55,
                                   "title": "Tidy your room",
                                   "completed": false,
                                   "id": 201
                                   }
    */
    @Test
    public void post05ObjectMapper() {
        //Set the Url
        spec.pathParam("first", "todos");

        //Set the Expected Data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55, "Tidy your room", false); // pojo class'dan obje oluşturarak parametrelerimizi giriyoruz.
                                                                                                                   // expectedData mızı (payload) oluşturuyoruz.
        System.out.println("expectedData = " + expectedData);

        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}"); // Post request yaptık, bize Response döndürecek.
        response.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        // ObjectMapperUtils classından convertJsonToJava reausable methodunu kullanarak, response'ı asString() methoduyla stringe çevirerek mothodun içine yerleştirdik.
        // ActualDatayı ==> Expected datayla aynı class olan ==> JsonPlaceHolderPojo.class'a çevirmesini istedik.
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
    }
}
