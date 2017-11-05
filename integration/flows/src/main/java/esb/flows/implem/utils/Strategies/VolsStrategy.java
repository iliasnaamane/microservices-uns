/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Strategies;

import esb.flows.implem.data.Flight.ItemPriceComparator;
import esb.flows.implem.data.Flight.vol;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 *
 * @author obisama
 */
public class VolsStrategy implements AggregationStrategy {

     @Override
    public Exchange aggregate(Exchange internal, Exchange external) {
     System.out.println("hello");
        if(internal == null){
            internal = external;
            System.out.println("null");
            System.out.println(internal.getIn().getBody().toString());
            return external;
        }
            
        else if(external != null)  {
            vol ch = (vol)internal.getIn().getBody();
            vol che = (vol)external.getIn().getBody();
            System.out.println("ch: "+ch.getPrice());
            System.out.println("che:"+che.getPrice());
        //    if(ch.getPrice() > che.getPrice())
        System.out.println("Integer.valueOf(ch.getPrice()) > Integer.valueOf(che.getPrice())"+"\n"+Integer.valueOf(ch.getPrice())+" "+ Integer.valueOf(che.getPrice()));
            if(Integer.valueOf(ch.getPrice()) > Integer.valueOf(che.getPrice())){
                System.out.println( "Maybe  the prob is here" +che + "   "+ch);
                internal.getIn().setBody(che.toString());
                System.out.println("return value : "+internal.getIn().getBody());
                return internal;
               
            }     
        }
        
        return internal;
            
         
      
         
    }
}