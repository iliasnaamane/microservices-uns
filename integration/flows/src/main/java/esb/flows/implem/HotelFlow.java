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
import esb.flows.implem.utils.Processors.HotelProcessors;
import esb.flows.implem.utils.Strategies.HotelStrategy;
import org.apache.camel.Exchange;


/**
 *
 * @author iliasnaamane
 */
public class HotelFlow extends RouteBuilder {
    private static final ExecutorService WORKERS = Executors.newFixedThreadPool(3);
    @Override
    public void configure() throws Exception {
       
        from(CSV_INPUT_FILE_HOTEL)
            .routeId("csv-input-hotel")
                .routeDescription("Loads a CSV containg hotel rent specification")
                .log("Processing ${file:name}")
                .unmarshal(CsvFormat.buildCsvFormat())
                .log("Loading the file as a CSV document")
                .split(body())
                    .parallelProcessing().executorService(WORKERS)
                        .process(HotelProcessors.cv2hotelSpec)
                .log("I am here")
                .to(BUILD_HOTEL_SPEC)
               
        ;
        
        from(BUILD_HOTEL_SPEC)
           .routeId("spec hotel queue")
                .routeDescription("queue")
                .multicast()
                .to(SEARCH_HOTEL_1,SEARCH_HOTEL_2)    
        ;
        
        from(SEARCH_HOTEL_1)
            .routeId("request-to-hotelrpc-1")
                .routeDescription("send request data from queue to service")
                .log(body().toString())
                .process(HotelProcessors.RequestRPC)
                .to(AGGREGATION_HOTEL)
        ;



                
        from(SEARCH_HOTEL_2)
            .routeId("request-to-hotelrest")
                .routeDescription("send request data from queue to service")
                .log(body().toString())
                .log("request to external hotel rest service")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("Accept", constant("application/json"))
                .process(HotelProcessors.RequestREST)
                .inOut(HOTEL_EXTERNAL_REST_ENDPOINT)
                .unmarshal().string()
                .process(HotelProcessors.json2CheapHotel)
                .to(AGGREGATION_HOTEL)   
        ;
        
        from(AGGREGATION_HOTEL)
            .routeId("aggregation-hotel")
                .routeDescription("send request ")
                .log(body().toString())
                .aggregate(constant(true),new HotelStrategy())
                .completionSize(2)
                .process(HotelProcessors.hotel2Json)
                .unmarshal().string()
                .to(BUSINESS_TRAVEL_REST)
                
        ;
        
        from(BUSINESS_TRAVEL_REST)
           .routeId("business-rest-call")
              .routeDescription("send request to rest service")
              .log(body().toString())
                .setHeader(Exchange.HTTP_METHOD, constant("POST")) 
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .inOut(BUSINESS_TRAVEL_ENDPOINT)
                .unmarshal().string()
                .to(LETTER_OUTPUT_DIR+"?fileName=output.txt")
        ;
    }
    
   /* private static   Processor listToCheapHotel = (Exchange exchange)-> {
        System.out.println("listHotelsToCheapHotel");
        System.out.println((String)exchange.getIn().getBody());
    };*/
    
}
