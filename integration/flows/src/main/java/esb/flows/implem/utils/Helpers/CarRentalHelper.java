package esb.flows.implem.utils.Helpers;

import esb.flows.implem.utils.Endpoints;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.ws.BindingProvider;
import stubs.car_rpc.CarRental;
import stubs.car_rpc.ExternalCarRentalService;
import stubs.hotel_rpc.ExternalHotelFinderService;
import stubs.hotel_rpc.HotelFinderService;

public class CarRentalHelper {

   public static  CarRental getWS() {
        URL wsdl=null;
        try {
            wsdl = new URL("http://car-rpc:8080/car-rpc/ExternalCarRentalService?wsdl");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        ExternalCarRentalService factory = new ExternalCarRentalService(wsdl);
        CarRental ws = factory.getExternalCRentalPort();
        String address = "http://car-rpc:8080/car-rpc/ExternalCarRentalService";
        ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        return ws;
    }
}