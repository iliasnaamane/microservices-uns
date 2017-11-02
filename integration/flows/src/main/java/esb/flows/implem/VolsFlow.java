/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;

import com.fasterxml.jackson.databind.ObjectMapper;
import esb.flows.implem.data.VolResponse;
import esb.flows.implem.data.VolsRequest;
import esb.flows.implem.data.vol;
import org.apache.camel.Processor;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.builder.RouteBuilder;
import esb.flows.implem.utils.Helpers.CsvFormat;
import static esb.flows.implem.utils.Endpoints.*;
import java.io.IOException;
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
                .unmarshal(CsvFormat.buildCsvFormat())  // Body is now a List of Map<String -> Object>
                    .log("  Splitting the content of the file into atomic lines")
                .split(body())
                    .parallelProcessing().executorService(WORKERS)
                        .log("  CSV 2 VolRequest")
                        .process(csv2volRequest)
                        .log("After Process (reading from csv)::::: "+body().toString())
                        .to(SEARCH_VOL)
                                   // Async transfer with JMS ( activemq:... )
        ;
                 
        from(SEARCH_VOL)
            .routeId("request-to-vol rest")
                .routeDescription("send request data from queue to service")
                .log("request to external hotel rest service")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .process(requestREST)
                .log("After Process ::::: "+body().toString())
                .inOut(VOL_JAXRS_ENDPOINT)
                .unmarshal().string()
                .process(GETCHEAPESTFLIGHT)
                .to(LETTER_OUTPUT_DIR+"?fileName=vols.txt")
                ;        
    }
    
    private static Processor csv2volRequest = (Exchange exchange) -> {
            Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();
            VolsRequest p =  new VolsRequest();
            p.setOutbound_date((String) data.get("Outbound_date"));          
            p.setFrom((String) data.get("from"));
            p.setTo((String) data.get("to"));
            System.out.println(p.toString());
            if(p.getOutbound_date()==null) 
            {p.setOutbound_date("12-10-2017");}
            System.out.println(p.toString());
            exchange.getIn().setBody(p);
    };
     
    private static Processor requestREST = (Exchange exchange) -> {
            VolsRequest p = (VolsRequest) exchange.getIn().getBody();
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
    
     private static final Processor GETCHEAPESTFLIGHT = (Exchange exchange) -> {
     String jsonobject = (String)exchange.getIn().getBody();
     
     ObjectMapper objectMapper = new ObjectMapper();
     vol cheapest = null;
     jsonobject=jsonobject.replace("Outbound", "outbound");
		try {
		//convert json string to object        
		VolResponse res = objectMapper.readValue(jsonobject, VolResponse.class);
                cheapest = res.getOutbound().get(0);
                }catch(IOException e)
                {
                    System.out.println("Conversion TO Json Failed");
                }
     exchange.getIn().setBody(cheapest.toString()); 
                };
}
