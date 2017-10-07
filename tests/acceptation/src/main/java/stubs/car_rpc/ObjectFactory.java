
package stubs.car_rpc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the stubs.car_rpc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCarsByPreferences_QNAME = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "getCarsByPreferences");
    private final static QName _GetCarsByPreferencesResponse_QNAME = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "getCarsByPreferencesResponse");
    private final static QName _Exception_QNAME = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: stubs.car_rpc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCarsByPreferences }
     * 
     */
    public GetCarsByPreferences createGetCarsByPreferences() {
        return new GetCarsByPreferences();
    }

    /**
     * Create an instance of {@link GetCarsByPreferencesResponse }
     * 
     */
    public GetCarsByPreferencesResponse createGetCarsByPreferencesResponse() {
        return new GetCarsByPreferencesResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarsByPreferences }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", name = "getCarsByPreferences")
    public JAXBElement<GetCarsByPreferences> createGetCarsByPreferences(GetCarsByPreferences value) {
        return new JAXBElement<GetCarsByPreferences>(_GetCarsByPreferences_QNAME, GetCarsByPreferences.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarsByPreferencesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", name = "getCarsByPreferencesResponse")
    public JAXBElement<GetCarsByPreferencesResponse> createGetCarsByPreferencesResponse(GetCarsByPreferencesResponse value) {
        return new JAXBElement<GetCarsByPreferencesResponse>(_GetCarsByPreferencesResponse_QNAME, GetCarsByPreferencesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
