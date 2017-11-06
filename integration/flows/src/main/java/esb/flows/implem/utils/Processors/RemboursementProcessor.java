package esb.flows.implem.utils.Processors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.xml.sax.InputSource;

public class RemboursementProcessor{

    /**
     * CSV to request object
     */
    
    public static   Processor csv2Request = (Exchange exchange)-> {
    System.out.println("csv2Request");
    Map<String, Object> data = (Map<String, Object>) exchange.getIn().getBody();

    String request = "{ \"event\": \"POST\","+
                        " \"remboursement\": \"" +""+"\","+
                         "\"idEmploye\" : \"" +(String)data.get("idEmploye") +"\","+
                         "\"idBusinessTravel\":\"" +(String)data.get("idBusinessTravel")+"\","+
                         "\"nomImage\":\"" +(String)data.get("nomImage")+"\","+
                         "\"fin\":\"" +data.get("fin")+"\","+
                        "}";

    exchange.getIn().setBody(request);
    System.out.println("Fin csv2Request");
    };
}