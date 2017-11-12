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
    		/*
 { "event": "POST",
"file": "image1",
"idBusinessTravel": "1",
"fin": "true",
"spending_no": 1,
"idEmploye": "1",
"Montant" : 520

}*/
    		report rpp=new report();
    	//on verifie si l'employé a deja un rapport non terminé ou pas 
    		try {
    			
    			JSONObject jsonObj=ListByIdEmployee(new JSONObject().put("employee_id", input.getInt("idEmploye")));
    			rpp=new report(jsonObj);
    		}
    		catch(Exception e )
    		{
    	// soit le rapport n'existe pas , ou bien le format Json pas valide
    			JSONObject jsono=new JSONObject();
    			int emp_id= input.getInt("idEmploye");
    			jsono.put("employee_id",emp_id);
    			int id_report = CreateRapport(jsono).getJSONObject("report").getInt("report_id");
    			rpp=new report(id_report,emp_id);
    			rpp.setBTravel_id(input.getInt("idBusinessTravel"));
    			rpp.setDuree_BT(input.getInt("duree_BT"));
    		}
    	
    	//on commance a construire l'evidence
    	spending sp=new spending();
    	sp.setEvidence_no(input.getInt("spending_no"));
    	sp.setAmount(input.getInt("Montant"));
    	sp.setEvidence_desc(input.getString("file"));
    	
    	//On ajoute maintenant le spending au rapport
    	
    	spending[] spends=rpp.getEvidences();
    	double total=sp.getAmount();
    	
    	spending[] new_spends;
    	try {
    	new_spends=new spending[spends.length+1];
  
		for(int j=0 ; j<spends.length;j++)
		{
			new_spends[j]=spends[j];
			total+=new_spends[j].getAmount();
		}
		new_spends[spends.length]=sp;
		total +=new_spends[spends.length].getAmount();
		
    	}
    	catch (Exception e)
    	{
    		//nullException e
    		new_spends=new spending[1];
    		new_spends[0]=sp;
    	}
    	JSONObject response =new JSONObject();
    	rpp.setEvidences(new_spends);
    	rpp.setComplete(input.getBoolean("fin"));
    	System.out.println("rpp "+rpp.toJson());
    	
    	if(rpp.getTotal_cost()==0)
    	{
    		rpp.setTotal_cost(total);
    		reports.insert(rpp);
    		response.put("Update result", "success");
    	}
    	else {
    	rpp.setTotal_cost(total);
    	reports.update("{report_id: "+rpp.getReport_id()+"}").with(rpp);
    	response.put("Update result","success");
    	}
    	System.out.println("rpp.getTotal_cost() "+rpp.getTotal_cost());
    	if(rpp.isComplete())
    	{	System.out.println("Tarifs.verif_rembo(rpp) "+Tarifs.verif_rembo(rpp));
    		if(Tarifs.verif_rembo(rpp))
    		{
    			rpp.setValide(true);
    			return new JSONObject().put("report",rpp.toJson());
    		}
    	} 
    	 
    	return response;
    	
    	}
    catch(Exception e ) 
	{
    	
		throw new RuntimeException("Report appending problem :"+e);    	
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
		throw new RuntimeException("Report Creation problem :"+e);    	
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
    	  try {
    		  cursor = reports.find("{valide:"+input.getBoolean("valide")+",complete:"+true+"}").as(report.class);
    	  }
    	  catch(JSONException ee)
    	  {
    		  cursor = reports.find().as(report.class);
    	  }
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
		throw new RuntimeException("Report Lisiting problem :"+e);    	
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
		throw new RuntimeException("Report Lisiting by id report problem :"+e);    	
	}	
    }
    //############################################
    public static JSONObject ListByIdEmployee(JSONObject input)
    {
    	try {
    	report cursor = reports.findOne("{employee_id: "+input.getInt("employee_id")+"}").as(report.class);
    	return cursor.toJson();
    }
    catch(Exception e ) 
	{
		throw new RuntimeException("Report Lisiting by id employee problem :"+e);    	
	}	
    }

    //#########################################
    static JSONObject submitRapport(JSONObject input) {
    	try {
    	report rp = reports.findOne("{report_id: "+input.getInt("report_id")+"}").as(report.class);
        rp.setComplete(true);
        
        reports.update("{report_id: "+rp.getReport_id()+"}").with(rp);
        return new JSONObject().put("result", "success");
    	}
        catch(Exception e ) 
    	{
    		throw new RuntimeException(e);    	
    	}	
            
    } 
    //#########################################
  
    public static JSONObject deleteRapport(JSONObject input) {
    	
    	try {
        	int rp_id=input.getInt("report_id");
        	reports.remove("{report_id: "+rp_id+"}");
        	 return new JSONObject().put("result", "success");
        	}
        	catch(Exception e ) 
        	{
        		throw new RuntimeException(e);    	
        	}
           
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






    
}
