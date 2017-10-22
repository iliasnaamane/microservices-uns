/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;

import esb.flows.implem.data.*;
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
import esb.flows.implem.utils.CarRentalHelper;

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


        /*------------------Add Car request-------------------*/
         from(CSV_INPUT_FILE_CAR)
            .routeId("csv-input-car")
            .routeDescription("Loads a CSV containg request")
            .log("Processing ${file:name}")
            .unmarshal(CsvFormat.buildCsvFormat())
            .log("Loading the file as a CSV document")
            .split(body())
                .parallelProcessing().executorService(WORKERS)
                .process(csv2Request)
            .to(SEARCH_CAR);
        
        from(SEARCH_CAR)
            .routeId("search-car")
            .routeDescription("Call the car rental service")
            .log("Processing ${file:name}")
            .bean(CarRentalHelper.class, "buildGetCarsRequest(${body})")
            .inOut(CAR_RPC_ENDPOINT)
            .log("THIS IS SPARTA")
                ;
                
       
    }

    private static   Processor csv2Request = (Exchange exchange)-> {
    System.out.println("csv2Request");
    Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();
    Request req = new Request();
    req.setPlace((String)data.get("place"));
    req.setDateStart((String)data.get("dateStart"));
    req.setDateEnd((String)data.get("dateEnd"));
    System.out.println(req.toString());
    exchange.getIn().setBody(req);
    System.out.println("Fin csv2Request");
    };
    
   /* private static   Processor listToCheapHotel = (Exchange exchange)-> {
        System.out.println("listHotelsToCheapHotel");
        System.out.println((String)exchange.getIn().getBody());
    };*/
    
}
