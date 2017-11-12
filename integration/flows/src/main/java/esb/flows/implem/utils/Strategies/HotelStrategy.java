/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import esb.flows.implem.data.Hotel.CheapHotel;
import static esb.flows.implem.utils.Endpoints.ERR_FILE_HOTEL;
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
public class HotelStrategy implements AggregationStrategy{

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
       System.out.println("hello");
        if(oldExchange == null){
            oldExchange = newExchange;
            System.out.println("null");
            return newExchange;
        }
            
        else if(newExchange != null)  {
            CheapHotel ch = (CheapHotel)oldExchange.getIn().getBody();
            CheapHotel che = (CheapHotel)newExchange.getIn().getBody();
            Object headerCh = oldExchange.getIn().getHeader("err1:");
            Object headerChe = newExchange.getIn().getHeader("err2:");
            if(headerCh != null && headerChe != null){
                 System.out.println("Both hotel Services  not found");
                oldExchange.getIn().setHeader("message","both-service-hotel");
              //oldExchange.getIn().setBody("Both Car Rental Services  not found");
                WriteInFile(ERR_FILE_HOTEL, "both hotel services are not available");
            }
            if(headerCh != null){
                System.out.println("Service 1 hotel not found ");
                oldExchange.getIn().setHeader("message","1-service-hotel-notfound");
              //oldExchange.getIn().setBody("Both Car Rental Services  not found");
                WriteInFile(ERR_FILE_HOTEL, "service 1 hotel not found");
            }
            if(headerChe != null){
                System.out.println("Service 2 hotel not found ");
                oldExchange.getIn().setHeader("message","2-service-hotel-notfound");
              //oldExchange.getIn().setBody("Both Car Rental Services  not found");
                WriteInFile(ERR_FILE_HOTEL, "service 2 hotel not found");
            }
                
            System.out.println("ch: "+ch.getPrice());
            System.out.println("che:"+che.getPrice());
            if(ch.getPrice() > che.getPrice()){
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
