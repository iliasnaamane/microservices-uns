package stubs.car_rpc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-11-11T15:38:11.031+01:00
 * Generated source version: 3.1.10
 * 
 */
@WebServiceClient(name = "ExternalCarRentalService", 
                  wsdlLocation = "file:/Users/iliasnaamane/microservices-uns/integration/flows/src/main/resources/car.wsdl",
                  targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/") 
public class ExternalCarRentalService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "ExternalCarRentalService");
    public final static QName ExternalCRentalPort = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "ExternalCRentalPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/Users/iliasnaamane/microservices-uns/integration/flows/src/main/resources/car.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ExternalCarRentalService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/Users/iliasnaamane/microservices-uns/integration/flows/src/main/resources/car.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ExternalCarRentalService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ExternalCarRentalService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ExternalCarRentalService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ExternalCarRentalService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ExternalCarRentalService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ExternalCarRentalService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns CarRental
     */
    @WebEndpoint(name = "ExternalCRentalPort")
    public CarRental getExternalCRentalPort() {
        return super.getPort(ExternalCRentalPort, CarRental.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CarRental
     */
    @WebEndpoint(name = "ExternalCRentalPort")
    public CarRental getExternalCRentalPort(WebServiceFeature... features) {
        return super.getPort(ExternalCRentalPort, CarRental.class, features);
    }

}
