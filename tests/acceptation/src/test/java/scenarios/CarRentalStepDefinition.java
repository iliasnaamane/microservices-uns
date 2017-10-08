package scenarios;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.text.html.HTMLDocument;
import org.junit.Assert;


import static org.junit.Assert.*;
import stubs.car_rpc.Car;
import stubs.car_rpc.CarRental;
import stubs.car_rpc.Exception_Exception;
import stubs.car_rpc.ExternalCarRentalService;

public class CarRentalStepDefinition{

    private String host = "localhost";
    private int port = 8080;
    private String place;
    private String dateStart;
    private String dateEnd;
    private ArrayList<Car> response = new ArrayList<Car>();

    @Given("^The CarRental service deployed on (.*):(\\d+)$")
    public void select_host_and_port(String host, int port) { 
        this.host = host; this.port = port;
        System.out.println("Service deployer sur "+host+":"+port);
         }

	@Given("^a place equal (.*)$")
    public void place_equal(String place) { 
        this.place = place; 
        System.out.println("La place est "+place);
    }

    @Given("^a date start equal (.*)$$")
    public void declare_dateStart(String dateStart) { 
        this.dateStart = dateStart; 
        System.out.println("La date de debut "+dateStart);
    }

    @Given("^a date end equal (.*)$")
    public void declare_dateEnd(String dateEnd) { 
        this.dateEnd = dateEnd; 
        System.out.println("La date de fin "+dateEnd);
    }

    @When("^the service is called$")
    public void call_service() throws Exception_Exception {
        CarRental cr = getWS();
        this.response =  (ArrayList<Car>) cr.getCarsByPreferences(this.place,this.dateStart,this.dateEnd);
       
        for(int i=0;i<this.response.size();i++){
            System.out.println(response.get(0).getMarque());
            
        }
        System.out.println("Taille de la liste = "+response.size());
        
    }
    
    @Then("^the list of cars is$")
    public void the_list_of_cars_is(DataTable cars) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        
        
        if (this.response.size() > 0) {
            int i =1;
            for (Car c : this.response) {
               ;
                System.out.println(cars.raw().get(i));
                assertEquals(Integer.toString(c.getIdCar()), cars.raw().get(i).get(0));
                System.out.println("done");
                assertEquals(c.getMarque(),cars.raw().get(i).get(2));
                 System.out.println("done");
                assertEquals(c.getModele(), cars.raw().get(i).get(4));
                 System.out.println("done");
                assertEquals(Integer.toString(c.getNumSerie()),cars.raw().get(i).get(6));
                 System.out.println("done");
                assertEquals(c.getPlace(),cars.raw().get(i).get(8));
                 System.out.println("done");
                assertEquals(Float.toString(c.getPrix()), cars.raw().get(i).get(10));
                 System.out.println("done");
                i++;
           
            }
        }



    
    }


    
   

   private CarRental getWS() 
   {
    URL wsdl = CarRentalStepDefinition.class.getResource("CAR.wsdl");
    ExternalCarRentalService factory = new ExternalCarRentalService(wsdl);
    CarRental ws = factory.getExternalCRentalPort();
    String address = "http://"+this.host+":"+this.port+"/car-rpc/ExternalCarRentalService";
    ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    return ws;
    } 
}