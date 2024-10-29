package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerokuAppBaseUrl {

    /*
                Given
               https://restful-booker.herokuapp.com/booking/52
           When
               I send GET Request to the url
           Then
               Response body should be like that;
                 {
            "firstname": "Josh",
            "lastname": "Allen",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "super bowls"
        }

     */

    @Test
    public void get09() {

        //Step 1:Set the URL
        spec.pathParams("p1", "booking", "p2", "52");

        //Step 2: Set the expected data
        //Step 2.1: Inner JSON - bookingdates
        Map<String, String> bookingMap = HerokuAppTestData.bookingDatesMapper("2018-01-01", "2019-01-01");
        System.out.println("bookingMap = " + bookingMap);
        // Step 2.2: Outer JSON - expected data
        Map<String, Object> expectedData = HerokuAppTestData.herokuAppMapper("Josh", "Allen", 111, true, bookingMap, "super bowls");
        System.out.println("expectedData = " + expectedData);

        //Step 3: Send the get request and get the response
        Response response = given(spec).when().get("{p1}/{p2}");
        response.prettyPrint();

        //Step 4: Do assertions
        //Step 4.1: Deserializing the response
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        //Step 4.2: Performing assertions
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingMap.get("checkin"), ((Map) (actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingMap.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));


    }
}
