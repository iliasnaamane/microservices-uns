package hotelFinder.service;

import hotelFinder.data.*;
import java.util.ArrayList;

import javax.jws.WebService;
import java.util.Collections;


@WebService(targetNamespace   = "http://informatique.polytech.unice.fr/soa1/cookbook/",
		    portName          = "ExternalHotelFinderPort",
		    serviceName       = "ExternalHotelFinderService",
		    endpointInterface = "hotelFinder.service.HotelFinderService")
public class HotelFinderImpl implements HotelFinderService{

        @Override
	public ArrayList<Hotel> recherche(String lieu,int duree, boolean sortedAsc){
		
                ArrayList<Hotel> newHotelList = new ArrayList<>(Hotel_List.getHotels().size());
                Hotel_List.getHotels().stream().filter((h) -> (h.getLieu().equals(lieu))).forEachOrdered((h) -> {
                    newHotelList.add(h.clone());
            });                 
                Collections.sort(newHotelList);
                newHotelList.forEach((h) -> {
                    h.setPrix(duree*h.getPrix());
            });
                if(!sortedAsc)
                    Collections.reverse(newHotelList);
		return newHotelList;
	}

  
}

// localhost:8080/