/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;


import esb.flows.implem.data.Request;
import esb.flows.implem.utils.CarRentalHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.builder.RouteBuilder;
import esb.flows.implem.utils.CsvFormat;
import static esb.flows.implem.utils.Endpoints.*;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


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
    
}
