package service;

import business.Hotel;
import business.HotelRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.HotelStorage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Antoine Aubé <aube.antoine@gmail.com>
 */
@Path("/hotels")
@Produces(MediaType.APPLICATION_JSON)
public class HotelService {

  private static DateFormat format =
      new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
  private static String ASC = "asc";
  private static String DESC = "desc";

  @GET
  public Response getTargetedHotels(
          @QueryParam("destination") String destination,
          @QueryParam("date") String stringDate,
          @QueryParam("price_ordering") String priceOrdering) {

    // Date in format DD-MM-YYYY.
    Date date;
    if (stringDate != null) {
      try {
        date = format.parse(stringDate);
      } catch (ParseException e) {
        date = null;
      }
    } else {
      date = null;
    }


    // Price ordering.
    Boolean ascendingOrder;
    if (ASC.equals(priceOrdering)) {
      ascendingOrder = true;
    } else if (DESC.equals(priceOrdering)) {
      ascendingOrder = false;
    } else {
      ascendingOrder = null;
    }

    HotelRequest request = new HotelRequest(destination, date, ascendingOrder);
    List<Hotel> hotels = HotelStorage.findAllBy(request);

    JSONArray jsonHotels = new JSONArray();
    for (Hotel hotel : hotels) {
      JSONObject jsonHotel = new JSONObject();

      jsonHotel.put("name", hotel.getName());
      jsonHotel.put("city", hotel.getCity());
      jsonHotel.put("roomCost", hotel.getRoomCost());

      jsonHotels.put(jsonHotel);
    }

    return Response.ok().entity(jsonHotels.toString(2)).build();
  }
}
