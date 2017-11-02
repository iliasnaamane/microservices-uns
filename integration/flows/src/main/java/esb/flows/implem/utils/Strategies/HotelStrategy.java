/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import esb.flows.implem.data.CheapHotel;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 *
 * @author iliasnaamane
 */
public class HotelStrategy implements AggregationStrategy{

    @Override
    public Exchange aggregate(Exchange internal, Exchange external) {
       System.out.println("hello");
        if(internal == null){
            internal = external;
            System.out.println("null");
            return external;
        }
            
        else if(external != null)  {
            CheapHotel ch = (CheapHotel)internal.getIn().getBody();
            CheapHotel che = (CheapHotel)external.getIn().getBody();
            System.out.println("ch: "+ch.getTotalPrice());
            System.out.println("che:"+che.getTotalPrice());
            if(ch.getTotalPrice() > che.getTotalPrice()){
                internal.getIn().setBody(che);
                System.out.println("return value : "+internal.getIn().getBody());
                return internal;
               
            }     
        }
        
        return internal;
            
         
      
         
    }
    
}
