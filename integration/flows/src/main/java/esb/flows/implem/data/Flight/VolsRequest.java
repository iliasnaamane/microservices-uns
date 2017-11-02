/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data.Flight;

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
 
    return map;
    }

    @Override
    public String toString() {
        return "vol{" +" Outbound_date=" + Outbound_date + ", from=" + from + ", to=" + to + '}';
    }
    
 
}
