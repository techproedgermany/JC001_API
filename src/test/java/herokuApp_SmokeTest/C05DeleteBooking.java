package herokuApp_SmokeTest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuApp_SmokeTest.C01PostBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.GetIdHerokuApp.getId;

public class C05DeleteBooking extends HerokuAppBaseUrl {

        /*
        Given
            https://restful-booker.herokuapp.com/booking/{{bookingid}}
        When
            User sends DELETE request
        Then
           Status code is 201
        And
           Response body is like: "Created"

         */

@Test
    public void C05DeleteBooking(){

    spec.pathParams("p1","booking","p2", bookingid);

    String expectedData = "Created";

    Response response = given(spec).delete("{p1}/{p2}");
    response.prettyPrint();

    response.then().statusCode(201);

    String actualData = response.asString(); //de-serialization

    assertEquals(expectedData,actualData);
}




}
