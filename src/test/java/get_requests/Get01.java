package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
        given() : where we set up the details of our request
        when()  : This tells RestAssured, "Now send the request"
        get()   : HTTP GET method to request data from the server
        prettyPrint(): will print the response body to the console

            1. We use Postman for Manual tests of API
            2. We use Rest Assured Library for automation API
            3. To type automation script, follow these steps:
                a) we need to understand the requirement
                b) Write test scripts in Gherkin Language
                      Gherkin Language has four keywords:
                      i) Given: used for pre-conditions (url, authorisation, body, content type etc..)
                      ii) When: used for action ( method name => get, post, put, delete etc..)
                      iii) Then: used for assertions
                      iv) And: used for repeating any step (multiple use of keywords)
                c) Start writing our Automation Script
                    1. Set the URL
                    2. Set the expected data
                    3. Send the request and get the response
                    4. Do assertion
        */

                /*
                Given
                https://restful-booker.herokuapp.com/booking/1
            When
                User sends a GET Request to the URL
            Then
                HTTP Status Code should be 200
            And
                Content Type should be application/json
            And
                Status Line should be HTTP/1.1 200 OK

                 */

    @Test
    public void get01(){

        // 1. Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10";

        //2. Set the expected data  --> do it later

        //3. Send the request and get the response

        Response response = given().get(url);       // when() method is OPTIONAL
        response.prettyPrint();     //prints the response on the console

        System.out.println("Status Code = " + response.statusCode());
        System.out.println("Status Line = " + response.statusLine());
        System.out.println("Response contentType = " + response.contentType());

        //4. Do assertion
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 OK");
    }









}
