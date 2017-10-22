/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;

import esb.flows.implem.data.HotelSpec;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import esb.flows.implem.utils.CsvFormat;
import static esb.flows.implem.utils.Endpoints.*;
import esb.flows.implem.utils.HotelSearchHelper;
import java.util.ArrayList;
import stubs.hotel_rpc.Hotel;
import stubs.hotel_rpc.HotelFinderService;

/**
 *
 * @author iliasnaamane
 */
public class ioRoutes extends RouteBuilder {
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
                        .process(HotelSearchHelper.cv2hotelSpec)
                .log("I am here")
                .to(BUILD_HOTEL_SPEC)
               
        ;
        
        from(BUILD_HOTEL_SPEC)
           .routeId("spec hotel queue")
                .routeDescription("queue")
                .to(SEARCH_HOTEL_1)    
        ;
        
        from(SEARCH_HOTEL_1)
            .routeId("request-to-hotelrpc-1")
                .routeDescription("send request data from queue to service")
                .log(body().toString())
                .process(HotelSearchHelper.RequestRPC)
                .to(LETTER_OUTPUT_DIR+"?fileName=cheapHotel.txt")
        ;
                
       
    }
    
    
    
    
    
    
    
    
    
    
   /* private static   Processor listToCheapHotel = (Exchange exchange)-> {
        System.out.println("listHotelsToCheapHotel");
        System.out.println((String)exchange.getIn().getBody());
    };*/
    
}
