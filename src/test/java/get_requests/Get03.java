package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 {

    /*
    Given
           https://jsonplaceholder.typicode.com/todos/23
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
       And
           “completed” is false
       And
           “userId” is 2

     */

    @Test
    public void get03(){
        // 1. Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos/23";

        // 2. Set the expected data
        // 3. Send the request and get the response
        Response response = given().get(url);
        response.prettyPrint();

        // 4. Do assertion

        //1st way: Hard Assertion
            // If you want your test to stop execution when it encounters an error, use Hard Assertion
            // Use separate body() methods for Hard Assertion

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)          // ContentType.JSON ==> “application/json”
                .body("title", equalTo("121213et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(3));


        //2nd way: Soft Assertion
            // If you want your test to continue execution even after it finds an error, use Soft Assertion
            // Use single body() method Soft Assertion
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)          // ContentType.JSON ==> “application/json”
                .body("title", equalTo("54874et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false), "userId", equalTo(3));

        //NOTE:
        // Hard Assertion stops the code/system straightaway as soon as it encounters any error
        // Soft Assertion does NOT stop the code/system straightaway, it allows to run all codes and print the error message at the end
        //Which one is better? ==> Depends on test type and test level
    }



}





















