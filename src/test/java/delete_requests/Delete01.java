package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
            Given
                https://jsonplaceholder.typicode.com/todos/198
            When
           I send DELETE Request to the Url
        Then
           Status code is 200
           And Response body is { }
     */

    @Test
    public void delete01(){

        //Set the Url
        spec.pathParams("p1","todos","p2", 198);

        //Set the expected data
        Map<String, Object> expectedData = jsonPlaceHolderMapper(null,null,null);
        System.out.println("expectedData = " + expectedData);

        //Send the delete request and get the response
        Response response = given(spec).when().delete("{p1}/{p2}");
        response.prettyPrint();

        //So assertions
        assertEquals(200,response.statusCode());
        Map<String,Object> actualData = convertJsonToJava(response.asString(), HashMap.class);
        //1st way:
        assertEquals(null,actualData.get(0));
        //2nd way:
        assertTrue(actualData.isEmpty());
        //3rd way:
        assertEquals(expectedData,actualData);
    }








}
