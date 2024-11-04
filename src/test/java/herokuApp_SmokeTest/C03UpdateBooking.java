package herokuApp_SmokeTest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static herokuApp_SmokeTest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static utils.GetIdHerokuApp.getId;

public class C03UpdateBooking extends HerokuAppBaseUrl {
    /*
        Given
         https://restful-booker.herokuapp.com/booking/{{bookingid}}
    And
       "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
     When
        User sends PUT request
     Then
         Status code is 200
      And
         Response body is like: {
                              "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin" : "2024-01-01",
            "checkout" : "2024-01-01"
        },
        "additionalneeds": "Breakfast"
    }
     */

@Test
    public void c03PutRequest(){

    spec.pathParams("p1","booking","p2",getId());
    System.out.println("getId() = " + getId());

    BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
    HerokuAppPojo payload = new HerokuAppPojo("Jim", "Brown", 111, true, bookingDates, "Breakfast" );
    System.out.println("payload = " + payload);

   // Response response = given(spec).header("Cookie", "token=212c02fd74146da").when().body(payload).put("{p1}/{p2}");
   // Response response = given(spec).when().body(payload).put("{p1}/{p2}");

    Response response = given(spec).when().body(payload).put("{p1}/{p2}");
    response.prettyPrint();
}




}
