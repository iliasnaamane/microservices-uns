/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data.Flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author obisama
 */
public class VolsRequest implements Serializable {
    
 private String Outbound_date;
 private String from;
 private String to;
   //@JsonProperty private String isDirect;
  
    public VolsRequest( Map<String, Object> data )
    {
           from=((String) data.get("from"));
           to=((String) data.get("to"));
           Outbound_date=((String) data.get("Outbound_date")); 
    }

    public String getOutbound_date() {
        return Outbound_date;
    }

    public void setOutbound_date(String Outbound_date) {
        this.Outbound_date = Outbound_date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    public Map<String,String>  vol2Map()
    {
         Map<String,String> map = new HashMap<String,String>();
         map.put("to",to);
         map.put("from",from);
         map.put("Outbound_date",Outbound_date);
       //  map.put("isDirect", isDirect);
 
    return map;
    }

//    public String getIsDirect() {
//        return isDirect;
//    }
//
//    public void setIsDirect(String isDirect) {
//        this.isDirect = isDirect;
//    }

    @Override
    public String toString() {
        return "VolsRequest{" + "Outbound_date=" + Outbound_date + ", from=" + from + ", to=" + to + '}';
    }

    
 
}
