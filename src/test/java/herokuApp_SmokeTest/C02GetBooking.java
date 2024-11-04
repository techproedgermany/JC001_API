//package herokuApp_SmokeTest;
//
//import base_urls.HerokuAppBaseUrl;
//import io.restassured.response.Response;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//import pojos.BookingDatesPojo;
//import pojos.HerokuAppPojo;
//import pojos.HerokuResponsePojo;
//
//import static herokuApp_SmokeTest.C01PostBooking.bookingid;
//import static io.restassured.RestAssured.given;
//import static org.junit.Assert.assertEquals;
//import static utils.GetIdHerokuApp.getId;
//import static utils.ObjectMapperUtils.convertJsonToJava;
//
//
//
//public class C02GetBooking extends HerokuAppBaseUrl {
//
//    /*
//     Given
//        https://restful-booker.herokuapp.com/booking/372
//     When
//        User sends GET request
//     Then
//        Status code is 200
//     And
//        Response body is like:  {
//                "bookingid": 4299,
//                "booking": {
//                    "firstname": "Jim",
//                    "lastname": "Brown",
//                    "totalprice": 111,
//                    "depositpaid": true,
//                    "bookingdates": {
//                        "checkin": "2018-01-01",
//                        "checkout": "2019-01-01"
//                    },
//                    "additionalneeds": "Breakfast"
//                }
//            }
//     */
//
//
//
//    @Test
//    public void c02GetRequest(){
//
//
//
////        spec.pathParams("p1","booking","p2", getId());
////        System.out.println("getId() = " + getId());
//
//        spec.pathParams("p1","booking","p2", C01PostBooking.bookingid);
//        System.out.println("bookingid = " + C01PostBooking.bookingid);
//
//        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
//        HerokuAppPojo expectedData = new HerokuAppPojo("Jim","Brown", 111, true, bookingDates, "Breakfast" );
//
//        Response response = given(spec).when().get("{p1}/{p2}");
//        response.prettyPrint();
//
//        //ObjectMapper POJO
//        HerokuAppPojo actualData = convertJsonToJava(response.asString(), HerokuAppPojo.class);
//        System.out.println("actualData = " + actualData);
//        assertEquals(200, response.statusCode());
//        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
//        assertEquals(expectedData.getLastname(), actualData.getLastname());
//        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
//        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
////        assertEquals(payload.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
////        assertEquals(payload.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
//        assertEquals(bookingDates.getCheckin(), actualData.getBookingdates().getCheckin());
//        assertEquals(bookingDates.getCheckout(), actualData.getBookingdates().getCheckout());
//        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
//
//
//
//
//
//    }
//
//
//}



package herokuApp_SmokeTest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static herokuApp_SmokeTest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C02GetBooking extends HerokuAppBaseUrl {

    @Test
    public void c02GetRequest() {


            spec.pathParams("first", "booking", "second", bookingid);

            BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01", "2019-01-01");
            HerokuAppPojo expectedData = new HerokuAppPojo("Jim", "Brown", 111, true, bookingDates, "Breakfast");

            Response response = given(spec).when().get("{first}/{second}");
            response.prettyPrint();

            assertEquals(200, response.statusCode());

            HerokuAppPojo actualData = convertJsonToJava(response.asString(), HerokuAppPojo.class);
            assertEquals(expectedData.getFirstname(), actualData.getFirstname());
            assertEquals(expectedData.getLastname(), actualData.getLastname());
            assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
            assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
            assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
            assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
            assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());


        }

}
