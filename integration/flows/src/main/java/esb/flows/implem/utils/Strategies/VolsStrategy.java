/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import esb.flows.implem.data.Flight.ItemPriceComparator;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 *
 * @author obisama
 */
public class VolsStrategy implements AggregationStrategy {
     @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(oldExchange == null){
            return newExchange;
        }
        
        if (newExchange != null){
            ItemPriceComparator oldflow = (ItemPriceComparator) oldExchange.getIn().getBody();
            ItemPriceComparator newflow = (ItemPriceComparator) newExchange.getIn().getBody();
            
            if(Integer.valueOf(newflow.getPrice()) < Integer.valueOf(oldflow.getPrice()))
            {
                oldExchange.getIn().setBody(newflow);
            }

        }
        return oldExchange;
    }

 
}