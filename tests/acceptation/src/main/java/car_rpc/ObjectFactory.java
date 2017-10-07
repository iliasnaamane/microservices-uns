
package car_rpc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the car_rpc package. 
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

    private final static QName _GetCars_QNAME = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "getCars");
    private final static QName _GetCarsResponse_QNAME = new QName("http://informatique.polytech.unice.fr/soa1/cookbook/", "getCarsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: car_rpc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCars }
     * 
     */
    public GetCars createGetCars() {
        return new GetCars();
    }

    /**
     * Create an instance of {@link GetCarsResponse }
     * 
     */
    public GetCarsResponse createGetCarsResponse() {
        return new GetCarsResponse();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCars }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", name = "getCars")
    public JAXBElement<GetCars> createGetCars(GetCars value) {
        return new JAXBElement<GetCars>(_GetCars_QNAME, GetCars.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", name = "getCarsResponse")
    public JAXBElement<GetCarsResponse> createGetCarsResponse(GetCarsResponse value) {
        return new JAXBElement<GetCarsResponse>(_GetCarsResponse_QNAME, GetCarsResponse.class, null, value);
    }

}
