/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data.Flight;
import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author obisama
 */

public class vol implements Serializable,ItemPriceComparator {
     String date=null;
     String prix=null;
     String destination=null;
   
     String id="1";
     String origine=null;

    @Override
    public String toString() {
        return "vol{" + "\n"+
                "'date'='" + date + "', \n"+
                "'prix'='" + prix + "', \n"+
                "'origine'='" + origine +"' , \n"+
                "'destination'='" + destination + "', \n"+
                "'id'='" + id + "', \n"+
                               '}';
    }

    public vol() {
    }

    public vol(String date, String prix, String cmpny, String nb_escales, String destination, String rating, String duree, String id, String origine) {
        this.date = date;
        this.prix = prix;
        
        this.destination = destination;
        
        this.id = id;
        this.origine = origine;
    }
       public vol(JsonObject jsn){
             this.prix=(jsn.get("prix").getAsString());
             this.destination=(jsn.get("destination").getAsString());
             this.date=(jsn.get("date").getAsString());
             this.origine=(jsn.get("origine").getAsString());
            
             this.id=(jsn.get("id").getAsString());
             
       }
   
     
    public String getDate() {
        return date;
    }
    
 

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

   

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

   
    public String tobusnissTJson(){
       String CheapFlight = 
"\n" +
"   \"flight\":" +
"     {\n" +
"       \"id_flight\":\"1\",\n" +
"       \"price\": "+getPrice()+"\n" +
"     }\n" ;
          
      return CheapFlight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    @Override
    public String getPrice() {
    return prix+"";
    }
}


