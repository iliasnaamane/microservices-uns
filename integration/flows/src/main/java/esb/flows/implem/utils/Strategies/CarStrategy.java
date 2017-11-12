/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import esb.flows.implem.data.Car.CarForm;
import static esb.flows.implem.utils.Endpoints.ERR_FILE_CAR;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 *
 * @author iliasnaamane
 */
public class CarStrategy implements AggregationStrategy {

    @Override
    @SuppressWarnings("empty-statement")
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                
        if(oldExchange == null){
            oldExchange = newExchange;
            System.out.println("null");
            return newExchange;
        }
            
        else if(newExchange != null)  {
            CarForm ch = (CarForm)oldExchange.getIn().getBody();
            CarForm che = (CarForm)newExchange.getIn().getBody();
            Object chHeader = oldExchange.getIn().getHeader("err1:");
           Object cheHeader = newExchange.getIn().getHeader("err2:");
            if(chHeader != null && cheHeader != null ){
                System.out.println("Both car Services  not found - Write Error");
                oldExchange.getIn().setHeader("message","both-service-car");
              //oldExchange.getIn().setBody("Both Car Rental Services  not found");
                WriteInFile(ERR_FILE_CAR, "both services are not available");
               
                return oldExchange;
            }
            if(chHeader != null){
                 System.out.println("Service 1 car not found");
                 WriteInFile(ERR_FILE_CAR, "service car internal is not available");
                oldExchange.getIn().setHeader("message", "1-service-car");
                oldExchange.getIn().setBody(che);
                return oldExchange;
                
            }
            
            if(cheHeader != null){
                System.out.println("Service 2 car not found");
                WriteInFile(ERR_FILE_CAR, "service car external is not available");
                oldExchange.getIn().setHeader("message", "2-service-car");
                return oldExchange;
            }

            if(ch.getPrix()> che.getPrix()){
                oldExchange.getIn().setBody(che);
                System.out.println("return value : "+oldExchange.getIn().getBody());
                return oldExchange;
               
            }     
        }
        return oldExchange;
    }
    
    
    private  void WriteInFile(String endpoint, String content){
        PrintWriter writer;
        try {
            writer = new PrintWriter(endpoint, "UTF-8");
            writer.println(content);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(CarStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
