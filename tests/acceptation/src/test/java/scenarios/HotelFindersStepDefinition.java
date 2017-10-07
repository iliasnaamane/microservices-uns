package scenarios;

import cucumber.api.java.en.*;
import hotel_rpc.*;

import javax.xml.ws.BindingProvider;
import java.net.URL;

import java.lang.*;

import static org.junit.Assert.*;

public class HotelFindersStepDefinition {

    private String host = "localhost";
    private int port = 8080;
	private String place;
	private int duration;
	private Hotel response = null;

    @Given("^The HotelFinders service deployed on (.*):(\\d+)$")
    public void select_host_and_port(String host, int port) { 
        this.host = host; this.port = port;
        System.out.println("Service deployer sur "+host+":"+port);
         }

	@Given("^a place is (.*)$")
    public void place_equal(String place) { 
        this.place = place; 
        System.out.println("La place est "+place);
    }

    @Given("^a duration is (\\d+)$")
    public void declare_duration(int duration) { 
        this.duration = duration; 
        System.out.println("La duree est "+duration);
    }

    @When("^the service called$")
    public void call_service() {
        HotelFinder hf = getWS();
        this.response = hf.recherche(this.place,this.duration);
         System.out.println("Element recuperer = "+this.response.getNom());
    }

    @Then("^the name of hotel is (.*)$")
    public void validate_name(String value)
    {
        if(this.response != null)
        {
            assertEquals(value, this.response.getNom());
        }
    }

   private HotelFinder getWS() 
   {
    URL wsdl = HotelFindersStepDefinition.class.getResource("HOTEL.wsdl");
    ExternalHotelFinderService factory = new ExternalHotelFinderService(wsdl);
    HotelFinder ws = factory.getExternalHotelFinderPort();
    String address = "http://"+this.host+":"+this.port+"/hotel-rpc/ExternalHotelFinderService";
    ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    return ws;
    } 
}