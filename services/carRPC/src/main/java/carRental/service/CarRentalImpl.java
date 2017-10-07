package carRental.service;

import java.util.ArrayList;
import java.util.Collection;
import carRental.data.*;
import java.text.SimpleDateFormat;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/",
        portName = "ExternalCRentalPort",
        serviceName = "ExternalCarRentalService",
        endpointInterface = "carRental.service.CarRentalService")
public class CarRentalImpl implements CarRentalService {

    
    @Override
    public ArrayList<Car> getCarsByPreferences(String place, String dateStart, String dateEnd) throws Exception {
        ArrayList<Car> CarsByPreferences = new ArrayList<>();
        ArrayList<Car> CarsByPlace = this.getCarsByPlace(place);
        Date startDate = new SimpleDateFormat("YYYY-MM-DD").parse(dateStart);
        Date endDate = new SimpleDateFormat("YYYY-MM-DD").parse(dateEnd);
        CarsByPlace.forEach((c) -> {
            ArrayList<Rent> Rents = this.getRentByDatesAndCarId(c.getIdCar(), startDate, endDate);
            if (Rents.isEmpty()) {
                CarsByPreferences.add(c);
            }
        });
        if (CarsByPreferences.isEmpty())
            throw new Exception("no car dispo");
        return CarsByPreferences;
        
    }
    
    
    private ArrayList<Car> getCarsByPlace(String place){
      
        ArrayList<Car> CarsByPlace = new ArrayList<>();
        Storage.getCars().stream().filter((c) -> (c.getPlace().equals(place))).forEachOrdered((c) -> {
            CarsByPlace.add(c);
        });
        return CarsByPlace;
    }
    
    public boolean checkIntersectionDates(Date StartDate, Date EndDate, Date StartDateOccup, Date EndDateOccup) {
        if ( (EndDate.after(StartDateOccup) && EndDate.before(EndDateOccup))
                || (StartDate.after(StartDateOccup) && StartDate.before(EndDateOccup))
                || (StartDate.before(StartDateOccup) && EndDate.after(EndDateOccup))) {
            return true;
        }
        return false;
    }

    private ArrayList<Rent> getRentByDatesAndCarId(int carId, Date dateStart, Date dateEnd){
        ArrayList<Rent> RentsByDatesAndCarId = new ArrayList<>();
        Storage.getRents().stream().filter((r) -> (r.getIdCar() == carId && checkIntersectionDates(dateStart, dateEnd, r.getDateStartOccup(), r.getDateEndOccup()) )).forEachOrdered((r) -> {
                    RentsByDatesAndCarId.add(r);
        });
        
        return RentsByDatesAndCarId;
    }
    
   
    
}
