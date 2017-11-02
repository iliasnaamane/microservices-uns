/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author iliasnaamane
 */
public class CheapHotelExternal implements Serializable{
   @JsonProperty private String city;
   @JsonProperty private String name;
   @JsonProperty private int roomCost;

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getRoomCost() {
        return roomCost;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomCost(int roomCost) {
        this.roomCost = roomCost;
    }

    @Override
    public String toString() {
        return "CheapHotelExternal{" + "city=" + city + ", name=" + name + ", roomCost=" + roomCost + '}';
    }
   
   
}
