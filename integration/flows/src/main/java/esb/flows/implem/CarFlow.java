

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package esb.flows.implem;




import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.builder.RouteBuilder;
import esb.flows.implem.utils.Helpers.CsvFormat;
import static esb.flows.implem.utils.Endpoints.*;

import esb.flows.implem.utils.Processors.CarProcessors;
import esb.flows.implem.utils.Strategies.CarStrategy;

import org.apache.camel.Exchange;


/**
 *
 * @author iliasnaamane
 */
public class CarFlow extends RouteBuilder {
    private static final ExecutorService WORKERS = Executors.newFixedThreadPool(3);
    @Override
    public void configure() throws Exception {

        from(CSV_INPUT_FILE_CAR)
        .routeId("csv-input-car")
        .routeDescription("Loads a CSV containg request")
        .log("Processing ${file:name}")
        .unmarshal(CsvFormat.buildCsvFormat())
        .log("Loading the file as a CSV document")
        .split(body())
            .parallelProcessing().executorService(WORKERS)
            .process(CarProcessors.csv2Request)
        .to(BUILD_CAR_QUEUE);
        
        
        from(BUILD_CAR_QUEUE)
          .routeId("car queue")
                .routeDescription("car request queue")
                .log("we are in the car request queue")
                .multicast()
                .to(SEARCH_CAR_1,SEARCH_CAR_2)
        ;

    
        from(SEARCH_CAR_1)
            .routeId("search-car-1")
            .routeDescription("Call our car rental RPC service")
            .log("Processing ${file:name}")

            .setHeader(Exchange.HTTP_METHOD, constant("POST"))
            .setHeader("Content-Type", constant("text/xml"))
            .process(CarProcessors.RequestRPC)
            .to(CARS_AGGREGATE); 
        
        from(SEARCH_CAR_2)
            .routeId("search-car-2")
                .routeDescription("Call external rental REST service ")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("Accept", constant("application/json"))
                .process(CarProcessors.RequestREST)
                .inOut(CAR_EXTERNAL_REST_ENDPOINT)
                .unmarshal().string()
                .process(CarProcessors.json2CarExternalForm)
                .to(CARS_AGGREGATE) 
        ;
        
        from(CARS_AGGREGATE)
            .routeId("car-aggregation")
               .routeDescription("car aggregation route")
                .log(body().toString())
                .aggregate(constant(true),new CarStrategy())
                .completionSize(2)
                .process(CarProcessors.CarForm2json)
                .unmarshal().string()
                .setHeader("type",constant("carRental"))
                .to(BUSINESS_TRAVEL_REST)
        ;
        
       
        
        
        
       
    }

   
    

}
