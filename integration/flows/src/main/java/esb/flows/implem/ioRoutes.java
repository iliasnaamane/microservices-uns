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
                        .process(cv2hotelSpec)
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
                .bean(HotelSearchHelper.class,"getCheapHotel(${body})")
                .unmarshal().string()
                .to(LETTER_OUTPUT_DIR+"?fileName=cheapHotel.txt")
        ;
                
       
    }
    
    private static   Processor cv2hotelSpec = (Exchange exchange)-> {
        System.out.println("cv2hotelSpecaaaa");
        Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();
        System.out.println("3333aaaaaaaaaa");
        HotelSpec hs = new HotelSpec();
        hs.setDest((String)data.get("dest"));
        hs.setDuration((int)data.get("duration"));
        hs.setSortedAscorDesc(true);
        System.out.println(hs.toString());
        exchange.getIn().setBody(hs);
        System.out.println("aaaaaaaaaa");
    };
    
   /* private static   Processor listToCheapHotel = (Exchange exchange)-> {
        System.out.println("listHotelsToCheapHotel");
        System.out.println((String)exchange.getIn().getBody());
    };*/
    
}
