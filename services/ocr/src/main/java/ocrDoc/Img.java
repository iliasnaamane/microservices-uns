package ocrDoc;

import org.json.JSONArray;
import org.json.JSONObject;


class Img {



    static JSONObject price1(JSONObject input){
        return input.put("remboursement", 1);
    }

    static JSONObject price2(JSONObject input){
        return input.put("remboursement", 10);
    }

    static JSONObject price3(JSONObject input){
        return input.put("remboursement", 100);
    }  



}