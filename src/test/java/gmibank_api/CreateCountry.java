package gmibank_api;

import base_urls.GMIBankAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPojo;
import pojos.StatePojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class CreateCountry extends GMIBankAppBaseUrl {
    /*
    Given
   https://gmibank.com/api/tp-countries
And
   {
       "id": null,
       "name": "Banana Republic",
       "states": [
           {
               "id": 0,
               "name": "Apple",
               "tpcountry": null
           },
           {
               "id": 1,
               "name": "Orange",
               "tpcountry": null
           },
           {
               "id": 2,
               "name": "Peach",
               "tpcountry": null
           }
       ]
   }
When
   Send POST request
Then
   Status code is 201
And
   Response body is like:
   {
       "id": 189865,
       "name": "Banana Republic",
       "states": [
           {
               "id": 0,
               "name": "Apple",
               "tpcountry": null
           },
           {
               "id": 1,
               "name": "Orange",
               "tpcountry": null
           },
           {
               "id": 2,
               "name": "Peach",
               "tpcountry": null
           }
       ]
   }

     */

    @Test
    public void createCountry(){

        spec.pathParams("p1","api","p2","tp-countries");

        //inner data
        StatePojo state1 = new StatePojo(0, "Apple", null);
        StatePojo state2 = new StatePojo(1, "Orange", null);
        StatePojo state3 = new StatePojo(2, "Peach", null);

        List<StatePojo> stateList = new ArrayList<>();
        stateList.add(state1);
        stateList.add(state2);
        stateList.add(state3);
        System.out.println("stateList = " + stateList);

        //outer data
        CountryPojo payload = new CountryPojo(null,"Banana Republic", stateList);

        Response response = given(spec).body(payload).post("{p1}/{p2}");
        response.prettyPrint();

        //Assertions
        // 1st validation: response.then().body()
        // 2nd validation: jsonPath()
        // 3rd validation: as() method converting response to MAP
        // 4th validation: as() method converting response to POJO
        // 5th validation: BEST PRACTICE ==> Object Mapper + POJO Combination
        assertEquals(201, response.statusCode());

        CountryPojo actualData = convertJsonToJava(response.asString(), CountryPojo.class);
        assertEquals(payload.getName(), actualData.getName());
        assertEquals(payload.getStates().get(0).getId(), actualData.getStates().get(0).getId());
        assertEquals(payload.getStates().get(0).getName(), actualData.getStates().get(0).getName());
        assertEquals(payload.getStates().get(0).getTpcountry(), actualData.getStates().get(0).getTpcountry());

        assertEquals(payload.getStates().get(1).getId(), actualData.getStates().get(1).getId());
        assertEquals(payload.getStates().get(1).getName(), actualData.getStates().get(1).getName());
        assertEquals(payload.getStates().get(1).getTpcountry(), actualData.getStates().get(1).getTpcountry());

        assertEquals(payload.getStates().get(2).getId(), actualData.getStates().get(2).getId());
        assertEquals(payload.getStates().get(2).getName(), actualData.getStates().get(2).getName());
        assertEquals(payload.getStates().get(2).getTpcountry(), actualData.getStates().get(2).getTpcountry());







    }











}
