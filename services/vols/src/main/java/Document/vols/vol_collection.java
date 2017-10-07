package Document.vols;

import com.mongodb.MongoClient;

import java.util.Date;

import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONObject;


public class vol_collection {
	
	
	
	
	 static JSONObject list(JSONObject input,Date aller,Date retour,String sortBy) {
		 JSONObject vols=null;
		 if(retour==null)
		 {
			  vols =list(input,aller,sortBy);  
		 }else 
		 {
		 vols.put("Vol_aller", list(input,aller,sortBy));
		 vols.put("Vol_retour", list(input,retour,sortBy));
		 }
	       
	        return new JSONObject().put("Vols", vols);
	    }
	 
	 static JSONObject list(JSONObject input,Date aller,String sortBy) {
	        MongoCollection vols_coll = getVols();
	        MongoCursor<vol> cursor ;
	       switch(sortBy)
	       {
	       case "PRIX":cursor =vols_coll.find("{date_depart:#}",aller).sort("{PRIX: 1}").as(vol.class);
	       case"RATING":cursor=vols_coll.find("{date_depart:#}",aller).sort("{RATING: 1}").as(vol.class);
	       case"DUREE":cursor=vols_coll.find("{date_depart:#}",aller).sort("{DUREE: 1}").as(vol.class);
	       case"ID":cursor=vols_coll.find("{date_depart:#}",aller).sort("{ID: 1}").as(vol.class);
	       default :cursor=vols_coll.find("{date_depart:#}",aller).sort("{ID: 1}").as(vol.class);
	       }
	        
	        cursor=vols_coll.find().as(vol.class);
	       
	        JSONArray contents = new JSONArray(); int size = 0;
	        while(cursor.hasNext()) {
	            contents.put(cursor.next().toJson()); size++;
	        }
	        return new JSONObject().put("size", size).put("VOL_DATE_DE",aller).put("sorted_vols", contents);
	    }
	
	private static MongoCollection getVols() {
	        MongoClient client = new MongoClient(DB_connection.HOST, DB_connection.PORT);
	        return new Jongo(client.getDB(DB_connection.DATABASE)).getCollection(DB_connection.COLLECTION);
	    }
}
