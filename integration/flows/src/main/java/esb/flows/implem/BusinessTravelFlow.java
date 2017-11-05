/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;

import static esb.flows.implem.utils.Endpoints.*;
import esb.flows.implem.utils.Strategies.BusinessTravelStrategy;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author iliasnaamane
 */
public class BusinessTravelFlow extends RouteBuilder {
    
    
    

    @Override
    public void configure() throws Exception {
        from(BUSINESS_TRAVEL_REST)
            .routeId("business-flow")
            .routeDescription("business travel route")
                .aggregate(constant(true), new BusinessTravelStrategy())
                .completionSize(3)
                .unmarshal().string()
                .to(LETTER_OUTPUT_DIR+"?fileName=output.txt")
        ;
    }
}
