package herokuApp_SmokeTest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import java.util.Map;

import static herokuApp_SmokeTest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerokuAppTestData.herokuAppMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04PartialUpdateBooking extends HerokuAppBaseUrl {
    /*
     Given
         https://restful-booker.herokuapp.com/booking/{{bookingid}}
    And
        {
            "firstname" : "Jane",
            "additionalneeds": "Extra pillows please"
        }
     When
        User sends PATCH request
     Then
         Status code is 200
     And
        Verify response body
     */


    // use MAP for payload
    // use POJO for expectedData
    // use OBJECT MAPPER with POJO for actualData

    @Test
    public void c04PartialUpdateBooking(){

        spec.pathParams("p1","booking","p2", bookingid);

        // use MAP for payload
        Map<String,Object> payload = herokuAppMapper("Jane",null,null,null,null, "Extra pillows please");

        //Send request get response
        Response response = given(spec).when().body(payload).patch("{p1}/{p2}");

        // use POJO for expectedData
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
        HerokuAppPojo expectedData = new HerokuAppPojo("Jane", "Brown", 111, true, bookingDates, "Extra pillows please" );

        // use OBJECT MAPPER with POJO for actualData
        HerokuAppPojo actualData = convertJsonToJava(response.asString(), HerokuAppPojo.class);  //De-serialization

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname() , actualData.getFirstname());
        assertEquals(expectedData.getLastname() , actualData.getLastname());
        assertEquals(expectedData.getTotalprice() , actualData.getTotalprice());
        assertEquals(expectedData.getBookingdates().getCheckin() , actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout() , actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds() , actualData.getAdditionalneeds());
    }
}
