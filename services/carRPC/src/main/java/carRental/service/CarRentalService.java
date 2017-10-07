package carRental.service;

import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import carRental.data.*;
import java.util.ArrayList;
import java.util.Date;

@WebService(name = "CarRental", targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/")
public interface CarRentalService {

    @WebResult(name = "carSearch_result")
    ArrayList<Car> getCarsByPreferences(@WebParam(name = "place") String place,
            @WebParam(name = "dateStart") String dateStart,@WebParam(name = "dateEnd") String dateEnd  ) throws Exception;
    
}
