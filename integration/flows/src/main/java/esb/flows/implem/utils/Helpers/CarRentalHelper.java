package esb.flows.implem.utils.Helpers;


import esb.flows.implem.data.Car.CarRequest;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

public class CarRentalHelper {

    private final XPath xpath = XPathFactory.newInstance().newXPath();

    public String buildGetCarsRequest(CarRequest req) {
        System.out.println("weweeee");
        StringBuilder builder = new StringBuilder();
        builder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cook=\"http://informatique.polytech.unice.fr/soa1/cookbook/\">");
        builder.append("<soapenv:Header/>");
        builder.append(" <soapenv:Body>");
        builder.append("<cook:getCarsByPreferences xmlns:cook=\"http://cookbook.soa1.polytech.unice.fr/\">\n");
        builder.append("  <getCarsByPreferences>\n");
        builder.append("    <place>Paris</place>\n");
        builder.append("    <dateStart>2018-08-23</dateStart>\n");
        builder.append("    <dateEnd>2018-08-24</dateEnd>\n");
        builder.append("  </getCarsByPreferences>\n");
        builder.append("</cook:getCarsByPreferences>");       
        builder.append(" </soapenv:Body>");
        builder.append("</soapenv:Envelope>");
        return builder.toString();
    }
}