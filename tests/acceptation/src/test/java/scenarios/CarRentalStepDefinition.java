package scenarios;

import cucumber.api.java.en.*;
import car_rpc.*;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import java.lang.*;

import static org.junit.Assert.*;

public class CarRentalStepDefinition{

    private String host = "localhost";
    private int port = 8080;
	private String place;
	private int duration;
	private List<Car> response = null;

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

    @Given("^a duration equal (\\d+)$")
    public void declare_duration(int duration) { 
        this.duration = duration; 
        System.out.println("La duree est "+duration);
    }

    @When("^the service is called$")
    public void call_service() {
        CarRental cr = getWS();
        this.response = cr.getCars(this.place,this.duration);
        System.out.println("Taille de la liste = "+this.response.size());
         System.out.println("Taille de la liste = "+this.response);
    }

    @Then("^the cout total is (\\d+\\.\\d+)$")
    public void validate_coutTotal(float value)
    {
        if(this.response.size() > 0)
        {
            assertEquals(value*this.duration, this.response.get(0).getCoutTotal(),0.01);
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