/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Processors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import esb.flows.implem.data.Flight.VolsRequest;
import esb.flows.implem.data.Flight.vol;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 *
 * @author iliasnaamane
 */
public class VolProcessors {
   public static Processor csv2volRequest = (Exchange exchange) -> {
            Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();
            VolsRequest p =  new VolsRequest(data);
          
            if(p.getOutbound_date()==null) 
            {  
                p.setOutbound_date("12-10-2017");
            }
          
            exchange.getIn().setBody(p);
    };
   
    public static Processor reqRestservA = (Exchange exchange) -> {
            VolsRequest p =  exchange.getIn().getBody(VolsRequest.class);
            Map<String,String> map = p.vol2Map();
            map.put("event", "Search");
            map.put("sortby", "Price");
            StringBuilder str = new StringBuilder();
            str.append("{");
            
            for(Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            str.append("\""+key +"\""+":"+"\""+value+"\""+",");
            }
            str.deleteCharAt(str.length()-1);
             str.append("}");
            

            exchange.getIn().setBody(str);
    };
    
     public static Processor reqRestservB = (Exchange exchange) -> { 
         VolsRequest p = (VolsRequest) exchange.getIn().getBody();
        String[] date2format = p.getOutbound_date().split("-");
        String dateformat2 = (date2format[2] + "-" + date2format[1] + "-" + date2format[0]);

        String request = "{ \"event\": \"LIST\","+
                        " \"filter\": "+
                                     "{\"destination\" : \"" +p.getTo() +"\","+
                                      "\"date\":\"" + dateformat2+"\","+
                                      "\"isDirect\":false"+
                        "}}";
     

           
         exchange.getIn().setBody(request);
    };
    public static final Processor Answer1ToFlight = (Exchange exchange) -> {
     vol resultat = new vol();
        resultat.setPrix(String.valueOf(Integer.MAX_VALUE));
        resultat.setDate("not found");
        resultat.setDestination("not found");
        try {
            JsonParser jparser = new JsonParser();
            JsonArray list = jparser.parse((String) exchange.getIn().getBody())
                             .getAsJsonObject().get("Outbound")
                             .getAsJsonArray();
            
            for (JsonElement j : list) {
                JsonObject jsontmp = j.getAsJsonObject();
                if(Integer.valueOf(jsontmp.get("prix").getAsString()) < Integer.valueOf(resultat.getPrice())){
                    resultat=new vol(jsontmp);
                }
            }
        }
        catch(JsonSyntaxException | NumberFormatException e){
            exchange.getIn().setBody(resultat);
        }

        exchange.getIn().setBody(resultat);
};

public static final Processor Answer2ToFlight = (Exchange exchange) -> {
     vol resultat = new vol();
        resultat.setPrix(String.valueOf(Integer.MAX_VALUE));
        resultat.setDate("not found");
        resultat.setDestination("not found");
        try { 
            JsonArray list =new JsonParser()
                    .parse((String) exchange.getIn().getBody())
                    .getAsJsonObject()
                    .get("vols")
                    .getAsJsonArray();
           
            for(JsonElement j : list){
              
                JsonObject jsontmp = j.getAsJsonObject();
                
               
                if(Integer.valueOf(jsontmp.get("price").getAsString()) < Integer.valueOf(resultat.getPrice())){
                    
                    resultat.setDestination(jsontmp.get("destination").getAsString());
                     
                    resultat.setDate(jsontmp.get("date").getAsString());
                     
                    resultat.setPrix(jsontmp.get("price").getAsString());
                      
                    JsonArray stops = j.getAsJsonObject()
                                       .get("stops")
                                       .getAsJsonArray();
                    
                    if(!stops.isJsonNull())
                    {    
                        StringBuilder ss =new StringBuilder();
                        for(JsonElement k :stops )
                        { 
                            // JsonObject jsontmp2 = k.getAsJsonObject();
                             ss.append(k.toString()+"/");
                        }
                        ss.deleteCharAt(ss.length()-1);
                      resultat.setOrigine(ss.toString());
                    }
                }
            }
        }
        catch(JsonSyntaxException | NumberFormatException e){
            e.printStackTrace();
            exchange.getIn().setBody(resultat);
        }
        exchange.getIn().setBody(resultat);
};
}
