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
    private static final int INDENT_FACTOR = 2;

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response process(String input) {
       
		
	   JSONObject obj = new JSONObject(input);
	   try {
		   switch (EVENT.valueOf(obj.getString("event"))) {
		   case Search :return Response.ok().entity(Return_response(obj).toString(INDENT_FACTOR)).build();
		   }
       
	   	}catch(Exception e) {
	   		JSONObject error = new JSONObject().put("error", e.toString());
    		return Response.status(400).entity(error.toString(INDENT_FACTOR)).build();
	   	}
	   
	  return null;
	}
	
	
}
