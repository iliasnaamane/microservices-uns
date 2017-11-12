
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esb.flows.implem.utils.Processors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import esb.flows.implem.data.Car.CarExternalForm;
import esb.flows.implem.data.Car.CarForm;
import esb.flows.implem.data.Car.CarRequest;
import esb.flows.implem.utils.Helpers.CarRentalHelper;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.xml.sax.InputSource;
import stubs.car_rpc.Car;
import stubs.car_rpc.CarRental;

/**
 *
 * @author iliasnaamane
 */

/**
 * This class contains all car rental flow processors
 */
public class CarProcessors {
     
    
    
    
    
    /**
    * build json request body for Car rental part
    */
    
    public static Processor CarForm2json = (Exchange exchange) -> {
      /*  String routeBody = (String)exchange.getIn().getBody();
        if(routeBody.equals("service car not found") ){
            exchange.getIn().setBody(routeBody);
        }
        else{*/
            String bodyJson;
            CarForm car = (CarForm) exchange.getIn().getBody();
            if(car.getModele().equals("fake-car-modele")){
                  bodyJson = "	\"carRental\":    \n"
                    + "  	{      \n"
                    + "  		\"id_rentcar\":\"\",       \n"
                    + "  		\"price\":\"\",       \n"
                    + "  		\"modele\":\"\",       \n"
                    + "  		\"marque\":\"\"    \n"
                    + "  		\n"
                    + "  	} ";
                  exchange.getIn().setHeader("car-not-found", "yes");
            }
            else {
                 bodyJson = "	\"carRental\":    \n"
                        + "  	{      \n"
                        + "  		\"id_rentcar\":\"" + car.getIdCar() + "-extern\",       \n"
                        + "  		\"price\":\"" + car.getPrix() + "\",       \n"
                        + "  		\"modele\":\"" + car.getModele() + "\",       \n"
                        + "  		\"marque\":\"" + car.getMarque() + "\"    \n"
                        + "  		\n"
                        + "  	} ";
            }  
            
            exchange.getIn().setBody(bodyJson);
      //  }
        
        
        
    };
    
    /**
     * Json to cheap car serialized object
     */
    public static Processor json2CarExternalForm = (Exchange exchange) -> {
        String response = (String)exchange.getIn().getBody();
        Gson gson = new Gson();
        ArrayList<CarExternalForm> Cars = gson.fromJson(response, new TypeToken<ArrayList<CarExternalForm>>(){}
                                            .getType());
        CarForm cheapCar = new CarForm();
        for (CarExternalForm c : Cars) {
            cheapCar.setIdCar(c.getId());
             cheapCar.setMarque(c.getMake());
             cheapCar.setModele(c.getModel());
             cheapCar.setPrix(c.getPriceperday());
             
             
            break;
        }
        
        exchange.getIn().setBody(cheapCar);
        
    };
    
    
    /**
     * Request REST to external car service
     */
    public static Processor RequestREST = (Exchange exchange) -> {
      
        CarRequest cr = (CarRequest) exchange.getIn().getBody();
          System.out.println("request rest car"+cr.getPlace());
        exchange.getIn().setHeader(Exchange.HTTP_QUERY,"location="+cr.getPlace());
        exchange.getIn().setBody(null);
    };
    
    
    
    /**
     * Request RPC to internal car service
     */
    
    public static final Processor RequestRPC = (Exchange exchange) -> {
        System.out.println("Request RPC to car internal service");
        
       /* StringBuilder builder = new StringBuilder();
        builder.append("<soapenv:Envelope  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cook=\"http://informatique.polytech.unice.fr/soa1/cookbook/\">\n");
        builder.append("<soapenv:Header/>\n");
        builder.append(" <soapenv:Body>\n");
        builder.append("<cook:getCarsByPreferences>\n");
        builder.append("    <place>Paris</place>\n");
        builder.append("    <dateStart>2018-08-23</dateStart>\n");
        builder.append("    <dateEnd>2018-08-24</dateEnd>\n");
        builder.append("</cook:getCarsByPreferences>");       
        builder.append(" </soapenv:Body>");
        builder.append("</soapenv:Envelope>");
        System.out.println(builder);
        
        exchange.getIn().setBody(builder.toString());*/
      
        CarRequest request = (CarRequest)exchange.getIn().getBody();
         System.out.println("date: " +request.getDateStart());
         System.out.println("date end: " +request.getDateEnd());
        CarRental cr = CarRentalHelper.getWS();
        System.out.println(cr);
        ArrayList<Car> response = new ArrayList<>();
        response = (ArrayList<Car>)cr.getCarsByPreferences(request.getPlace(), request.getDateStart(), request.getDateEnd());
        System.out.println("wahaalalala");
        System.out.println("response"+response);
        CarForm cf = new CarForm();
        for (Car c : response) {
            System.out.println("weeeeweeee");
            System.out.println("model cheap car in rpc intenrnal service:"+c.getModele());
            
            cf.setIdCar(c.getIdCar());
            cf.setMarque(c.getMarque());
            cf.setModele(c.getModele());
            cf.setPlace(c.getPlace());
            cf.setPrix(c.getPrix());
            
             
            break;
        }
        exchange.getIn().setBody(cf);  
        
        
    };
    
    /**
     * consolidate RPC Response using Xpath 
     */
    public static final Processor consolidateResponse = (Exchange exchange) -> {
       
        String response = (String)exchange.getIn().getBody();
        System.out.println("response car rpc:"+response);
        XPath xpath = XPathFactory.newInstance().newXPath();
        CarForm cf = new CarForm();

        InputSource src =  new InputSource(new StringReader(response));   
        cf.setIdCar(Integer.parseInt(xpath.evaluate("//idCar/text()", src)));  
        src =  new InputSource(new StringReader(response));
        cf.setMarque(xpath.evaluate("//marque/text()", src));
        src =  new InputSource(new StringReader(response));
        cf.setModele(xpath.evaluate("//modele/text()",src));
        src =  new InputSource(new StringReader(response));
        cf.setNumSerie(Integer.parseInt(xpath.evaluate("//numSerie/text()", src)));
        src =  new InputSource(new StringReader(response));
        cf.setPlace(xpath.evaluate("//place/text()", src));
        src =  new InputSource(new StringReader(response));
        cf.setPrix(Float.parseFloat(xpath.evaluate("//prix/text()", src)));
        System.out.println("consolidate response");
        exchange.getIn().setBody(cf);
        
        
    };
    
    
    /**
     * CSV to car request object
     */
    
    public static   Processor csv2Request = (Exchange exchange)-> {
    Map<String, String> data = (Map<String, String>) exchange.getIn().getBody();
    CarRequest req = new CarRequest();
    data.entrySet().forEach((Map.Entry<String, String> entry) -> {
        if(entry.getKey().equals("place"))
            req.setPlace(entry.getValue());
        else if(entry.getKey().equals("dateStart"))
            req.setDateStart(entry.getValue());
        else
            req.setDateEnd(entry.getValue());
                
             
    });
    System.out.println("csv 2 request");
    System.out.println(req.getDateStart());
    exchange.getIn().setBody(req);
    };
    
    
    public static   Processor fakeRentCarBuilder = (Exchange exchange)-> {
        System.out.println("fake rent car builder");
        CarForm cf = new CarForm();
        cf.setIdCar(00);
        cf.setMarque("fake-car-marque");
        cf.setModele("fake-car-modele");
        cf.setPlace("fake-car-place");
        cf.setPrix(Integer.MAX_VALUE);
        exchange.getIn().setBody(cf);
            
    };
    
    
    
}
