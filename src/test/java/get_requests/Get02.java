package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {

            /*
        Given
              https://restful-booker.herokuapp.com/booking/0
          When
              User send a GET Request to the url
          Then
              HTTP Status code should be 404
          And
              Status Line should be HTTP/1.1 404 Not Found
          And
              Response body contains "Not Found"
          And
              Response body does not contain "TechProEd"
          And
              Server is "Cowboy"
        */

    @Test
    public  void get02(){
        // 1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";

        //2. Set the expected data  --> do it later

        //3. Send the request and get the response

        Response response = given().get(url);       // when() method is OPTIONAL
        response.prettyPrint();     //prints the response on the console

        //4. Do assertion
        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server","Cowboy");

        // We can change the format of the response body using as..() method
        String responseBody = response.asString();

        // Response body contains "Not Found"
        assertEquals("Not Found", responseBody);
        assertTrue(responseBody.contains("Not Found"));

        // Response body does not contain "TechProEd"
        assertFalse(responseBody.contains("TechProEd"));
        assertNotEquals("TechProEd", responseBody);

        // Server is "Cowboy"
        String server = response.header("Server");
        System.out.println("server = " + server);
        assertEquals("Cowboy",server);
    }

















}
