/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data.Hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author iliasnaamane
 */
public class HotelSpec implements Serializable  {
    
    @JsonProperty private String dest;
    @JsonProperty private int duration;
    @JsonProperty private boolean sortedAscorDesc;

    public String getDest() {
        return dest;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isSortedAscorDesc() {
        return sortedAscorDesc;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSortedAscorDesc(boolean sortedAscorDesc) {
        this.sortedAscorDesc = sortedAscorDesc;
    }

    @Override
    public String toString() {
        return "HotelSpec{" + "dest=" + dest + ", duration=" + duration + ", sortedAscorDesc=" + sortedAscorDesc + '}';
    }
    
    
    
    
}
