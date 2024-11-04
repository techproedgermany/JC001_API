package herokuApp_SmokeTest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;


public class C01PostBooking extends HerokuAppBaseUrl {
    public static int bookingid;

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
                {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        user sends POST request
    Then
        status code should be 200
    And
        body should be like:
                        {
                "bookingid": 4299,
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
            }
     */



    @Test
    public void co1PostBooking(){

        spec.pathParam("p1", "booking");

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuAppPojo payload = new HerokuAppPojo("Jim", "Brown", 111, true, bookingDates, "Breakfast" );
        System.out.println("payload = " + payload);

        Response response = given(spec).when().body(payload).post("{p1}");
        response.prettyPrint();

//        //ObjectMapper POJO
        HerokuResponsePojo actualData = convertJsonToJava(response.asString(), HerokuResponsePojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(payload.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(payload.getLastname(), actualData.getBooking().getLastname());
        assertEquals(payload.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(payload.getDepositpaid(), actualData.getBooking().getDepositpaid());
//        assertEquals(payload.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
//        assertEquals(payload.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payload.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        bookingid = response.jsonPath().getInt("bookingid");
        System.out.println("bookingid = " + bookingid);

//        // Extract bookingid directly
//        HerokuResponsePojo herokuResponse = response.as(HerokuResponsePojo.class);
//        bookingid = herokuResponse.getBookingid(); // Set bookingid for use in other classes
//        System.out.println("Generated booking ID: " + bookingid);
//
//        System.out.println("Booking ID after POST request: " + bookingid);
//        assert bookingid > 0 : "Booking ID was not properly set from POST response";

    }


}
