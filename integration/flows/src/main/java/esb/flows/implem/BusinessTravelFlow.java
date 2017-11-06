/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;

import static esb.flows.implem.utils.Endpoints.*;
import esb.flows.implem.utils.Processors.CarProcessors;
import esb.flows.implem.utils.Strategies.BusinessTravelStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author iliasnaamane
 */
public class BusinessTravelFlow extends RouteBuilder {
    
  private static final ExecutorService WORKERS = Executors.newFixedThreadPool(3);
    

    @Override
    public void configure() throws Exception {
        from(BUSINESS_TRAVEL_REST)
            .routeId("business-flow")
            .routeDescription("business travel route")
                .aggregate(constant(true), new BusinessTravelStrategy())
                .completionPredicate((exchng) -> {
                    String outPutAggreg = exchng.getIn().getBody(String.class);
                    if(outPutAggreg.contains("flight") && outPutAggreg.contains("hotel") && outPutAggreg.contains("car")){
                        System.out.println("END BUSINESS TRAVEL AGGREGATION");
                        return true;  
                    }
                       
                     return false;
                })
                .unmarshal().string()
                .process(finalJson)
                .setHeader(Exchange.HTTP_METHOD, constant("POST")) 
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .inOut(BUSINESS_TRAVEL_ENDPOINT)
                .unmarshal().string()
                .to(LETTER_OUTPUT_DIR+"?fileName=output.txt");
                
        ;
        
       
                
        
        
        
    }
    
    public static Processor finalJson = (Exchange exchange) -> {
       System.out.println("final Json");
        String json = (String)exchange.getIn().getBody();
        String finalJson = "{\n"+json+"}\n";
        exchange.getIn().setBody(finalJson);
        
    };
}
