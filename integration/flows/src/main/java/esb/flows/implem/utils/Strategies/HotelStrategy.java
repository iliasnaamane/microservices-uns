/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import esb.flows.implem.data.Hotel.CheapHotel;
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
    
}
