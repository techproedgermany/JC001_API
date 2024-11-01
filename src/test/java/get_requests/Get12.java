package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get12 extends GoRestBaseUrl {

    /*
            Given
          https://gorest.co.in/public/v1/users
        When
          User send GET Request
        Then
          The value of "pagination limit" is 10
        And
          The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
          The number of users should  be 10
        And
          We have at least one "active" status
        And
          "Yogendra Mahajan", "Prof. Gobinda Menon", "Jagdeep Guneta" are among the users
        And
          The female users are less than or equals to male users
             */

    @Test
    public void get12(){
        //Set  the url
        spec.pathParam("p1","users");

        // Set the expected data
        // Send request get response
        Response response=given(spec).when().get("{p1}");

        response.prettyPrint();

        // Do assertions
        JsonPath jsonPath=response.jsonPath();
        // The value of "pagination limit" is 10

        assertEquals(10,jsonPath.getInt("meta.pagination.limit"));

        //The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        String currentLink="https://gorest.co.in/public/v1/users?page=1";
        assertEquals(currentLink,jsonPath.getString("meta.pagination.links.current"));

        //The number of users should  be 10
        assertEquals(10,jsonPath.getList("data").size() );
        System.out.println("actual users amount = " + jsonPath.getList("data").size());

        //  We have at least one "active" status
        Boolean isExist = jsonPath.getList("data.status").contains("active");
        assertTrue(isExist);

        // "Balagovind Saini", "Gov. Agasti Sinha", "Mayoor Pandey" are among the users
        List<String> userNameList = jsonPath.getList("data.name");
        System.out.println("userNameList = " + userNameList);
        assertTrue(userNameList.contains("Balagovind Saini"));
        assertTrue(userNameList.contains("Gov. Agasti Sinha"));
        assertTrue(userNameList.contains("Mayoor Pandey"));

        // The female users are less than or equals to male users
        List<String> maleList = jsonPath.getList("data.findAll{it.gender == 'male'}");
        int maleUsers = maleList.size();
        System.out.println("maleUsers = " + maleUsers);

        int actualUsers = jsonPath.getList("data.id").size(); //10
        System.out.println("actualUsers = " + actualUsers);

        int femaleList = actualUsers - maleUsers;
        assertTrue(femaleList <= maleUsers);





    }



}
