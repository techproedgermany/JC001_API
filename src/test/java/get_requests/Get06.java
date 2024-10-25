package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerokuAppBaseUrl {

    /*
     Given
            https://restful-booker.herokuapp.com/booking/13
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                "firstname": "John",
                "lastname": "Smith",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get06(){
        // Set the URL
        spec.pathParams("p1","booking", "p2", "13");

        //Send the request and get the response
        Response response = given(spec).get("{p1}/{p2}");         // syntax for more than one path param => "{}/{}"
        response.prettyPrint();

        // Assertion
        // 1st way: using matchers (equalTo() or is() method)
            // with equalTo()
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("John"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Breakfast"));


            //is() method
            response.then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("firstname", is("John"),
                            "lastname", is("Smith"),
                            "totalprice", is(111),
                            "depositpaid", is(true),
                            "bookingdates.checkin", is("2018-01-01"),
                            "bookingdates.checkout", is("2019-01-01"),
                            "additionalneeds", is("Breakfast"));

            /*
            NOTE: We have multiple ways of extracting the data out of the response body;
                i) asString() method => changes data type of response to String
                ii) JsonPath
                iii) Maps
                iv) Pojo Class
             */

            //2nd way: Using JsonPath

               //Step 1: Creating the JsonPath object
                JsonPath jsonPath = response.jsonPath();

               //Step2: Extracting values with JsonPath
                String firstName = jsonPath.getString("firstname");
                String lastName = jsonPath.getString("lastname");
                int totalPrice = jsonPath.getInt("totalprice");
                boolean depositPaid = jsonPath.getBoolean("depositpaid");
                String checkinDate = jsonPath.getString("bookingdates.checkin");
                String checkoutDate = jsonPath.getString("bookingdates.checkout");
                String additionalNeeds = jsonPath.getString("additionalneeds");

                //Step3: Asserting values with JsonPath
                assertEquals("John", firstName);   // assertEquals("John", jsonPath.getString("firstname"));
                assertEquals("Smith", lastName);
                assertEquals( 111, totalPrice);
                assertEquals(true, depositPaid);
                assertEquals("2018-01-01", checkinDate);
                assertEquals("2019-01-01", checkoutDate);
                assertEquals("Breakfast", additionalNeeds);
    }
}
