package api.Tests;

import org.json.JSONObject;
import org.junit.Test;

public class C04_JsonObjesiOlusturma {

    @Test
    public void test01(){


        JSONObject obj1 =new JSONObject();
        obj1.put("title","Ahmet");
        obj1.put("body","Merhaba");
        obj1.put("userId",1);




    }
}
