package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class Get04 extends JsonPlaceHolderBaseUrl {

    /*
             Given
                 https://jsonplaceholder.typicode.com/todos
             And
                 Accept type is “application/json” > >> Content type
             When
                  I send a GET request to the Url
             Then
                 HTTP Status Code should be 200
             And
                 Response format should be "application/json"  >> Content type
             And
                 There should be 200 todos
             And
                 "quis eius est sint explicabo" should be one of the todos title
             And
                 2, 7, and 9 should be among the userIds
     */

    @Test
    public void get04() {
        //Set the url
        // String url = "https://jsonplaceholder.typicode.com/todos";       // not recommended
        // We set baseurl as precondition in Specification class under base_urls package and extend this class to have access to those specifications

        // spec.pathParam("first", "todos");  ==>> "https://jsonplaceholder.typicode.com/todos"

        spec.accept(ContentType.JSON).pathParam("first", "todos");

        //2. Set the expected data  --> do it later
        //3. Send the request and get the response

        Response response = given(spec).when().get("{first}");          // ===> https://jsonplaceholder.typicode.com/todos
        response.prettyPrint();

        //4. Do assertion
        response.then()
                .statusCode(200)                       // Validate that the status code is 200
                .contentType(ContentType.JSON)           //Validate that the response is in JSON format
                .body("id", hasSize(200))             // Validate that there are 200 items in the response
                .body("title", hasItem("quis eius est sint explicabo"))     // Check if this specific title exist
                .body("userId", hasItems(2, 7, 9));       // Validate that these userIds exist in the response

        /*
        If response body returns collection (list) :
            i) check its size using hasSize() method
            ii) check if any item exists in that list using hasItem() method
            iii) check multiple items by using hasItems() method
         */
    }
}
