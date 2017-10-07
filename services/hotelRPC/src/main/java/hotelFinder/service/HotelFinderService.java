package hotelFinder.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import hotelFinder.data.*;
import java.util.ArrayList;

@WebService(name="HotelFinderService", targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/")
public interface HotelFinderService {

	@WebResult(name="recherche_hotel")
	public ArrayList<Hotel> recherche(@WebParam(name="lieu") String lieu,@WebParam(name="dure") int dure, boolean ascOrdesc);

}