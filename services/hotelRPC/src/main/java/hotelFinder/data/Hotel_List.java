package hotelFinder.data;

import java.util.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hotel_List {
       // this mocks a database.
        
        @XmlElementWrapper
        @XmlElement
	private static ArrayList<Hotel> hotels = new ArrayList< Hotel>();
        
	public static void add(int id,String lieu,int prix,String nom) {
		hotels.add(new Hotel(id,lieu,prix,nom));
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
		Hotel_List.add(1,"Paris",30,"MetroDODO");
		Hotel_List.add(2,"Paris",1000,"Dododoré");
		Hotel_List.add(3,"Paris",25,"Le Carton Confortable");
		Hotel_List.add(4,"Londre",200,"Le gros coussin");
		Hotel_List.add(5,"Londre",2000,"the Sleeping Beauty");
		Hotel_List.add(6,"Londre",20,"Lit Douillé");
		Hotel_List.add(7,"Nice",40,"RonRon");

		//Storage.create("demogen");
	}

}