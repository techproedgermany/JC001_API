package get_requests;

import base_urls.PetstoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get07 extends PetstoreBaseUrl {
    /*
      Given
        https://petstore.swagger.io/v2/pet/1098
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
               {
                "id": 1098,
                "category": {
                    "id": 0,
                    "name": "Bird"
                },
                "name": "Tweety",
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 0,
                        "name": "Pets"
                    },
                    {
                        "id": 0,
                        "name": "PrettyPaws"
                    }
                ],
                "status": "pending"
                }
     */


    @Test
    public void get07() {


        //Set the url
        spec.pathParams("p1", "pet", "p2", "1098");

        //Send the request and get the response
        Response response = given(spec).get("{p1}/{p2}");
        response.prettyPrint();

        //Do Assertion
        response.then().statusCode(200).contentType(ContentType.JSON);

        //1st way: Soft Assertion (using single body() method)
        // HOMEWORK

        //2nd way: Using JsonPath
            //Step 1: Creating the JsonPath object
                JsonPath jsonPath = response.jsonPath();
            //Step2: Asserting values with JsonPath
                assertEquals(1098, jsonPath.getInt("id"));
                assertEquals("Bird", jsonPath.getString("category.name"));
                assertEquals(0, jsonPath.getInt("category.id"));
                assertEquals("Tweety", jsonPath.getString("name"));
                assertEquals("string", jsonPath.getString("photoUrls[0]"));
                assertEquals("Pets",jsonPath.getString("tags[0].name"));
                assertEquals("PrettyPaws", jsonPath.getString("tags[1].name"));
                assertEquals("pending", jsonPath.getString("status"));
    }

}


















