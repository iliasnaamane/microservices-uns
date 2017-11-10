/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Helpers;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.ws.BindingProvider;
import stubs.hotel_rpc.ExternalHotelFinderService;
import stubs.hotel_rpc.HotelFinderService;

/**
 *
 * @author iliasnaamane
 */
public class HotelHelper {
    public static  HotelFinderService getWS() {
        URL wsdl = HotelHelper.class.getResource("hotel.wsdl");
        ExternalHotelFinderService factory = new ExternalHotelFinderService(wsdl);
        HotelFinderService ws = factory.getExternalHotelFinderPort();
        String address = "http://hotel-rpc:8080/hotel-rpc/ExternalHotelFinderService";
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }
}
