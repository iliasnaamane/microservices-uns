/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author obisama
 */

public class vol implements Serializable {
     String date;
     double   prix;
     String     cmpny;
     int       nb_escales;
     String    destination;
     double     rating;
     double      duree;
     int       id;
     String    origine;

    @Override
    public String toString() {
        return "Cheapestflight {" + "'date'='" + date + "', \n"+
                "'prix'='" + prix + "', \n"+
                "'cmpny'='" +cmpny + "', \n"+
                "'nb_escales'='" + nb_escales + "', \n"+
                "'destination'='" + destination + "', \n"+
                "'rating'='" + rating + "', \n"+
                "'duree'='" + duree +"', \n"+
                "'id'='" + id + "', \n"+
                "'origine'='" + origine +"' \n"+ '}';
    }

    public vol() {
    }

    public vol(String date, double prix, String cmpny, int nb_escales, String destination, double rating, double duree, int id, String origine) {
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

     
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCmpny() {
        return cmpny;
    }

    public void setCmpny(String cmpny) {
        this.cmpny = cmpny;
    }

    public int getNb_escales() {
        return nb_escales;
    }

    public void setNb_escales(int nb_escales) {
        this.nb_escales = nb_escales;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }
}

