package Document.vols;

import Document.vols.comparators.Comparateur_DUREE_DE_VOL;
import Document.vols.comparators.Comparateur_ID;
import Document.vols.comparators.Comparateur_NB_ESCALES;
import Document.vols.comparators.Comparateur_Prix;
import Document.vols.comparators.Comparateur_RATING;

import static Document.vols.vol_collection.One_Way_Duration;
import static Document.vols.vol_collection.One_Way_Price;
import static Document.vols.vol_collection.One_Way_Rating;
import static Document.vols.vol_collection.One_Way_Stops;
import static Document.vols.vol_collection.Return_Duration;
import static Document.vols.vol_collection.Return_Price;
import static Document.vols.vol_collection.Return_Rating;
import static Document.vols.vol_collection.Return_Stops;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.json.JSONArray;
import org.json.JSONObject;


public class vol_collection {
	
	public static JSONObject Return_response(JSONObject input)
	{
		
		 switch (input.getString("sortby")) {
	       
	       case "Price": return One_Way_Price(input,(input.getString("Outbound_date")));
	       case "Stops":return One_Way_Stops(input,(input.getString("Outbound_date")));
	       case "Duration":return One_Way_Duration(input,(input.getString("Outbound_date")));
	       case "Rating":return One_Way_Rating(input,(input.getString("Outbound_date")));
	       case "Price_r":return Return_Price(input,(input.getString("Outbound_date")),(input.getString("return_date")));
	       case "Stops_r":return Return_Stops(input,(input.getString("Outbound_date")),(input.getString("return_date")));
	       case "Duration_r":return Return_Duration(input,(input.getString("Outbound_date")),(input.getString("return_date")));
	       case "Rating_r":return Return_Rating(input,(input.getString("Outbound_date")),(input.getString("return_date")));
	}
		 return null;
	}
	
	
	 static JSONObject One_Way_Price (JSONObject input,String aller) {
		 JSONObject vols=new JSONObject();		
		 vols.put("Outbound", list(input,aller,"PRICE"));		 
		 return new JSONObject().put("Flights", vols);
	    }
	 static JSONObject One_Way_Rating (JSONObject input,String aller) {
		 	JSONObject vols=new JSONObject();	 
		 	vols.put("Outbound", list(input,aller,"RATING"));
			return new JSONObject().put("Flights", vols);
	    }
	 static JSONObject One_Way_Duration (JSONObject input,String aller) {
		 JSONObject vols=new JSONObject();
		 vols.put("Outbound", list(input,aller,"DURATION"));
		 return new JSONObject().put("Flights", vols);
	    }
	 static JSONObject One_Way_Stops (JSONObject input,String aller) {
		 JSONObject vols=new JSONObject();
		
		 vols.put("Outbound", list(input,aller,"STOPS"));
		 
	        return new JSONObject().put("Flights", vols);
	    }
	 static JSONObject Return_Price (JSONObject input,String aller,String retour) {
		 JSONObject vols=new JSONObject();
		
		 vols.put("Outbound", list(input,aller,"PRICE"));
		 vols.put("Return", list(input,retour,"PRICE"));
		 
		 return new JSONObject().put("Flights", vols);
	    }
	 static JSONObject Return_Rating (JSONObject input,String aller,String retour) {
		 JSONObject vols=new JSONObject();
		
		 vols.put("Outbound", list(input,aller,"RATING"));
		 vols.put("Return", list(input,retour,"RATING"));
		 
		 return new JSONObject().put("Flights", vols);
	    } 
	 static JSONObject Return_Duration (JSONObject input,String aller,String retour) {
		 JSONObject vols=new JSONObject();
		 
		
		 vols.put("Outbound", list(input,aller,"DURATION"));
		 vols.put("Return", list(input,retour,"DURATION"));
		 
		 return new JSONObject().put("Flights", vols);
	    } 
	 static JSONObject Return_Stops (JSONObject input,String aller,String retour) {
		 JSONObject vols=new JSONObject();
		
		 vols.put("Outbound", list(input,aller,"STOPS"));
		 vols.put("Return", list(input,retour,"STOPS"));
		 
		 return new JSONObject().put("Flights", vols);
	    }
	 
	 static JSONObject list(JSONObject input,String aller,String sortBy) {
		 String from=input.getString("from");
		 String to=input.getString("to");
		 String date=aller;
		 
		 
		
		 
		 ArrayList<vol> vols_coll = getVols(from,to,date);
		 
	        
	       switch(sortBy)
	       {
	       case "PRICE":Collections.sort(vols_coll, new Comparateur_Prix());break;
	       case"RATING":Collections.sort(vols_coll, new Comparateur_RATING());break;
	       case"DURATION":Collections.sort(vols_coll, new Comparateur_DUREE_DE_VOL());break;
	       case"STOPS":Collections.sort(vols_coll, new Comparateur_NB_ESCALES());break;
	       default :Collections.sort(vols_coll, new Comparateur_ID());break;
	       }
	       JSONArray contents = new JSONArray();
	        for(int i=0 ; i<vols_coll.size();i++) 
	        {
	        	contents.put(vols_coll.get(i).toJson());        	
	        }    

	        return new JSONObject().put("sorted_flights", contents).put("Number_of_Results", vols_coll.size()).put("DATE",aller);
	    }
	
	private static ArrayList<vol> getVols(String from,String to,String date) {
		 ArrayList<vol> list=new ArrayList<vol>();//Creating arraylist  
	     list.add(new vol(1,"Nice","Paris","11-10-2017",5,3196.0,"Jetair",1,4));
	     list.add(new vol(2,"Nice","Paris","12-10-2017",5,2555.0,"AirFrance",1,4));
	     list.add(new vol(3,"Nice","Paris","12-10-2017",2.5,450.0,"Ryanair",1,4));
	     list.add(new vol(4,"Nice","Paris","12-10-2017",3,500.0,"Ryanair",1,4));
	     list.add(new vol(5,"Nice","Paris","13-10-2017",3,1000.0,"Virgin",1,4));
	     list.add(new vol(6,"Nice","Paris","14-10-2017",4,1213.0,"Turkish airlines",1,4));
	     list.add(new vol(7,"Nice","Paris","15-10-2017",2,255.0,"Emirate Fly",1,4));
	     list.add(new vol(8,"Nice","Paris","16-10-2017",5,400.0,"british Royal air",1,4));
	     list.add(new vol(9,"Nice","Paris","17-10-2017",4.5,1245.0,"Americain airlines",1,4));
	     
	     CollectionUtils.filter(list,  new FlightPredicates(from,to,date));

	    // Collection<vol> smallList = CollectionUtils.select(list, new FlightPredicates(from,to,date));
	     
	     return list;
	}
	    		 
		 
	
}
