

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


public class RemboursementFlow extends RouteBuilder {
    private static final ExecutorService WORKERS = Executors.newFixedThreadPool(3);
    @Override
    public void configure() throws Exception 
    {

        from(CSV_INPUT_FILE_CAR)
        .routeId("csv-input-remboursement")
        .routeDescription("Loads a CSV containg request to remboursement")
        .log("Processing ${file:name}")
        .unmarshal(CsvFormat.buildCsvFormat())
        .log("Loading the file as a CSV document")
        .setHeader(Exchange.HTTP_METHOD, constant("POST")) 
        .setHeader("Content-Type", constant("application/json"))
        .setHeader("Accept", constant("application/json"))
		.process(RemboursementProcessors.csv2Request)
        .inOut(OCR_ENDPOINT) 
        .unmarshal().string()
        .to(LETTER_OUTPUT_DIR+ "?fileName=OCR.txt")
          
       
    }  



}
