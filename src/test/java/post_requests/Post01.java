package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
     Given
     1) https://jsonplaceholder.typicode.com/todos
     2)  {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false
        }
     3) Content type should be json
  When
      I send POST Request to the Url


  Then
      Status code is 201
  And
      response body is like
                   {
                      "userId": 55,
                      "title": "Tidy your room",
                      "completed": false,
                      "id": 201
                    }


NOTE: for payload {"userId": 55, "title": "Tidy your room", "completed": false}

     */

@Test
    public void post01(){

    //Set the URL
    spec.pathParam("first", "todos");

    //Set the expected data ( payload => the data that you want to transfer to the server)
    // use String to define the payload
    String payload = "{\n" +
            "           \"userId\": 55,\n" +
            "           \"title\": \"Tidy your room\",\n" +
            "           \"completed\": false\n" +
            "        }";

    System.out.println("payload = " + payload);
    System.out.println("response:");

    // Send the post request and get the response
    Response response = given(spec).when().body(payload).post("{first}");
    response.prettyPrint();

    //Do assertion
    JsonPath jsonPath = response.jsonPath();

        // Check the status code
        assertEquals(201, response.statusCode());

        // Check the response body values
        assertEquals(55, jsonPath.getInt("userId"));
        assertEquals("Tidy your room", jsonPath.getString("title"));
        assertEquals(false, jsonPath.getBoolean("completed"));

    //NOTE: We don't assert ids because inreal life, the ids are generated by the system
    //      and the id will be different every time

}



}
