

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package esb.flows.implem;



import esb.flows.implem.utils.Helpers.CarRentalHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.builder.RouteBuilder;
import esb.flows.implem.utils.Helpers.CsvFormat;
import static esb.flows.implem.utils.Endpoints.*;
import java.util.Map;
import esb.flows.implem.utils.Processors.CarProcessors;
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
            .process(CarProcessors.csv2Request)
        .to(SEARCH_CAR);
    
        from(SEARCH_CAR)
            .routeId("search-car")
            .routeDescription("Call the car rental service")
            .log("Processing ${file:name}")
            .bean(CarRentalHelper.class, "buildGetCarsRequest(${body})")
            .log("input requete dans car-RPC")
            .inOut(CAR_RPC_ENDPOINT)
            .to(AGGREGATION_CAR);

        from(AGGREGATION_CAR)
            //.process(CarProcessors.car2Json)
            .unmarshal().string()
            .to(LETTER_OUTPUT_DIR+"?fileName=car.txt");
     
    }

}
