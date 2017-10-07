/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carRental.data;

import java.util.Date;

/**
 *
 * @author iliasnaamane
 */
public class Rent {
    private int idRent;
    private int idCar;
    private Date dateStartOccup;
    private Date dateEndOccup;

    public int getIdCar() {
        return idCar;
    }

    public Date getDateStartOccup() {
        return dateStartOccup;
    }

    public Date getDateEndOccup() {
        return dateEndOccup;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public void setDateStartOccup(Date dateStartOccup) {
        this.dateStartOccup = dateStartOccup;
    }

    public void setDateEndOccup(Date dateEndOccup) {
        this.dateEndOccup = dateEndOccup;
    }

    public int getIdRent() {
        return idRent;
    }

    public void setIdRent(int idRent) {
        this.idRent = idRent;
    }
    
   
    
}
