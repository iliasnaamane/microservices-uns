package Document.vols;


import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import static Document.vols.vol_collection.*;

@Path("vols")
@Produces(MediaType.APPLICATION_JSON)
public class annuaire_des_Vols {

	@POST
   // @Consumes(MediaType.APPLICATION_JSON)
    public Response process(String input) {
        JSONObject obj = new JSONObject(input);
       Boolean aller_retour= Boolean.valueOf(obj.getString("aller_retour")) ;
      
       Date date_aller=new Date(obj.getString("date_aller"));
       Date date_retour=null;
       if(aller_retour==true) {
    	   date_retour=new Date(obj.getString("date_arrive"));
       }
   
       switch (sort_preference.valueOf(obj.getString("sortBy"))) {
       
       case PRIX:Response.ok().entity(list(obj,date_aller,date_retour,"PRIX").toString()).build();
       case RATING:Response.ok().entity(list(obj,date_aller,date_retour,"RATING").toString()).build();
       case ID:Response.ok().entity(list(obj,date_aller,date_retour,"ID").toString()).build();
       case DUREE:Response.ok().entity(list(obj,date_aller,date_retour,"DUREE").toString()).build();
       }
      
    
      
       return null;
        
	
	}
	
	  @GET
	 // @Produces(MediaType.APPLICATION_JSON)
     public String returnanote() 
     {
  	   return "Hello mother fucker";
     }
	
	
}
