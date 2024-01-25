package api.Tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JsonPathKullanimi {


    @Test
    public void test01() {
        JSONObject kisiBilgileriJsonObj = new JSONObject();
        JSONObject adresJsonObj = new JSONObject();


        JSONArray telefonNumralariArr = new JSONArray();

        JSONObject cepTelefonuJsonObj = new JSONObject();
        JSONObject evTelefonuJsonObj = new JSONObject();


        adresJsonObj.put("stressAddress", "naist street");
        adresJsonObj.put("city", "Nara");
        adresJsonObj.put("postalCode", "630-0192");

        cepTelefonuJsonObj.put("type", "iPhone");
        cepTelefonuJsonObj.put("number", "0123-4567-8888");
        evTelefonuJsonObj.put("type", "home");
        evTelefonuJsonObj.put("number", "0123-4567-8910");

        telefonNumralariArr.put(cepTelefonuJsonObj);
        telefonNumralariArr.put(evTelefonuJsonObj);

        kisiBilgileriJsonObj.put("firstName", "Jhon");
        kisiBilgileriJsonObj.put("lastName", "Doe");
        kisiBilgileriJsonObj.put("age", 26);
        kisiBilgileriJsonObj.put("address", adresJsonObj);
        kisiBilgileriJsonObj.put("phoneNumbers", telefonNumralariArr);

        System.out.println(kisiBilgileriJsonObj);
        System.out.println("firstname: " + kisiBilgileriJsonObj.get("firstName"));
        System.out.println("cadde: " + kisiBilgileriJsonObj.getJSONObject("address").get("stressAddress"));
        System.out.println("city: " + kisiBilgileriJsonObj.getJSONObject("address").get("city"));
        System.out.println("cep tel no: " + kisiBilgileriJsonObj.getJSONArray("phoneNumbers"));
    }
}
