/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.data.Car;

import java.util.Map;

/**
 *
 * @author iliasnaamane
 */
public class CarExternalForm {
    
    private int id;
    private String make;
    private String model;
    private int year;
    private Map<String,String> agency;
    private Float priceperday;

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Map<String, String> getAgency() {
        return agency;
    }

    public Float getPriceperday() {
        return priceperday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAgency(Map<String, String> agency) {
        this.agency = agency;
    }

    public void setPriceperday(Float priceperday) {
        this.priceperday = priceperday;
    }
    
    
    
}
