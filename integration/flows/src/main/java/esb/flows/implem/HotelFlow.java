/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.builder.RouteBuilder;
import esb.flows.implem.utils.CsvFormat;
import static esb.flows.implem.utils.Endpoints.*;
import esb.flows.implem.utils.HotelSearchHelper;
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
                        .process(HotelSearchHelper.cv2hotelSpec)
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
                .process(HotelSearchHelper.RequestRPC)
                .to(LETTER_OUTPUT_DIR+"?fileName=cheapHotel.txt")
        ;

/*
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
                ;*/
                
        from(SEARCH_HOTEL_2)
            .routeId("request-to-hotelrest")
                .routeDescription("send request data from queue to service")
                .log(body().toString())
                .log("request to external hotel rest service")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader("Accept", constant("application/json"))
                .process(HotelSearchHelper.RequestREST)
                .inOut(HOTEL_EXTERNAL_REST_ENDPOINT)
                .unmarshal().string()
                .to(LETTER_OUTPUT_DIR+"?fileName=cheapHotelExtern.txt")
                
                
        ;
       
    }

   /* private static   Processor csv2Request = (Exchange exchange)-> {
    System.out.println("csv2Request");
    Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();
    Request req = new Request();
    req.setPlace((String)data.get("place"));
    req.setDateStart((String)data.get("dateStart"));
    req.setDateEnd((String)data.get("dateEnd"));
    System.out.println(req.toString());
    exchange.getIn().setBody(req);
    System.out.println("Fin csv2Request");
    };*/
    
   /* private static   Processor listToCheapHotel = (Exchange exchange)-> {
        System.out.println("listHotelsToCheapHotel");
        System.out.println((String)exchange.getIn().getBody());
    };*/
    
}
