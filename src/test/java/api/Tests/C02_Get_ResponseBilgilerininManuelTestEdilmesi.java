package api.Tests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgilerininManuelTestEdilmesi {

    @Test
    public void test01() {

        String url="https://restful-booker.herokuapp.com/booking/10";


        Response response = given().when().get(url);

        System.out.println(response.getStatusCode());
        System.out.println(response.contentType());
        System.out.println(response.getHeader("Server"));
        System.out.println(response.getStatusLine());
        System.out.println(response.getTime());







    }


}
