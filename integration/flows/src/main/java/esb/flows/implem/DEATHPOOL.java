/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem;

import static esb.flows.implem.utils.Endpoints.DEATH_POOL;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author iliasnaamane
 */
public class DEATHPOOL extends RouteBuilder {
     static int REDELIVERIES = 2;

    @Override
    public void configure() throws Exception {

        errorHandler(
                deadLetterChannel(DEATH_POOL)
                        .useOriginalMessage()
                        .maximumRedeliveries(REDELIVERIES)
                        .redeliveryDelay(500)
        );
    }
}
