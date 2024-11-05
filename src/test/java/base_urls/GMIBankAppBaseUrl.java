package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationGMIBank.generateToken;

public class GMIBankAppBaseUrl {

    protected RequestSpecification spec;

    @Before  // This annotation will run before each test method
    public void setUp() {
        String baseUrl = "https://gmibank.com";
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Authorization", "Bearer " + generateToken())
                .setContentType(ContentType.JSON).build();
    }
}
