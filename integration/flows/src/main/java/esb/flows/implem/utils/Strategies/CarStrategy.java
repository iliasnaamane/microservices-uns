/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import esb.flows.implem.data.Car.CarForm;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 *
 * @author iliasnaamane
 */
public class CarStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        System.out.println("hello");
        
        
        if(oldExchange == null){
            oldExchange = newExchange;
            System.out.println("null");
            return newExchange;
        }
            
        else if(newExchange != null)  {
            CarForm ch = (CarForm)oldExchange.getIn().getBody();
            CarForm che = (CarForm)newExchange.getIn().getBody();
            System.out.println("ch: "+ch.getPrix());
            System.out.println("che:"+che.getPrix());
            if(ch.getPrix()> che.getPrix()){
                oldExchange.getIn().setBody(che);
                System.out.println("return value : "+oldExchange.getIn().getBody());
                return oldExchange;
               
            }     
        }
        System.out.println("wesh");
        return oldExchange;
    }
    
}
