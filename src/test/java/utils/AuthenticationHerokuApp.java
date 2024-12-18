package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.BookingDatesPojo;
import pojos.HerokuAppPojo;

import static io.restassured.RestAssured.given;

public class AuthenticationHerokuApp {


    public static String generateToken(){
        String credentials = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        Response response = given().contentType(ContentType.JSON).body(credentials).post("https://restful-booker.herokuapp.com/auth");
        return response.jsonPath().getString("token");
    }
}
