/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.implem.service_report;
import org.json.JSONObject;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author obisama
 */

@Path("reportspendings")
@Produces(MediaType.APPLICATION_JSON)

public class mainreport {
    
    private static final int INDENT_FACTOR = 2;
   
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it too baby!";
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response process(String input) {
        JSONObject obj = new JSONObject(input);
        System.out.println("Receving request with JSON nested object: " + obj.toString());
        try {
            JSONObject response = null;
            switch ((obj.getString("event"))) {
                case "Append":
                    response = Handler.appendRapport(obj);        //submitSpends(obj.getJSONObject("spends"));
                    break;
                case "List":
                    response = Handler.ListRapports(obj);//approveSpends(obj.getInt("id"));
                    break;
                case "Create":
                    response = Handler.CreateRapport(obj);//rejectSpends(obj.getInt("id"));
                    break;
                case "ListById":
                    response = Handler.ListByIdRapport(obj);//retrieveSpends(obj.getInt("id"));
                    break;
                case "Submit":
                    response = Handler.submitRapport(obj);//retrieveSpends(obj.getInt("id"));
                    break;
                case "delete":
                    response = Handler.deleteRapport(obj);//retrieveSpends(obj.getInt("id"));
                    break;
            }

            if (response != null)
                return Response.ok().entity(response.toString(INDENT_FACTOR)).build();

        }catch(Exception e) {
            JSONObject error = new JSONObject().put("Error, please mind reading the message: ", e.toString());            
            return Response.status(400).entity(error.toString(INDENT_FACTOR)).build();
        }
        return null;
    }
}
