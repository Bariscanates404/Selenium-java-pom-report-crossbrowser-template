package api.Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C09_Get_TestYaparkenTekrarlardanKurtulma {

    @Test
    public void test01() {

        //https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde donen Response’un,
        //status code’unun 200,
        //ve content type’inin application-json, ve response body’sindeki
        //"firstname“in, "Jim",
        //ve "lastname“in, "Jackson",
        //ve "totalprice“in, 1000 den kucuk oldugu,
        //ve "depositpaid“in, true,
        //ve "additionalneeds“in, "Breakfast"
        //oldugunu test edin

        //1- url ve response body olustur
        String url = "https://restful-booker.herokuapp.com/booking/10";
        //2- expected data olustur

        //3- request gönderip dönen response,ı kontrol et
        Response response = given().when().get(url);


        //4- assertipons

        //1. yontem ↓
        /*
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("firstname", Matchers.equalTo("Jim"))
                .body("totalprice",Matchers.lessThan(1000))
                .body("depositpaid", Matchers.equalTo(true))
                .body("additionalneeds", Matchers.equalTo("Breakfast"));
        */

        //2. yontem ↓
        response
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("firstname", equalTo("Eric"),
                        "totalprice", lessThan(1000),
                        "depositpaid", equalTo(false),
                        "additionalneeds", equalTo("Breakfast")
                );


    }
}
