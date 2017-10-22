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
import static esb.flows.implem.utils.HotelSearchHelper.getWS;
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
                        .process(cv2hotelSpec)
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
                .process(RequestRPC)
                .to(LETTER_OUTPUT_DIR+"?fileName=cheapHotel.txt")
        ;
                
       
    }
    
    private static final   Processor cv2hotelSpec = (Exchange exchange) -> {
        //System.out.println("cv2hotelSpec");
        Map<String, String> data = (Map<String, String>) exchange.getIn().getBody();
        HotelSpec hs = new HotelSpec();
        data.entrySet().forEach((Map.Entry<String, String> entry) -> {
            System.out.println(entry.getKey() + ", " + entry.getValue());
            if(entry.getKey().equals("duration")){
                hs.setDuration(Integer.parseInt(entry.getValue()));
                System.out.println("duration: "+Integer.parseInt(entry.getValue()));
            }
                
            else{
                hs.setDest(entry.getValue());
                System.out.println("destination: " + entry.getValue());
            }
             
        });
        
        hs.setSortedAscorDesc(true);
        System.out.println(hs.toString());
        System.out.println("hello its working");
        exchange.getIn().setBody(hs);
        
    };
    
     private static final   Processor RequestRPC = (Exchange exchange) -> {
         HotelSpec hs = (HotelSpec)exchange.getIn().getBody();
         System.out.println(hs.toString());
         System.out.println("Request RPC");
         HotelFinderService hf = HotelSearchHelper.getWS();
         System.out.println("hf");
         System.out.println(hf);
         ArrayList<Hotel> response = new ArrayList<Hotel>();
         System.out.println(hs.getDest());
         response = (ArrayList<Hotel>) hf.recherche(hs.getDest(), hs.getDuration(), true);
         System.out.println("display response");
         System.out.println(response);
         int min_value = Integer.MAX_VALUE;
         int index = -1;
         for (int i = 0; i < response.size(); i++) {
             int price = response.get(i).getPrix();
             if (price < min_value) {
                 min_value = price;
                 index = i;
             }
         }
         Hotel CheapHotel = response.get(index);
         System.out.println(CheapHotel.getNom());
         //return CheapHotel.getIdentifier() + "," + CheapHotel.getNom() + "," + CheapHotel.getPrix() * hs.getDuration();
         exchange.getIn().setBody(hs.toString());
        
    };
    
    
    
    
    
    
   /* private static   Processor listToCheapHotel = (Exchange exchange)-> {
        System.out.println("listHotelsToCheapHotel");
        System.out.println((String)exchange.getIn().getBody());
    };*/
    
}
