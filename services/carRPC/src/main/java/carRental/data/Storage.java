package carRental.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.cxf.configuration.spring.SimpleBeanDefinitionParser;

public class Storage {

    private static ArrayList<Car> CarsData = new ArrayList<Car>();
    private static ArrayList<Rent> RentsData = new ArrayList<Rent>();

    public static void createCar(int idCar, String place, float prix, String marque, String modele) {
        Car c = new Car();
        c.setMarque(marque);
        c.setModele(modele);
        c.setIdCar(idCar);
        c.setPrix(prix);
        c.setPlace(place);
        CarsData.add(c);
    }
    
    public static void createRent(int idRent, int idCar, Date dateStartOccup, Date dateEndOccup){
        Rent r = new Rent();
        r.setIdRent(idRent);
        r.setIdCar(idCar);
        r.setDateStartOccup(dateStartOccup);
        r.setDateEndOccup(dateEndOccup);
        RentsData.add(r);
    }
    public static void deleteCar(Car voiture) {
        
        CarsData.remove(voiture);
    }
    
    public static void deleteRent(Rent rent){
        RentsData.remove(rent);
    }
    
    public static ArrayList<Car> getCars(){
        return CarsData;
    }
    
    public static ArrayList<Rent>getRents(){
        return RentsData;
    }

    static {
        Storage.createCar(1,"Londre",30.5F,"Seat","Ibiza");
        Storage.createCar(2, "Paris", 30.5F, "Renaut", "Megane");
        Storage.createCar(3, "NewYork", 30.5F, "Renaut", "Ibiza");
        Storage.createCar(4, "Washinton", 30.5F, "Volswagen", "Golf5");
        Storage.createCar(5,"Londre",30.5F,"Seat","Leon");
      
        try {
            Storage.createRent(1,1, new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-23"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-26"));
            Storage.createRent(2, 1,new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-29"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-01"));
            Storage.createRent(3, 2, new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-17"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-24"));
            Storage.createRent(4, 2,  new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-23"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-25"));
             Storage.createRent(5,5, new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-23"), new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-26"));
        } catch (ParseException ex) {
            Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        
        
    }
}
