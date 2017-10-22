/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils;

import esb.flows.implem.data.HotelSpec;
import static esb.flows.implem.utils.Endpoints.HOTEL_RPC_ENDPOINT;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.ws.BindingProvider;
import stubs.hotel_rpc.ExternalHotelFinderService;
import stubs.hotel_rpc.Hotel;
import stubs.hotel_rpc.HotelFinderService;

/**
 *
 * @author iliasnaamane
 */
public class HotelSearchHelper {
   
   
   public String getCheapHotel(HotelSpec hs){
       System.out.println("ça passe par ici");
       HotelFinderService hf = getWS();
       ArrayList<Hotel> response = new ArrayList<Hotel>();
       response = (ArrayList<Hotel>)hf.recherche(hs.getDest(),hs.getDuration(),true);
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
       return CheapHotel.getIdentifier()+","+CheapHotel.getNom()+","+CheapHotel.getPrix()*hs.getDuration();           
       
   }
   
    private HotelFinderService getWS() {
        URL wsdl = HotelSearchHelper.class.getResource("HOTEL.wsdl");
        ExternalHotelFinderService factory = new ExternalHotelFinderService(wsdl);
        HotelFinderService ws = factory.getExternalHotelFinderPort();
        String address = HOTEL_RPC_ENDPOINT;
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }
}
