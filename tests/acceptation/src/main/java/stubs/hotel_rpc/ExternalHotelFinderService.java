package stubs.hotel_rpc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-11-12T18:33:40.298+01:00
 * Generated source version: 3.1.10
 * 
 */
@WebServiceClient(name = "ExternalHotelFinderService", 
                  wsdlLocation = "file:/home/neko/Integration/final/microservices-uns/tests/acceptation/src/main/resources/HOTEL.wsdl",
                  targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/") 
public class ExternalHotelFinderService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "ExternalHotelFinderService");
    public final static QName ExternalHotelFinderPort = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "ExternalHotelFinderPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/neko/Integration/final/microservices-uns/tests/acceptation/src/main/resources/HOTEL.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ExternalHotelFinderService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/neko/Integration/final/microservices-uns/tests/acceptation/src/main/resources/HOTEL.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ExternalHotelFinderService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ExternalHotelFinderService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ExternalHotelFinderService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ExternalHotelFinderService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ExternalHotelFinderService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ExternalHotelFinderService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns HotelFinderService
     */
    @WebEndpoint(name = "ExternalHotelFinderPort")
    public HotelFinderService getExternalHotelFinderPort() {
        return super.getPort(ExternalHotelFinderPort, HotelFinderService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HotelFinderService
     */
    @WebEndpoint(name = "ExternalHotelFinderPort")
    public HotelFinderService getExternalHotelFinderPort(WebServiceFeature... features) {
        return super.getPort(ExternalHotelFinderPort, HotelFinderService.class, features);
    }

}
