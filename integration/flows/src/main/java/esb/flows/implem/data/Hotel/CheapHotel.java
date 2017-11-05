package esb.flows.implem.data.Hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iliasnaamane
 */
public class CheapHotel  implements Serializable {
    @JsonProperty private String identifier;
    @JsonProperty private int duration;
    @JsonProperty private int price;
    
    

    public String getIdentifier() {
        return identifier;
    }

    public int getDuration() {
        return duration;
    }


    public int getPrice() {
        return price;
    }
    
   

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CheapHotel{" + "identifier=" + identifier + ", duration=" + duration + ", price=" + price + '}';
    }
    
    
    
}
