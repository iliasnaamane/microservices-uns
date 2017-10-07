package hotel_rpc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-10-07T11:05:40.525+02:00
 * Generated source version: 3.1.10
 * 
 */
@WebServiceClient(name = "ExternalHotelFinderService", 
                  wsdlLocation = "file:/home/neko/Integration/copytest/tests/acceptation/src/main/resources/HOTEL.wsdl",
                  targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/") 
public class ExternalHotelFinderService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "ExternalHotelFinderService");
    public final static QName ExternalHotelFinderPort = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "ExternalHotelFinderPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/neko/Integration/copytest/tests/acceptation/src/main/resources/HOTEL.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ExternalHotelFinderService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/neko/Integration/copytest/tests/acceptation/src/main/resources/HOTEL.wsdl");
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
     *     returns HotelFinder
     */
    @WebEndpoint(name = "ExternalHotelFinderPort")
    public HotelFinder getExternalHotelFinderPort() {
        return super.getPort(ExternalHotelFinderPort, HotelFinder.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HotelFinder
     */
    @WebEndpoint(name = "ExternalHotelFinderPort")
    public HotelFinder getExternalHotelFinderPort(WebServiceFeature... features) {
        return super.getPort(ExternalHotelFinderPort, HotelFinder.class, features);
    }

}
