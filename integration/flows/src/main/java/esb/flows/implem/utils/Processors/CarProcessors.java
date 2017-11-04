package esb.flows.implem.utils.Processors;

import com.google.gson.Gson;
import esb.flows.implem.data.Car.CarForm;
import java.util.ArrayList;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import esb.flows.implem.data.Car.CarRequest;
import esb.flows.implem.utils.Helpers.CarRentalHelper;
import java.text.ParseException;

import stubs.car_rpc.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import static java.lang.Math.toIntExact;

/**
 *
 * @author maximeDjx
 */
public class CarProcessors {
   
	/**
     * transform csv to car request
     */
    public static final Processor csv2Request = (Exchange exchange) -> {
        //System.out.println("cv2hotelSpec");
        Map<String, String> data = (Map<String, String>) exchange.getIn().getBody();
        CarRequest cs = new CarRequest();
        data.entrySet().forEach((Map.Entry<String, String> entry) -> {
            //System.out.println(entry.getKey() + "," + entry.getValue());

            if(entry.getKey().equals("dateStart")){
                cs.setDateStart(entry.getValue());
                //System.out.println("DateStart: "+ entry.getValue());
            }
            else if(entry.getKey().equals("dateEnd")){
                cs.setDateEnd(entry.getValue());
                //System.out.println("DateEnd: " + entry.getValue());
            }
            else if(entry.getKey().equals("place")){
            	cs.setPlace(entry.getValue());
                //System.out.println("Place: " + entry.getValue());
            }
        });
        exchange.getIn().setBody(cs);
    };

        public static final Processor car2Json = (Exchange exchange) -> {
        ArrayList<Car> cheapCar = (ArrayList<Car>)exchange.getIn().getBody();

        float min_value = -1f;
        int index = -1;
        for (int i = 0; i < cheapCar.size(); i++) {
            float price = cheapCar.get(i).getPrix();
            if (price < min_value) {
                min_value = price;
                index = i;
            }
        }
        Car car = cheapCar.get(index);

        String CheapCarJson = 
        "{\n" +
        "   \"car\":[\n" +
        "     {\n" +
        "       \"idCar\":\""+car.getIdCar()+"\",\n" +
        "       \"marque\": "+car.getMarque()+",\n" +
        "       \"modele\": "+car.getModele()+"\n" +
        "       \"numSerie\":\""+car.getNumSerie()+"\",\n" +
        "       \"place\": "+car.getPlace()+",\n" +
        "       \"prix\": "+car.getPrix()+"\n" +
        "     }\n" +
        "\n" +
        "   ]\n" +
        "   \n" +
        "}";
        exchange.getIn().setBody(CheapCarJson);
        
    };

   /* public static Processor RequestRPC = (Exchange exchange) -> {
        CarRequest cs = (CarRequest) exchange.getIn().getBody();
        CarRental cf = CarRentalHelper.getWS();
        ArrayList<Car> response = new ArrayList<Car>();
        response = (ArrayList<Car>) cf.getCarsByPreferences(cs.getPlace(), cs.getPlace(), cs.getDateEnd());
        
        float min_value = -1f;
        int index = -1;
        for (int i = 0; i < response.size(); i++) {
            float price = response.get(i).getPrix();
            if (price < min_value) {
                min_value = price;
                index = i;
            }
        }
        Car car = response.get(index);
        //on récupere la durée
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
        long diff = 0;
        try {
        Date dateStart = formatter.parse(cs.getDateStart());
        Date dateEnd = formatter.parse(cs.getDateStart());
        diff = dateEnd.getTime() - dateStart.getTime();
        
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int duration = toIntExact(diff);
        System.out.println("Duration: " + duration);
        float total_price = duration*min_value;
        
        CarForm cheapCar = new CarForm();
        cheapCar.setIdCar(car.getIdCar());
        cheapCar.setDuration(hs.getDuration());
        cheapCar.setTotalPrice(total_price);
        System.out.println("rpc"+CheapHotel.toString());
        exchange.getIn().setBody(CheapHotel);

    };*/

}