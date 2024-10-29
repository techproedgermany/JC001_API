package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Patch01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
      1) https://jsonplaceholder.typicode.com/todos/72
      2)  {
             "title": "Read your storybook"
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
                     "title": "Read your storybook",
                    "completed": false
                    }
     */



    @Test
    public void patch01(){

        //Set the URL
        spec.pathParams("p1", "todos","p2","72");

        // Set the expected data ( payload => the data that you want to transfer to the server)
        Map<String, Object> payload = jsonPlaceHolderMapper(null,"Read your storybook",null);
        System.out.println("payload = " + payload);

        //Send the request and get the response
        Response response = given(spec).when().body(payload).patch("{p1}/{p2}");  //Serialization
        response.prettyPrint();

        // Do assertion
        Map<String, Object> actualData = response.as(HashMap.class); //De-serialization
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(4,actualData.get("userId"));
        assertEquals(72,actualData.get("id"));
        assertEquals("Read your storybook",actualData.get("title"));
        assertEquals(false,actualData.get("completed"));
    }

}
