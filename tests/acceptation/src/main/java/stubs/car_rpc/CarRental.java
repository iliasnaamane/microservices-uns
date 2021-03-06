package stubs.car_rpc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-11-12T18:33:40.843+01:00
 * Generated source version: 3.1.10
 * 
 */
@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", name = "CarRental")
@XmlSeeAlso({ObjectFactory.class})
public interface CarRental {

    @WebMethod
    @RequestWrapper(localName = "getCarsByPreferences", targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", className = "stubs.car_rpc.GetCarsByPreferences")
    @ResponseWrapper(localName = "getCarsByPreferencesResponse", targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", className = "stubs.car_rpc.GetCarsByPreferencesResponse")
    @WebResult(name = "carSearch_result", targetNamespace = "")
    public java.util.List<stubs.car_rpc.Car> getCarsByPreferences(
        @WebParam(name = "place", targetNamespace = "")
        java.lang.String place,
        @WebParam(name = "dateStart", targetNamespace = "")
        java.lang.String dateStart,
        @WebParam(name = "dateEnd", targetNamespace = "")
        java.lang.String dateEnd
    ) throws Exception_Exception;
}
