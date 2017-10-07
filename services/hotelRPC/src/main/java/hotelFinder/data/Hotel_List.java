package hotelFinder.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
public class Hotel_List {
       // this mocks a database.
        
        @XmlElementWrapper
        @XmlElement
	private static ArrayList<Hotel> hotels = new ArrayList< Hotel>();
        
	public static void add(String lieu,int prix,String nom) {
		hotels.add(new Hotel(lieu,prix,nom));
	}
        @XmlElement
	public static Hotel getHotel(int index) {
		return hotels.get(index);
	}
        
	public static void delete(Hotel h) {
		hotels.remove(h);
	}
        @XmlElementWrapper
        @XmlElement
        public static ArrayList<Hotel> getHotels() {
            return hotels;
        }

	static {
		Hotel_List.add("Paris",30,"MetroDODO");
		Hotel_List.add("Paris",1000,"Dododoré");
		Hotel_List.add("Paris",2,"Le Carton Confortable");
		Hotel_List.add("Paris",200,"Le gros coussin");
		Hotel_List.add("Paris",2000,"the Sleeping Beauty");
		Hotel_List.add("Paris",20,"Lit Douillé");
		Hotel_List.add("Paris",40,"RonRon");

		//Storage.create("demogen");
	}

}