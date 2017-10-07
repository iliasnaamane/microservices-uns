package hotelFinder.service;

import hotelFinder.data.*;
import java.util.ArrayList;

import javax.jws.WebService;
import java.util.Date;
import java.util.Collection;
import java.util.Collections;
import javax.xml.bind.annotation.XmlElement;


@WebService(targetNamespace   = "http://informatique.polytech.unice.fr/soa1/cookbook/",
		    portName          = "ExternalHotelFinderPort",
		    serviceName       = "ExternalHotelFinderService",
		    endpointInterface = "hotelFinder.service.HotelFinderService")
public class HotelFinderImpl implements HotelFinderService{

	public ArrayList<Hotel> recherche(String lieu,int duree, boolean asc){
		
                ArrayList<Hotel> newHotelList = new ArrayList<Hotel>();
                for(Hotel h : Hotel_List.getHotels()){
                    newHotelList.add(h);
                }
                Collections.sort(newHotelList);
		for (Hotel h : newHotelList) {
                    h.setPrix(duree*h.getPrix());
		}
                if(!asc)
                    Collections.reverse(newHotelList);
		return newHotelList;
	}

  
}

// localhost:8080/