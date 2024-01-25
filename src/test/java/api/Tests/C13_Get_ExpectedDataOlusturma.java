package api.Tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C13_Get_ExpectedDataOlusturma {

    @Test
    public void test01() {
        //https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request yolladigimizda donen response body’sinin asagida verilen ile ayni oldugunutest ediniz
        //Response body :
        //{
        //    "userId": 3,
        //"id": 22,
        //"title": "dolor sint quo a velit explicabo quia nam",
        //"body": "eos qui et ipsum ipsam suscipit autsed omnis non odioexpedita ear
        //}
        //um mollitia molestiae aut atque rem suscipitnam impedit esse"


        //1- end point ve request body hazırla
        String url = "https://jsonplaceholder.typicode.com/posts/2";


        //2- expected data olustur
        JSONObject expectedDataJson = new JSONObject();
        expectedDataJson.put("userId", 1);
        expectedDataJson.put("id", 2);
        expectedDataJson.put("title", "qui est esse");
        expectedDataJson.put("body", "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla");
        //3- responsu al ve kaydet
        Response response = given().when().get(url);
        response.prettyPrint();
        //4- assertions

        // eski yontem ile ↓
        response.then().assertThat().body("userId", equalTo(1)
                , "id", equalTo(2)
                , "title", equalTo("qui est esse")
                , "body", equalTo("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"));


        // yeni JsonPath yontemi ile ↓
        JsonPath responseJsonPath = response.jsonPath();
        Assert.assertEquals(expectedDataJson.get("userId"), responseJsonPath.getInt("userId"));

        Assert.assertEquals(expectedDataJson.get("id"), responseJsonPath.getInt("id"));

        Assert.assertEquals(expectedDataJson.get("body"), responseJsonPath.getString("body"));

        Assert.assertEquals(expectedDataJson.get("title"), responseJsonPath.getString("title"));

    }
}
