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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;
import stubs.hotel_rpc.ExternalHotelFinderService;
import stubs.hotel_rpc.Hotel;
import stubs.hotel_rpc.HotelFinderService;

/**
 *
 * @author iliasnaamane
 */
public class HotelSearchHelper {
   
   
   /*public String getCheapHotel(HotelSpec hs){
       System.out.println("ça passe par ici");
       HotelFinderService hf = getWS();
       System.out.println("hf");
       System.out.println(hf);
       ArrayList<Hotel> response = new ArrayList<Hotel>();
       System.out.println(hs.getDest());
       response = (ArrayList<Hotel>)hf.recherche(hs.getDest(),hs.getDuration(),true);
       System.out.println("display response");
       System.out.println(response);
       int min_value = Integer.MAX_VALUE;
       int index = -1;
       for(int i = 0;i<response.size();i++){
           int price = response.get(i).getPrix();
           if(price < min_value){
              min_value = price;
              index = i;
           }
       }
       Hotel CheapHotel = response.get(index);
       System.out.println(CheapHotel.getNom());
       return CheapHotel.getIdentifier()+","+CheapHotel.getNom()+","+CheapHotel.getPrix()*hs.getDuration();           
       
   }*/
   
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
