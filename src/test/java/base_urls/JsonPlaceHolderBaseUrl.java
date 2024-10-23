package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    // This class is created to prevent repetition of pre-conditions (urls, content-type, header, token etc..)

    protected RequestSpecification spec;

    @Before     // This annotation will run before each test method
    public void setUp(){

        String baseUrl = "https://jsonplaceholder.typicode.com";
        spec = new RequestSpecBuilder().setBaseUri(baseUrl).setContentType(ContentType.JSON).build();
    }

}
