package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import org.junit.Test;

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
    public void get04(){
        //Set the url
        // String url = "https://jsonplaceholder.typicode.com/todos";       // not recommended
        // We set baseurl as precondition in Specification class under base_urls package and extend this class to have access to those specifications

   // spec.pathParam("first", "todos");  ==>> "https://jsonplaceholder.typicode.com/todos"

        spec.accept(ContentType.JSON).pathParam("first", "todos");






    }










}
