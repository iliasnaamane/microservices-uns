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
import esb.flows.implem.utils.Processors.VolProcessors;
/**
 *
 * @author obisama
 */
public class VolsFlow extends RouteBuilder {
 private static final ExecutorService WORKERS = Executors.newFixedThreadPool(3);
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
                        .process(VolProcessors.csv2volRequest)
                        .log("After Process (reading from csv)::::: "+body().toString())
                        .to(BUILD_VOL_QUEUE)
                                   
        ;
        
                 from(BUILD_VOL_QUEUE)
                .routeId("vols-req-queue")
                .routeDescription("queue des requetes de vols")
                .multicast() 
                .to(SEARCH_VOLA, SEARCH_VOLB)   
        ;

                
                 
        from(SEARCH_VOLA)
            .routeId("request-to-vol rest A")
                .routeDescription("send request data from queue to service A")
                .log("request to external hotel rest service")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .log("Before Process reqRestservA ::::: "+body().toString())
                .process(VolProcessors.reqRestservA)
                .log("After Process ::::: "+body().toString())
                .inOut(VOL_JAXRS_ENDPOINT)
                .unmarshal().string()
                .process(VolProcessors.Answer1ToFlight)
                .to(VOLS_AGGREGATE)
                ; 
         from(SEARCH_VOLB) 
                .routeId("request-to-vol extern B")
                .routeDescription(" request data from external service")
                .setHeader(Exchange.HTTP_METHOD, constant("POST")) 
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .log("Before Process reqRestservB ::::: "+body().toString())
                .process(VolProcessors.reqRestservB)
                .inOut(VOLS_EXTERNAL_JAXRS_ENDPOINT) 
                .unmarshal().string()
                .process(VolProcessors.Answer2ToFlight)
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
                 .setHeader("type",constant("flight"))
                .to(BUSINESS_TRAVEL_REST)
        ;
          
          
        

    }

}
