package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos/72
      2)  {
            "userId": 4,
             "id": 72,
             "title": "Wash the dishes",
             "completed": false
            }
      3) Content type should be json
    When
       I send PUT Request to the Url


    Then
       Status code is 200
    And
       response body is like
                    {
                     "userId": 4,
                     "id": 72,
                     "title": "Wash the dishes",
                    "completed": false
                    }

     */


    @Test
    public void put01(){

        //SSet the Url
        spec.pathParams("p1", "todos", "p2", "72");

        // Set the expected data
        Map<String, Object> payload = jsonPlaceHolderMapper(null,"Wash the dishes", false);
        System.out.println("payload = " + payload);

        //Send the request and get the response
        Response response = given(spec).when().body(payload).put("{p1}/{p2}");  //Serialization
        response.prettyPrint();

        // Do assertion
        Map<String, Object> actualData = response.as(HashMap.class); //De-serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(payload.get("userId"),actualData.get("userId"));
        assertEquals(payload.get("title"),actualData.get("title"));
        assertEquals(payload.get("completed"),actualData.get("completed"));
    }
}
