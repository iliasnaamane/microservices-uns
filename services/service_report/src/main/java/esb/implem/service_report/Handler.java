/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.implem.service_report;

import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author obisama
 */
public class Handler {
        private static MongoCollection reports = getReportCollection();
      
       //#########################################     
    public static JSONObject appendRapport(JSONObject input)
    {
    	try {
    	report rp=new report() ;
    	spending sp;
    	JSONObject jsonObj=ListByIdRapport(input);
    	try {
    	rp=new report(jsonObj);
    	spending[] newsp;
    	
    	JSONArray jsArr=input.getJSONArray("evidences");
    	double total=0;
    	for ( int i=0 ; i<jsArr.length();i++)
    	{	
    		total=0;
    		sp=new spending(jsArr.getJSONObject(i));
    		
    		spending[] evidences=rp.getEvidences();
    		if(evidences!=null)
    		{
    			newsp=new spending[evidences.length+1];
    			for(int j=0 ; j<evidences.length;j++)
    			{
    				newsp[j]=evidences[j];
    				total+=newsp[j].getAmount();
    			}
    			newsp[evidences.length]=sp;
    			total +=newsp[evidences.length].getAmount();
    		}
    		else 
    		{
    		newsp=new spending[1];
    		newsp[0]=sp;
    		}
    		rp.setEvidences(newsp);	
    		rp.setTotal_cost(total);
    			}
    		} catch(Exception e){e.printStackTrace();}
    	
    	reports.update("{report_id: "+rp.getReport_id()+"}").with(rp);
    	
    return new JSONObject().put("Updat result", rp.toJson());
    }
    catch(Exception e ) 
	{
		throw new RuntimeException("Something went wrong" + e.toString());    	
	}	
    }
     //#########################################
    
    public static JSONObject CreateRapport(JSONObject input)
    { 
    	 try {	//reports.insert(new report(input.getJSONObject("body")));
    	MongoCursor<report> cursor=reports.find().sort("{report_id:-1}").limit(1).as(report.class);
    	int max_id=0;
    	try {
    		report troro =cursor.next();
    		max_id=troro.getReport_id();
    	}
    	catch(Exception e) {e.printStackTrace();}
    	
    	report newreport=new report(input.getInt("employee_id"),max_id+1);
    	reports.insert(newreport);
    	
    	
    return new JSONObject().put("inserted", "true").put("report", newreport.toJson());
    }
    catch(Exception e ) 
	{
		throw new RuntimeException("Something went wrong"+ e.toString());    	
	}	
    }
    
    //#########################################
    
    public static JSONObject ListRapports(JSONObject input)
    {
      try { //  String id_employe = input.getString("id_employe");
    	  MongoCursor<report> cursor;
    	  try {
    		  
    		  cursor = reports.find("{complete:"+input.getBoolean("submitted")+"}").as(report.class);
    	  }
    	  catch(JSONException e)
    	  {
    		  cursor = reports.find().as(report.class);
    	  }
        cursor.toString();
       JSONArray contents = new JSONArray(); 
       while(cursor.hasNext()) {
            contents.put(cursor.next().toJson());
        }
       
       return new JSONObject().put("reports", contents);
    }
    catch(Exception e ) 
	{
		throw new RuntimeException("Something went wrong "+ e.toString());    	
	}	
    }
    
    //#########################################
    
    public static JSONObject ListByIdRapport(JSONObject input)
    {try {
    	System.out.println("report_id: "+input.getInt("report_id")+"}");
    	report cursor = reports.findOne("{report_id: "+input.getInt("report_id")+"}").as(report.class);
    	return cursor.toJson();
    }
    catch(Exception e ) 
	{
		throw new RuntimeException("The report_Id is not correct");    	
	}	
    }

    //#########################################
    static JSONObject submitRapport(JSONObject input) {
    	try {
    	report rp = reports.findOne("{report_id: "+input.getInt("report_id")+"}").as(report.class);
        rp.setComplete(true);
        
        reports.update("{report_id: "+rp.getReport_id()+"}").with(rp);
    	}
        catch(Exception e ) 
    	{
    		throw new RuntimeException("The report_Id is not correct");    	
    	}	
            return new JSONObject().put("result", "success");
    } 
    //#########################################
  
    public static JSONObject deleteRapport(JSONObject input) {
    	
    	try {
        	int rp_id=input.getInt("report_id");
        	reports.remove("{report_id: "+rp_id+"}");
        	}
        	catch(Exception e ) 
        	{
        		throw new RuntimeException("The report_Id is not correct");    	
        	}
            return new JSONObject().put("result", "success");
    }
    //#########################################
    
 private static MongoCollection getReportCollection() {
  try {    
	 MongoClient client = new MongoClient(Network.HOST, Network.PORT);
        return new Jongo(client.getDB(Network.DATABASE)).getCollection(Network.COLLECTION);
 }
 catch(Exception e ) 
	{
		throw new RuntimeException("A Problem with Mongo, make sure that your Mango instance is available");    	
	}	   
 }



//
//    private static MongoCollection citizens = getCitizens();
//
//    static JSONObject register(JSONObject input) {
//        Citizen data = new Citizen(input.getJSONObject("citizen"));
//        citizens.insert(data);
//        return new JSONObject().put("inserted", true).put("citizen",data.toJson());
//    }
//
//    static JSONObject delete(JSONObject input) {
//        String ssn = input.getString("ssn");
//        Citizen theOne = citizens.findOne("{ssn:#}",ssn).as(Citizen.class);
//        if (null == theOne) {
//            return new JSONObject().put("deleted", false);
//        }
//        citizens.remove(new ObjectId(theOne._id));
//        return new JSONObject().put("deleted", true);
//    }
//
//    static JSONObject list(JSONObject input) {
//        String filter = input.getString("filter");
//        MongoCursor<Citizen> cursor =
//                citizens.find("{lastName: {$regex: #}}", filter).as(Citizen.class);
//        JSONArray contents = new JSONArray(); int size = 0;
//        while(cursor.hasNext()) {
//            contents.put(cursor.next().toJson()); size++;
//        }
//        return new JSONObject().put("size", size).put("citizens", contents);
//    }
//
//    static JSONObject dump(JSONObject input) {
//        return list(new JSONObject().put("filter",".*"));
//    }
//
//    static JSONObject purge(JSONObject input) {
//        if(input.getString("use_with").equals("caution")) {
//            citizens.drop();
//            return new JSONObject().put("purge", "done");
//        }
//        throw new RuntimeException("Safe word does not match what is expected!");
//    }
//
//    static JSONObject retrieve(JSONObject input) {
//        String ssn = input.getString("ssn");
//        Citizen theOne = citizens.findOne("{ssn:#}",ssn).as(Citizen.class);
//        if (theOne == null) {
//            throw new RuntimeException("No match found for " + ssn);
//        }
//        return theOne.toJson();
//    }
//
//    private static MongoCollection getCitizens() {
//        MongoClient client = new MongoClient(Network.HOST, Network.PORT);
//        return new Jongo(client.getDB(Network.DATABASE)).getCollection(Network.COLLECTION);
//    }



    
}
