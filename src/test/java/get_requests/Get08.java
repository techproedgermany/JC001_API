package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
     Given
             https://jsonplaceholder.typicode.com/todos
      When
           I send GET Request to the URL
      Then
           1)Status code is 200
           2)Print all ids greater than 190 on the console
             Assert that there are 10 ids greater than 190
           3)Print all userIds whose ids are less than 5 on the console
             Assert that the number of userIds whose ids are less than 5 is 4
           4)Print all titles whose ids are less than 5
             Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

        @Test
            public void get08(){

        // Set the url
            spec.pathParam("p1", "todos");
        // Send the request and get the response
            Response response = given(spec).get("{p1}");
           // response.prettyPrint();

        // Do Assertion
        //1)Status code is 200
            // response.then().statusCode(200);
            //or
            assertEquals(200, response.statusCode());

            //Extract the data out of the response body using JsonPath
            JsonPath jsonPath = response.jsonPath();

            // 1st way: Using list and loop
        //2)Print all ids greater than 190 on the console
                    //Step 1: Extract the List of all IDs from the response
                        List<Integer> idList = jsonPath.getList("id");
                        System.out.println("idList = " + idList);
            //Assert that there are 10 ids greater than 190
                    //Step 2: Initialize an empty list to store IDs greater than 190
                        List<Integer> idsGreaterThan190 = new ArrayList<>();
                    //Step 3: Loop through each ID in the list and add IDs greater than 190 to the new list
                        for (int id: idList){
                            if (id > 190) {
                                idsGreaterThan190.add(id);
                            }
                        }
                    System.out.println("idsGreaterThan190 = " + idsGreaterThan190);
                    //Step4: Assert that there are exactly 10 IDs greater than 190
                        assertEquals(10, idsGreaterThan190.size());
                        System.out.println("idsGreaterThan190.size(): " + idsGreaterThan190.size());

            // 2nd way:  Using GROOVY LANGUAGE
            //Using Groovy to find all IDs greater than 190
                    List<Integer> idsGreaterThan190Groovy = jsonPath.getList("findAll{it.id > 190}.id");
                    System.out.println("idsGreaterThan190Groovy = " + idsGreaterThan190Groovy);

                    //Additional step:
                       // List<String> titleGroovy = jsonPath.getList("findAll{it.title = 'delectus aut autem' }.title");
                       //  System.out.println("titleGroovy = " + titleGroovy);

        //3)Print all userIds whose ids are less than 5 on the console
                    List<Integer> userIdsList = jsonPath.getList("findAll{it.id < 5}.userId");
                  //  System.out.println("userIdsList = " + userIdsList);
            //Assert that the number of userIds whose ids are less than 5 is 4
            assertEquals(4, userIdsList.size());
          //  System.out.println("userIdsList.size(): "+ userIdsList.size());

        // 4)Print all titles whose ids are less than 5
                List<String> titleList = jsonPath.getList("findAll{it.id < 5}.title");
              //  System.out.println("titleList = " + titleList);
            //Assert that "delectus aut autem" is one of the titles whose id is less than 5
            assertTrue(titleList.contains("delectus aut autem"));
        }


          /*
            NOTE: We have multiple ways of extracting the data out of the response body;
                i) asString() method => changes data type of response to String
                ii) JsonPath
                iii) Maps
                iv) Pojo Class
             */
}
