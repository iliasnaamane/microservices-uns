package scenarios;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;

import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.ArrayList;


import static org.junit.Assert.*;
import stubs.hotel_rpc.ExternalHotelFinderService;
import stubs.hotel_rpc.Hotel;
import stubs.hotel_rpc.HotelFinderService;

public class HotelFindersStepDefinition {

    private String host = "localhost";
    private int port = 9010;
	private String place;
	private int duration;
        private boolean arg2;
	private ArrayList<Hotel> response = new ArrayList<>();

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
    
    @Given("^Ascending is true$")
    public void ascending_is_true()  {
        // Write code here that turns the phrase above into concrete actions
        this.arg2 = true;
    }
    
    
   
    @When("^the service called$")
    public void call_service() {
        HotelFinderService hf = getWS();
        this.response = (ArrayList<Hotel>) hf.recherche(this.place,this.duration,this.arg2);
        for(Hotel h : this.response)
            System.out.println(h.getNom());
    }

    @Then("^the list of hotels is$")
    public void validate_list(DataTable hotels)
    {
        if(this.response != null)
        {
            int i =1;
            for (Hotel h : this.response) {
               
                System.out.println(hotels.raw().get(i));
                assertEquals(h.getIdentifier(), hotels.raw().get(i).get(0));
                System.out.println("Identifier done");
                assertEquals(h.getLieu(),hotels.raw().get(i).get(2));
                 System.out.println("Lieu done");
                assertEquals(h.getNom(), hotels.raw().get(i).get(4));
                 System.out.println("done");
                assertEquals(Integer.toString(h.getPrix()),hotels.raw().get(i).get(6));
                 System.out.println("done");
                
                i++;
           
            }
        }
    }

   private HotelFinderService getWS() 
   {
    URL wsdl = HotelFindersStepDefinition.class.getResource("HOTEL.wsdl");
    ExternalHotelFinderService factory = new ExternalHotelFinderService(wsdl);
    HotelFinderService ws = factory.getExternalHotelFinderPort();
    String address = "http://"+this.host+":"+this.port+"/hotel-rpc/ExternalHotelFinderService";
    ((BindingProvider) ws).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    return ws;
    } 
}