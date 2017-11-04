package esb.flows.implem.utils.Helpers;


import esb.flows.implem.data.Car.CarRequest;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import stubs.car_rpc.*;

public class CarRentalHelper {

    private XPath xpath = XPathFactory.newInstance().newXPath();

    public String buildGetCarsRequest(CarRequest req) {
        System.out.println("Je build la requete");
        System.out.println("Avec place = " + req.getPlace());
        System.out.println("Avec DateStart = " + req.getDateStart());
        System.out.println("Avec DateEnd = " + req.getDateEnd());

        StringBuilder builder = new StringBuilder();
        builder.append("<cook:getCarsByPreferences xmlns:cook=\"http://informatique.polytech.unice.fr/soa1/cookbook/\">\n");
        builder.append("    <place>"     + req.getPlace()  + "</place>\n");
        builder.append("    <dateStart>" + req.getDateStart() + "</dateStart>\n");
        builder.append("    <dateEnd>"   + req.getDateEnd()   + "</dateEnd>\n");
        builder.append("</cook:getCarsByPreferences>");
        return builder.toString();
    }
}