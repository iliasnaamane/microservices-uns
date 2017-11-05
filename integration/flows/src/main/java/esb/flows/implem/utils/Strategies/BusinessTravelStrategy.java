/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 *
 * @author iliasnaamane
 */
public class BusinessTravelStrategy implements AggregationStrategy{

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        System.out.println("hello aggregation old and new");
        
        if(oldExchange == null) {
           System.out.println("oldExchange null");
            String str = newExchange.getIn().getBody(String.class);
           newExchange.getIn().setBody(str);
           return newExchange;
        }
        else {
            String str = oldExchange.getIn().getBody(String.class);
            String concatStr = str+newExchange.getIn().getBody(String.class);
            System.out.println(concatStr);
            oldExchange.getIn().setBody(concatStr);
            return oldExchange;
            
            
        }
       
        
    }
    
}
