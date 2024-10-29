package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {
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
    public void post02a(){  // JsonPath for assertion

        //Set the URL
        spec.pathParam("first", "todos");

        // Set the expected data ( payload => the data that you want to transfer to the server)
        // using MAP to define the payload
        // We can set the data using MAP => RECOMMENDED because we can make it more dynamic when it comes to assertions
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 55);
        payload.put("title", "Tidy your room");
        payload.put("completed", false);

        System.out.println("payload = " + payload);

        //Send request and get response
        Response response = given(spec).when().body(payload).post("{first}"); // At this point, we need to do SERIALIZATION
        //Serialization => We need serializer to convert Java object to JSON ==> Converting Java Object to JSON is called SERIALIZATION
        // Do to do serialization, add Jackson DataBind dependency in pom.xml file
        response.prettyPrint();

        //Do assertion
        // Use JsonPath to extract the data out or response body for assertion
        JsonPath jsonPath = response.jsonPath();
        assertEquals(201, response.statusCode());
        assertEquals(payload.get("userId"), jsonPath.getInt("userId"));
        assertEquals(payload.get("title"), jsonPath.getString("title"));
        assertEquals(payload.get("completed"), jsonPath.getBoolean("completed"));

    }


    @Test
    public void post02b(){      // De-serialization (MAP) for assertion
        //Set the URL
        spec.pathParam("first", "todos");

        // Set the expected data ( payload => the data that you want to transfer to the server)
        // using MAP to define the payload
        // We can set the data using MAP => RECOMMENDED because we can make it more dynamic when it comes to assertions
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 55);
        payload.put("title", "Tidy your room");
        payload.put("completed", false);

        System.out.println("payload = " + payload);

        //Send request and get response
        Response response = given(spec).when().body(payload).post("{first}"); // At this point, we need to do SERIALIZATION
        //Serialization => We need serializer to convert Java object to JSON ==> Converting Java Object to JSON is called SERIALIZATION
        // Do to do serialization, add Jackson DataBind dependency in pom.xml file
        response.prettyPrint();

        //Do assertion
        // For assertions, both data types should be uniform (same)
        // So we will change response body to a amap and then use it for assertions
        // Converting JSON to Java Object is called DE-SERIALIZATION

        Map<String, Object> actualData = response.as(HashMap.class); //De-serialization
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(payload.get("userId"), actualData.get("userId"));
        assertEquals(payload.get("title"), actualData.get("title"));
        assertEquals(payload.get("completed"), actualData.get("completed"));
    }
}
