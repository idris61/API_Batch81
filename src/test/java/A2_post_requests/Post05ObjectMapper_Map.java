package A2_post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonplaceholderBaseUrl {
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
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
    /*
    BU CLASS'TA YAPTIĞIMIZ İŞLEM;
   {"userId": 55, "title": "Tidy your room", "completed": false }  ==> datasını;
        * JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData(); methodu ile ==> String'e çevirdik (String jsonInString)
        * HashMap expectedData =   new ObjectMapper().readValue(jsonInString, HashMap.class); ==> String datayı kullanarak HashMap oluşturuyoruz.
        * Bu HashMap ==> BİZİM EXPECTED DATAMIZ OLUYOR.
   STRING DATAYI OBJEMAPPER İLE MAP'E ÇEVİRİP EXPECTED DATAMIZI OLUŞTURDUK. POST YAPTIK, SONRASINDA
   RESPONSE'TEN GELENİ DE MAP'E ÇEVİRİP KARŞILAŞTIRDIK...
     */
    @Test
    public void post05ObjectMapper() throws IOException {
        //Set the Url
        spec.pathParam("first","todos");

        //Set the Expected Data

        /*
        String jsonInString = "{\n" +
                "                                    \"userId\": 55,\n" +
                "                                    \"title\": \"Tidy your room\",\n" +
                "                                    \"completed\": false,\n" +
                "                                    \"id\": 201\n" +
                "                                    }";
         */
        // yorum içerisindeki görüntü ekranda olamsın diye JsonPlaceHolderTestData class'ında method oluşturduk.

        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        String jsonInString = obj.expectedDataInString(55,"idy your room",false);

        HashMap expectedData =   new ObjectMapper().readValue(jsonInString, HashMap.class); //(Map<String,Object> expectedData = new ObjectMapper().readValue(jsonInString, HashMap.class);)
        System.out.println("expectedData = " + expectedData);

        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Do Assertion
        HashMap actualData = new ObjectMapper().readValue(response.asString(),HashMap.class); // (Map<String, Object> actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        // response'ı String olarak alıp ==> HashMap'e çevirdik
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));

    }
}