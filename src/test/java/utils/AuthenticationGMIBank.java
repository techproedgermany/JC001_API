package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationGMIBank {


    public static String generateToken(){
        String credentials = "{\n" +
                "  \"password\": \"Mark.123\",\n" +
                "  \"rememberMe\": true,\n" +
                "  \"username\": \"mark_twain\"\n" +
                "}\n";

        Response response = given().contentType(ContentType.JSON).body(credentials).post("https://gmibank.com/api/authenticate");
        return response.jsonPath().getString("id_token");
    }
}
