package stubs.hotel_rpc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.10
<<<<<<< HEAD
 * 2017-11-04T19:36:30.245+01:00
=======
 * 2017-11-04T21:28:53.596+01:00
>>>>>>> fac171f12408ee638e4ce0192033ab1d01bcc76e
 * Generated source version: 3.1.10
 * 
 */
@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", name = "HotelFinderService")
@XmlSeeAlso({ObjectFactory.class})
public interface HotelFinderService {

    @WebMethod
    @RequestWrapper(localName = "recherche", targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", className = "stubs.hotel_rpc.Recherche")
    @ResponseWrapper(localName = "rechercheResponse", targetNamespace = "http://informatique.polytech.unice.fr/soa1/cookbook/", className = "stubs.hotel_rpc.RechercheResponse")
    @WebResult(name = "recherche_hotel", targetNamespace = "")
    public java.util.List<stubs.hotel_rpc.Hotel> recherche(
        @WebParam(name = "lieu", targetNamespace = "")
        java.lang.String lieu,
        @WebParam(name = "dure", targetNamespace = "")
        int dure,
        @WebParam(name = "arg2", targetNamespace = "")
        boolean arg2
    );
}
