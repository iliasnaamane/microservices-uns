/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Helpers;

import static esb.flows.implem.utils.Endpoints.HOTEL_RPC_ENDPOINT;
import java.net.MalformedURLException;
import java.net.URL;
import stubs.hotel_rpc.ExternalHotelFinderService;
import stubs.hotel_rpc.HotelFinderService;

/**
 *
 * @author iliasnaamane
 */
public class HotelHelper {
    public static  HotelFinderService getWS() {
        URL wsdl=null;
        try {
            wsdl = new URL("http://localhost:9010/hotel-rpc/ExternalHotelFinderService?wsdl");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        ExternalHotelFinderService factory = new ExternalHotelFinderService(wsdl);
        HotelFinderService ws = factory.getExternalHotelFinderPort();
        String address = HOTEL_RPC_ENDPOINT;
        return ws;
    }
}
