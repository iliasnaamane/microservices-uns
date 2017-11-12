/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Processors;




import com.google.gson.Gson;
import esb.flows.implem.data.Hotel.CheapHotel;
import esb.flows.implem.data.Hotel.CheapHotelExternal;
import esb.flows.implem.data.Hotel.HotelSpec;
import esb.flows.implem.utils.Helpers.HotelHelper;
import java.util.ArrayList;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import stubs.hotel_rpc.Hotel;
import stubs.hotel_rpc.HotelFinderService;


/**
 *
 * @author iliasnaamane
 */


/**
 * This class contain all hotel processors
 * 
 */
public class HotelProcessors {
    
    /**
     *  transform cheapest hotel from both services to Json 
     */
    public static final Processor RequestRESTBusinessTravel = (Exchange exchange) -> {
        CheapHotel cheapHotel = (CheapHotel)exchange.getIn().getBody();
         String CheapHotelJson = "{\n" +
"       \"id_hotel\":\""+cheapHotel.getIdentifier() +"-service-1,\n" +
"       \"nights\":"+ cheapHotel.getDuration()+",\n" +
"       \"price\":"+ cheapHotel.getPrice() + "\n" +
"       }";
        exchange.getIn().setBody(CheapHotelJson);
        
    };
    
    
    
    /**
     *  transform cheapest hotel from both services to Json 
     */
    public static final Processor hotel2Json = (Exchange exchange) -> {
        CheapHotel cheapHotel = (CheapHotel)exchange.getIn().getBody();
        String CheapHotelJson;
        if(cheapHotel.getIdentifier().equals("00")){
             CheapHotelJson
                    = "\n"
                    + "   \"hotel\":"
                    + "     {\n"
                    + "       \"id_hotel\":\"\",\n"
                    + "       \"price\":\n"
                    + "     }\n";
        }
        else{
            CheapHotelJson = 
             "\n" +
             "   \"hotel\":"+
             "     {\n" +
             "       \"id_hotel\":\""+cheapHotel.getIdentifier()+"\",\n" +
             "       \"price\": "+cheapHotel.getPrice()+"\n" +
             "     }\n" 
                         ;
        }
  
        exchange.getIn().setBody(CheapHotelJson);
        
    };
    
    
    /**
     * transform Json output from REST external service to Cheap Hotel Object 
     */
    public static final Processor json2CheapHotel = (Exchange exchange) -> {
        String outputJson = (String)exchange.getIn().getBody();
        String jsonObject = outputJson.replaceAll("[\\[\\]]","");; // using regex to remove array brackets
        Gson gson = new Gson();
        CheapHotelExternal chExternal = gson.fromJson(jsonObject, CheapHotelExternal.class);
        CheapHotel ch = new CheapHotel();
        ch.setIdentifier("service-2");
        ch.setPrice(chExternal.getRoomCost());
       
        exchange.getIn().setBody(ch);
        
        
    };
    
    /**
     * transform csv to hotel specification ( object HotelSpec )
     */
    public static final Processor cv2hotelSpec = (Exchange exchange) -> {
        //System.out.println("cv2hotelSpec");
        Map<String, String> data = (Map<String, String>) exchange.getIn().getBody();
        HotelSpec hs = new HotelSpec();
        data.entrySet().forEach((Map.Entry<String, String> entry) -> {
            if(entry.getKey().equals("duration")){
                hs.setDuration(Integer.parseInt(entry.getValue()));
            }
                
            else{
                hs.setDest(entry.getValue());
            }
             
        });
        
        hs.setSortedAscorDesc(true);

        exchange.getIn().setBody(hs);
        
    };
   
    
    
    public static Processor RequestRPC = (Exchange exchange) -> {
        HotelSpec hs = (HotelSpec) exchange.getIn().getBody();
        HotelFinderService hf = HotelHelper.getWS();
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
        Hotel H = response.get(index);
        CheapHotel CheapHotel = new CheapHotel();
        CheapHotel.setIdentifier(H.getIdentifier());
        CheapHotel.setDuration(hs.getDuration());
        CheapHotel.setPrice(min_value);
       /* String CheapHotelJson = "{\n" +
"       \"id_hotel\":\""+CheapHotel.getIdentifier() +"-service-1,\n" +
"       \"nights\":"+ hs.getDuration()+",\n" +
"       \"price\":"+ total_price + "\n" +
"       }";*/
        exchange.getIn().setBody(CheapHotel);

    };
    
     public static final Processor RequestREST = (Exchange exchange) -> {
        HotelSpec hs = (HotelSpec) exchange.getIn().getBody();
        exchange.getIn().setHeader(Exchange.HTTP_QUERY,"destination="+hs.getDest());
        exchange.getIn().setBody(null);
        
    };
    
    
    
     public static final Processor fakeHotelBuilder = (Exchange exchange) -> {
       CheapHotel CheapHotel = new CheapHotel();
       CheapHotel.setIdentifier("00");
       CheapHotel.setDuration(0);
       CheapHotel.setPrice(Integer.MAX_VALUE);
       exchange.getIn().setBody(CheapHotel);
        
    };
    
   
}
