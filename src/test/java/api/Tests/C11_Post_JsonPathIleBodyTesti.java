package api.Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class C11_Post_JsonPathIleBodyTesti {

    @Test
    public void test01() {
//https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request gonderdigimizde
//{
//"firstname" : "Ahmet", "lastname" : “Bulut", "totalprice" : 500, "depositpaid" : false, "bookingdates" : {
//"checkin" : "2023-01-10",
//"checkout" : "2023-01-20" },
//"additionalneeds" : "wi-fi" }
//donen Response’un,
//status code’unun 200,
//ve content type’inin application-json, ve response body’sindeki
//"firstname“in,"Ahmet",
//ve "lastname“in, "Bulut",
//ve "totalprice“in,500,
//ve "depositpaid“in,false,
//ve "checkin" tarihinin 2023-01-10 ve "checkout" tarihinin 2023-01-20 ve "additionalneeds“in,"wi-fi"
//oldugunu test edin

        //1- her zaman end point ve request body olustur.
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();
        JSONObject rezervasyonTarihleri = new JSONObject();
        rezervasyonTarihleri.put("checkin", "2023-01-10");
        rezervasyonTarihleri.put("checkout", "2023-01-20");
        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezervasyonTarihleri);
        requestBody.put("additionalneeds", "wi-fi");
        System.out.println(requestBody);

        //2- expected response body olustur
        //3- requesti gönder ve responsu kaydet

        Response response = given().contentType(ContentType.JSON).when().body(requestBody.toString()).post(url);

        //4- assertion
        //JSON Path kullanımı!
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("booking.firstname", equalTo("Ahmet")
                        , "booking.lastname", equalTo("Bulut")
                        , "booking.totalprice", equalTo(500)
                        , "booking.bookingdates.checkin", equalTo("2023-01-10")
                        , "booking.bookingdates.checkout", equalTo("2023-01-20"),
                        "booking.additionalneeds", equalTo("wi-fi"));

    }
}
