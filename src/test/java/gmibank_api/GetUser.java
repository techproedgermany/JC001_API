package gmibank_api;

import base_urls.GMIBankAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.gmibank_pojos.CountryPojo;
import pojos.gmibank_pojos.RootPojo;
import pojos.gmibank_pojos.UserPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class GetUser extends GMIBankAppBaseUrl {

    /*
            Given
              https://www.gmibank.com/api/tp-customers/110457
           When
             I send GET Request to the URL
          Then
             Status code is 200
             And response body is like {
            "id": 110457,
            "firstName": "Jeremy",
            "lastName": "O'Reilly",
            "middleInitial": "A",
            "email": "lane.kreiger@hotmail.com",
            "mobilePhoneNumber": "208-961-1605",
            "phoneNumber": "208-961-1605",
            "zipCode": "56163",
            "address": "96922 Schumm Port, North Emmitt, KS 04003",
            "city": "Port Morgan",
            "ssn": "704-72-7293",
            "createDate": "2021-11-28T21:00:00Z",
            "zelleEnrolled": false,
            "country": {
                "id": 24105,
                "name": "San Jose",
                "states": null
            },
            "state": "",
            "user": {
                "id": 110022,
                "login": "courtney.aufderhar",
                "firstName": "Jeremy",
                "lastName": "O'Reilly",
                "email": "lane.kreiger@hotmail.com",
                "activated": true,
                "langKey": "en",
                "imageUrl": null,
                "resetDate": null
            },`
        "accounts": []
              }
     */

    @Test
    public void getUser(){

        spec.pathParams("p1","api", "p2","tp-customers","p3","110457");

        Response response = given(spec).get("{p1}/{p2}/{p3}");
        response.prettyPrint();

        //Inner POJO for Country
        CountryPojo country = new CountryPojo(24105, "San Jose", null );

        //Inner POJO for User
        UserPojo user = new UserPojo(110022, "courtney.aufderhar", "Jeremy","O'Reilly","lane.kreiger@hotmail.com",true, "en",null,null);

        //Account List
        List<Object> accounts = new ArrayList<>();

        //Outer Pojo - Root
        RootPojo expectedData = new RootPojo(110457,"Jeremy", "O'Reilly", "A", "lane.kreiger@hotmail.com",
                                            "208-961-1605", "208-961-1605", "56163", "96922 Schumm Port, North Emmitt, KS 04003",
                                            "Port Morgan", "704-72-7293", "2021-11-28T21:00:00Z", false, country, "", user, accounts );
        System.out.println("expectedData = " + expectedData);

        //Deserialization ==> BEST PRACTICE ==> Object Mapper + POJO Combination
        RootPojo actualData = convertJsonToJava(response.asString(), RootPojo.class);
        System.out.println("actualData = " + actualData);

        //Assertions
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(), actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(), actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getPhoneNumber(), actualData.getPhoneNumber());
        assertEquals(expectedData.getZipCode(), actualData.getZipCode());
        assertEquals(expectedData.getAddress(), actualData.getAddress());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getSsn(), actualData.getSsn());
        assertEquals(expectedData.getCreateDate(), actualData.getCreateDate());
        assertEquals(expectedData.isZelleEnrolled(), actualData.isZelleEnrolled());

        //Country
        assertEquals(expectedData.getCountry().getName(), actualData.getCountry().getName());
        //...

        //User
        assertEquals(user.getId() , actualData.getUser().getId());
        assertEquals(expectedData.getUser().getLogin() , actualData.getUser().getLogin());
        //....

        //Accounts
        //assertEquals(expectedData.getAccounts(), actualData.getAccounts());
    }




}
