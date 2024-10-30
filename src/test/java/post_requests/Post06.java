package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends HerokuAppBaseUrl {
    /*
    Given
 1)  https://restful-booker.herokuapp.com/booking
 2)   {
       "firstname": "John",
       "lastname": "Doe",
       "totalprice": 999,
       "depositpaid": true,
       "bookingdates": {
           "checkin": "2021-09-21",
           "checkout": "2021-12-21"
        },
        "additionalneeds": "Breakfast"
       }
When
    I send POST Request to the URL
Then
    Status code is 200
And
    Response body is like {
                            "bookingid": 4319,
                            "booking" :{
                               "firstname": "John",
                               "lastname": "Doe",
                               "totalprice": 999,
                               "depositpaid": true,
                               "bookingdates": {
                                   "checkin": "2021-09-21",
                                   "checkout": "2021-12-21"
                               },
                               "additionalneeds": "Breakfast"
                            }
                         }
     */

    @Test
    public void post06(){

        //Set the Url
        spec.pathParam("p1","booking");
        //Set the expected data
            // prepare data for inner json first (using pojo)
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21","2021-12-21");
            // prepare data for outer json (using pojo)
        HerokuAppPojo payload = new HerokuAppPojo("John","Doe",999,true,bookingDates,"Breakfast" );
        System.out.println("payload = " + payload);
        //Send the request and get the response
        Response response = given(spec).when().body(payload).post("{p1}");
        response.prettyPrint();
        //Do assertion

        // NOTE: We created a separate POJO class to accommodate response because response body has two key:value pairs => bookingid and booking
        // All values/variables in HerokuAppPojo are the values of one key called "booking" in response body

        HerokuResponsePojo actualData = response.as(HerokuResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(payload.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(payload.getLastname(), actualData.getBooking().getLastname());
        assertEquals(payload.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(payload.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDates.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
      //  assertEquals(payload.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin() );
        assertEquals(payload.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());







    }
}
//Set the Url
//Set the expected data
//Send the request and get the response
//Do assertion