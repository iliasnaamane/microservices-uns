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
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.xml.sax.InputSource;

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
        CarForm car = (CarForm)exchange.getIn().getBody();
        String bodyJson = "	\"carRental\":    \n" +
"  	{      \n" +
"  		\"id_rentcar\":\""+car.getIdCar()+"-extern\",       \n" +
"  		\"price\":\""+car.getPrix()+"\",       \n" +
"  		\"modele\":\""+car.getModele()+"\",       \n" +
"  		\"marque\":\""+car.getMarque()+"\"    \n" +
"  		\n" +
"  	} ";
        System.out.println(bodyJson);
        exchange.getIn().setBody(bodyJson);
        
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
            System.out.println(c.getModel());
             cheapCar.setIdCar(c.getId());
             cheapCar.setMarque(c.getMake());
             cheapCar.setModele(c.getModel());
             cheapCar.setPrix(c.getPriceperday());
             
             
            break;
        }
        
        System.out.println("cheap car returned");
      
        exchange.getIn().setBody(cheapCar);
        
    };
    
    
    /**
     * Request REST to external car service
     */
    public static Processor RequestREST = (Exchange exchange) -> {
        CarRequest cr = (CarRequest) exchange.getIn().getBody();
        exchange.getIn().setHeader(Exchange.HTTP_QUERY,"location="+cr.getPlace()+"&start="+cr.getDateStart()+"&end="+cr.getDateEnd()+"&sort=asc");
        exchange.getIn().setBody(null);
    };
    
    
    
    /**
     * Request RPC to internal car service
     */
    
    public static final Processor RequestRPC = (Exchange exchange) -> {
        System.out.println("weweeee");
        
        StringBuilder builder = new StringBuilder();
        builder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cook=\"http://informatique.polytech.unice.fr/soa1/cookbook/\">\n");
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
        
        exchange.getIn().setBody(builder);
        
    };
    
    /**
     * consolidate RPC Response using Xpath 
     */
    public static final Processor consolidateResponse = (Exchange exchange) -> {
       
        String response = (String)exchange.getIn().getBody();
        System.out.println(response);
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
    System.out.println("csv2Request");
    Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();
    CarRequest req = new CarRequest();
    req.setPlace((String)data.get("place"));
    req.setDateStart((String)data.get("dateStart"));
    req.setDateEnd((String)data.get("dateEnd"));
    System.out.println(req.toString());
    exchange.getIn().setBody(req);
    System.out.println("Fin csv2Request");
    };
}
