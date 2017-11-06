package ocrDoc;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

import static ocrDoc.Img.*;

@Path("/ocr")
@Produces(MediaType.APPLICATION_JSON)
public class Ocr {

    private static final int INDENT_FACTOR = 2;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response process(String input) {
        JSONObject obj = new JSONObject(input);
        try {
            switch (EVENT.valueOf(obj.getString("nomImage"))) {
                case image1:
                    return Response.ok().entity(price1(obj).toString(INDENT_FACTOR)).build();
                case image2:
                    return Response.ok().entity(price2(obj).toString(INDENT_FACTOR)).build();
                case image3:
                    return Response.ok().entity(price3(obj).toString(INDENT_FACTOR)).build();
            }
        }catch(Exception e) {
            JSONObject error = new JSONObject().put("error", e.toString());
            return Response.status(400).entity(error.toString(INDENT_FACTOR)).build();
        }
        return null;
    }

}