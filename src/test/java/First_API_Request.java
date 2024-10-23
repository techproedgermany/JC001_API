import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class First_API_Request {

    public static void main(String[] args) {
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


     //   System.out.println(given().when().get("https://petstore.swagger.io/v2/pet/1098")); //provides reference

     //   given().when().get("https://petstore.swagger.io/v2/pet/1098").prettyPrint();        // prints the actual body of the response


        Response response = given().when().get("https://petstore.swagger.io/v2/pet/1098");

        System.out.println("Status Code = " + response.statusCode());
        System.out.println("Status Line = " + response.statusLine());
        System.out.println("Response contentType = " + response.contentType());
        System.out.println("Response Time = " + response.getTime());
        System.out.println("Response Header = " + response.headers());
        System.out.println("Response Date: = " + response.header("Date"));
        System.out.println("Response Server: = " + response.header("Server"));



    }
}
