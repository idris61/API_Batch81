package Api_Tekrar;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Odev02 extends GoRestBaseUrl {


            /*
          Given
              https://gorest.co.in/public/v1/users/2986
          When
              User send GET Request to the URL
          Then
              Status Code should be 200
          And
              Response body should be like
           {
            "meta": null,
            "data": {
                "id": 2986,
                "name": "Prof. Bala Pilla",
                "email": "pilla_prof_bala@zemlak.io",
                "gender": "female",
                "status": "active"
            }
        }
         */

    @Test
    public void odev02() {

        // 1-Set the Url
        spec.pathParams("first", "users", "second", 2986);

        // 2-Set The Expected Data

        // 3-Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        // 4-Do Assertion
        assertNull(jsonPath.getString("meta"));
        assertEquals("Prof. Bala Pilla", jsonPath.getString("data.name"));
        assertEquals("pilla_prof_bala@zemlak.io", jsonPath.getString("data.email"));
        assertEquals("female", jsonPath.getString("data.gender"));
        assertEquals("active", jsonPath.getString("data.status"));
        assertEquals(response.statusCode(), 200);
    }
}
