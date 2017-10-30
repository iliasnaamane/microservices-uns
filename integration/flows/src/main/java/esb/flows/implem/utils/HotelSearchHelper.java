/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils;

import esb.flows.implem.data.HotelSpec;
import static esb.flows.implem.utils.Endpoints.HOTEL_RPC_ENDPOINT;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import stubs.hotel_rpc.ExternalHotelFinderService;
import stubs.hotel_rpc.Hotel;
import stubs.hotel_rpc.HotelFinderService;

/**
 *
 * @author iliasnaamane
 */
public class HotelSearchHelper {
   
  
    
    public static final Processor cv2hotelSpec = (Exchange exchange) -> {
        //System.out.println("cv2hotelSpec");
        Map<String, String> data = (Map<String, String>) exchange.getIn().getBody();
        HotelSpec hs = new HotelSpec();
        data.entrySet().forEach((Map.Entry<String, String> entry) -> {
            System.out.println(entry.getKey() + ", " + entry.getValue());
            if(entry.getKey().equals("duration")){
                hs.setDuration(Integer.parseInt(entry.getValue()));
                System.out.println("duration: "+Integer.parseInt(entry.getValue()));
            }
                
            else{
                hs.setDest(entry.getValue());
                System.out.println("destination: " + entry.getValue());
            }
             
        });
        
        hs.setSortedAscorDesc(true);
        System.out.println(hs.toString());
        System.out.println("hello its working");
        exchange.getIn().setBody(hs);
        
    };
   
    public static Processor RequestRPC = (Exchange exchange) -> {
        HotelSpec hs = (HotelSpec) exchange.getIn().getBody();
        HotelFinderService hf = HotelSearchHelper.getWS();
        ArrayList<Hotel> response = new ArrayList<Hotel>();
        response = (ArrayList<Hotel>) hf.recherche(hs.getDest(), hs.getDuration(), true);
        int min_value = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < response.size(); i++) {
            int price = response.get(i).getPrix();
            if (price < min_value) {
                min_value = price;
                index = i;
            }
        }
        Hotel CheapHotel = response.get(index);
        int total_price = hs.getDuration()*min_value;
        String CheapHotelJson = "{\n" +
"       \"id_hotel\":\""+CheapHotel.getIdentifier() +"-service-1,\n" +
"       \"nights\":"+ hs.getDuration()+",\n" +
"       \"price\":"+ total_price + "\n" +
"       }";
        System.out.println(CheapHotelJson);
        exchange.getIn().setBody(CheapHotelJson);

    };
    
     public static final Processor RequestREST = (Exchange exchange) -> {
        HotelSpec hs = (HotelSpec) exchange.getIn().getBody();
        exchange.getIn().setHeader(Exchange.HTTP_QUERY,"destination="+hs.getDest());
        exchange.getIn().setBody(null);
        
    };
    
    public static  HotelFinderService getWS() {
        System.out.println("get web service RPcC");
        URL wsdl=null;
       try {
           wsdl = new URL("http://localhost:9010/hotel-rpc/ExternalHotelFinderService?wsdl");
       } catch (MalformedURLException ex) {
           ex.printStackTrace();
       }
        System.out.println(wsdl);
        System.out.println("WSDL");
        ExternalHotelFinderService factory = new ExternalHotelFinderService(wsdl);
        HotelFinderService ws = factory.getExternalHotelFinderPort();
        String address = HOTEL_RPC_ENDPOINT;
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        System.out.println("WSDL DONE");
        return ws;
    }
    
    
   
}
