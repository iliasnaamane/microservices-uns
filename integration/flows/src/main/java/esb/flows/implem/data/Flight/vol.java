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
     String cmpny=null;
     String nb_escales=null;
     String destination=null;
     String rating=null;
     String duree=null;
     String id=null;
     String origine=null;

    @Override
    public String toString() {
        return "Cheapestflight {" + "\n"+
                "'date'='" + date + "', \n"+
                "'prix'='" + prix + "', \n"+
                "'origine'='" + origine +"' , \n"+
                "'destination'='" + destination + "', \n"+
                "'id'='" + id + "', \n"+
                "'cmpny'='" +cmpny + "', \n"+
                "'nb_escales'='" + nb_escales + "', \n"+
                "'rating'='" + rating + "', \n"+
                "'duree'='" + duree +"' \n"+
                '}';
    }

    public vol() {
    }

    public vol(String date, String prix, String cmpny, String nb_escales, String destination, String rating, String duree, String id, String origine) {
        this.date = date;
        this.prix = prix;
        this.cmpny = cmpny;
        this.nb_escales = nb_escales;
        this.destination = destination;
        this.rating = rating;
        this.duree = duree;
        this.id = id;
        this.origine = origine;
    }
       public vol(JsonObject jsn){
             this.prix=(jsn.get("prix").getAsString());
             this.destination=(jsn.get("destination").getAsString());
             this.date=(jsn.get("date").getAsString());
             this.origine=(jsn.get("origine").getAsString());
             this.cmpny=(jsn.get("cmpny").getAsString());
             this.duree=(jsn.get("duree").getAsString());
             this.id=(jsn.get("id").getAsString());
             this.nb_escales=(jsn.get("nb_escales").getAsString());
             this.rating=(jsn.get("rating").getAsString());
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

    public String getCmpny() {
        return cmpny;
    }

    public void setCmpny(String cmpny) {
        this.cmpny = cmpny;
    }

    public String getNb_escales() {
        return nb_escales;
    }

    public void setNb_escales(String nb_escales) {
        this.nb_escales = nb_escales;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
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


