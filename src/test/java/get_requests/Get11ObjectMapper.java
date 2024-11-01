package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static test_data.JsonPlaceHolderTestData.stringBody;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Get11ObjectMapper extends JsonPlaceHolderBaseUrl {

        /*
        Given
                 https://jsonplaceholder.typicode.com/todos/198
             When
                  I send GET Request to the URL
              Then
                  Status code is 200
                  And response body is like {
                                             "userId": 10,
                                             "id": 198,
                                             "title": "quis eius est sint explicabo",
                                             "completed": true
                                           }




        {"userId": 10,"title": "quis eius est sint explicabo","completed": true}

         */

    @Test
    public void get11() {


        // Set the URL
        spec.pathParams("p1", "todos", "p2", "198");

        //Set the expected data
        //1st way: method call for map
        Map<String, Object> expectedData1 = jsonPlaceHolderMapper(10, "quis eius est sint explicabo", true);
        System.out.println("expectedData1 = " + expectedData1);
        //2nd way: Create object from POJO class
        JsonPlaceHolderPojo expectedData2 = new JsonPlaceHolderPojo(10, "quis eius est sint explicabo", true);
        System.out.println("expectedData2 = " + expectedData2);
        //3rd way: Using ObjectMapper and Map
//            String expectedDataStr = "{\n" +
//                    "                                             \"userId\": 10,\n" +
//                    "                                             \"id\": 198,\n" +
//                    "                                             \"title\": \"quis eius est sint explicabo\",\n" +
//                    "                                             \"completed\": true\n" +
//                    "                                           }";
//
//            String expectedDataStr = "{\"userId\": 10,+ \"id\": 198, \"title\": \"quis eius est sint explicabo\",\"completed\": true}";

        String expectedDataStr = stringBody(10, "quis eius est sint explicabo", true);
        System.out.println("expectedDataStr = " + expectedDataStr);

        Map<String, Object> expectedData3 = convertJsonToJava(expectedDataStr, HashMap.class);
        System.out.println("expectedData3 = " + expectedData3);

        //4th way: Using ObjectMapper and POJO
        JsonPlaceHolderPojo expectedData4 = convertJsonToJava(expectedDataStr, JsonPlaceHolderPojo.class);
        System.out.println("expectedData4 = " + expectedData4);

        /*
        NOTE: WE SET THE EXPECTED DATA IN VARIOUS WAYS TO PRACTICE -- YOU CAN USE WHICHEVER METHOD YOU PREFER
        In the market, ObjectMapper with POJO is the most PREFERRED METHOD
         */

        //Send the request and get the response
        Response response = given(spec).when().get("{p1}/{p2}");
        response.prettyPrint();

        //Do assertions
        //Deserialization using Object Mapper and Pojo
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData4.getUserId(), actualData.getUserId());
        assertEquals(expectedData4.getTitle(), actualData.getTitle());
        assertEquals(expectedData4.getCompleted(), actualData.getCompleted());

    }


}
