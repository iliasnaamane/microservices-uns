/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import esb.flows.implem.data.Flight.VolsRequest;
import esb.flows.implem.data.Flight.vol;
import org.apache.camel.Processor;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.builder.RouteBuilder;
import esb.flows.implem.utils.Helpers.CsvFormat;
import static esb.flows.implem.utils.Endpoints.*;
import esb.flows.implem.utils.Strategies.VolsStrategy;
import org.apache.camel.Exchange;
/**
 *
 * @author obisama
 */
public class VolsFlow extends RouteBuilder {
 private static final ExecutorService WORKERS = Executors.newFixedThreadPool(4);
    @Override
    public void configure() throws Exception {
       
         from(CSV_INPUT_FILE_VOLS)
                .routeId("csv-to-Vol")
                .routeDescription("Loads a CSV file containing The flights request info")
                .log("Processing ${file:name}")
                .log("  Loading the file as a CSV document")
                .unmarshal(CsvFormat.buildCsvFormat())  
                .log("  Splitting the content of the file into atomic lines")
                .split(body())
                .parallelProcessing().executorService(WORKERS)
                        .log("  CSV 2 VolRequest")
                        .process(csv2volRequest)
                        .log("After Process (reading from csv)::::: "+body().toString())
                        .to(BUILD_VOL_QUEUE)
                                   
        ;
        
                 from(BUILD_VOL_QUEUE)
                .routeId("vols-req-queue")
                .routeDescription("queue des requetes de vols")
                .multicast() 
                .to(SEARCH_VOLA, SEARCH_VOLB)
//                  .to(SEARCH_VOLA)        
        ;

                
                 
        from(SEARCH_VOLA)
            .routeId("request-to-vol rest A")
                .routeDescription("send request data from queue to service A")
                .log("request to external hotel rest service")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .log("Before Process reqRestservA ::::: "+body().toString())
                .process(reqRestservA)
                .log("After Process ::::: "+body().toString())
                .inOut(VOL_JAXRS_ENDPOINT)
                .unmarshal().string()
                .process(Answer1ToFlight)
//                .to(VOLS_AGGREGATE)
                .to(VOLS_AGGREGATE)
                ; 
         from(SEARCH_VOLB) // transforme des FlightRequest
                .routeId("request-to-vol extern B")
                .routeDescription(" request data from external service")
                .setHeader(Exchange.HTTP_METHOD, constant("POST")) 
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .log("Before Process reqRestservB ::::: "+body().toString())
                .process(reqRestservB)
                .inOut(VOLS_EXTERNAL_JAXRS_ENDPOINT) 
                .unmarshal().string()
                .process(Answer2ToFlight)
                //.marshal().json(JsonLibrary.Jackson)
                .to(VOLS_AGGREGATE)
        ;
         
          from(VOLS_AGGREGATE)
                .routeId("aggreg-flight")
                .routeDescription("l'aggregator des avions")
                .log("before aggreg" + body())
                .aggregate(constant(true), new VolsStrategy())
                  .log("After Aggregator")
                .completionSize(2)
                .log("after aggreg" + body())
//                .unmarshal().string()
                .log("nothing left but to wright on the file")
                //.marshal().json(JsonLibrary.Jackson)
                .to(BUSINESS_TRAVEL_REST2)
        ;
          
           from(BUSINESS_TRAVEL_REST2)
           .routeId("business-rest-call2")
              .routeDescription("send request to rest service2")
              .log(body().toString())
                .setHeader(Exchange.HTTP_METHOD, constant("POST")) 
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
              //  .unmarshal().string()
                .to(LETTER_OUTPUT_DIR+"?fileName=volss.txt")
        ;

    }
    
    private static Processor csv2volRequest = (Exchange exchange) -> {
            Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();
            VolsRequest p =  new VolsRequest(data);
            System.out.println(p.toString());
            if(p.getOutbound_date()==null) 
            {   System.out.println("Date Is NULL AGAAAAIN and data contains Outbound_date ?? ="+data.containsKey("Outbound_date"));
                  for(Map.Entry<String, Object> entry : data.entrySet()) {
                         System.out.println( 
                                 entry.getKey() +
                                 ":"+entry.getValue());}
                p.setOutbound_date("12-10-2017");
            }
            System.out.println(p.toString());
            exchange.getIn().setBody(p);
    };
     
    private static Processor reqRestservA = (Exchange exchange) -> {
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
             System.out.println(str);

            exchange.getIn().setBody(str);
    };
    
     private static Processor reqRestservB = (Exchange exchange) -> { 
         VolsRequest p = (VolsRequest) exchange.getIn().getBody();
        String[] date2format = p.getOutbound_date().split("-");
        String dateformat2 = (date2format[2] + "-" + date2format[1] + "-" + date2format[0]);

        String request = "{ \"event\": \"LIST\","+
                        " \"filter\": "+
                                     "{\"destination\" : \"" +p.getTo() +"\","+
                                      "\"date\":\"" + dateformat2+"\","+
                                      "\"isDirect\":false"+
                        "}}";
     

            System.out.println("erqRestservB resultat::::: "+request);
         exchange.getIn().setBody(request);
    };
    

private static final Processor Answer1ToFlight = (Exchange exchange) -> {
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

private static final Processor Answer2ToFlight = (Exchange exchange) -> {
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
            
            System.out.println("j'ai transformé le json" + list) ;
            for(JsonElement j : list){
                System.out.println("0::dans la boucle for") ;
                JsonObject jsontmp = j.getAsJsonObject();
                System.out.println("1::dans la boucle for") ;
               
                if(Integer.valueOf(jsontmp.get("price").getAsString()) < Integer.valueOf(resultat.getPrice())){
                     System.out.println("2::dans la boucle for, dans if") ;
                    resultat.setDestination(jsontmp.get("destination").getAsString());
                      System.out.println("3::dans la boucle for, dans if") ;
                    resultat.setDate(jsontmp.get("date").getAsString());
                      System.out.println("4::dans la boucle for, dans if") ;
                    resultat.setPrix(jsontmp.get("price").getAsString());
                      System.out.println("5::dans la boucle for, dans if") ;
                    JsonArray stops = j.getAsJsonObject()
                                       .get("stops")
                                       .getAsJsonArray();
                      System.out.println("6::dans la boucle for, dans if") ;
                    
                    if(!stops.isJsonNull())
                    {
                          System.out.println("7::dans la boucle for, dans if") ;
                        StringBuilder ss =new StringBuilder();
                        for(JsonElement k :stops )
                        { 
                            // JsonObject jsontmp2 = k.getAsJsonObject();
                             ss.append(k.toString()+"/");
                        }
                          System.out.println("8::dans la boucle for, dans if") ;
                        ss.deleteCharAt(ss.length()-1);
                      resultat.setOrigine(ss.toString());
                    }
                }
            }
        }
        catch(JsonSyntaxException | NumberFormatException e){
            e.printStackTrace();
            System.out.println("9::dans catch") ;
            System.out.println(exchange.getIn().getBody().toString() + " \n" + exchange.getIn().getHeaders());
            exchange.getIn().setBody(resultat);
        }
        System.out.println(resultat.toString());
        System.out.println("10::sortie") ;
        exchange.getIn().setBody(resultat);
};
}
