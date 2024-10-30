package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerokuAppBaseUrl {

    /*
    Given
       https://restful-booker.herokuapp.com/booking/1285
   When
       I send GET Request to the url
   Then
       Response body should be like that;
{
    "firstname": "Jane",
    "lastname": "Doe",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Extra pillows please"

     */

    @Test
    public void get10(){

        //Set the Url
        spec.pathParams("p1","booking","p2","1285");
        //Set the expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");

        HerokuAppPojo expectedData = new HerokuAppPojo("Jane","Doe",111, true, bookingDates, "Extra pillows please");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).when().get("{p1}/{p2}");
        response.prettyPrint();

        //Do assertion
        HerokuAppPojo actualData = response.as(HerokuAppPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());






    }



}
