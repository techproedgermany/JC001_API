package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static io.restassured.RestAssured.given;

public class GetIdHerokuApp {

    public static int getId(){

        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuAppPojo payload = new HerokuAppPojo("Jim", "Brown", 111, true, bookingDates, "Breakfast" );
        System.out.println("payload = " + payload);

        Response response = given().given().contentType(ContentType.JSON).when().body(payload).post("https://restful-booker.herokuapp.com/booking");
        return response.jsonPath().getInt("bookingid");

    }

    public static void main(String[] args) {
        System.out.println("getId() = " + getId());
    }
}
