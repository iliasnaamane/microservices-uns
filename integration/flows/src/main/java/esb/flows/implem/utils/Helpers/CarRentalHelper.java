package esb.flows.implem.utils.Helpers;

import esb.flows.implem.data.Request;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

public class CarRentalHelper {

    private XPath xpath = XPathFactory.newInstance().newXPath();

    public String buildGetCarsRequest(Request req) {
        StringBuilder builder = new StringBuilder();
        builder.append("<cook:getCarsByPreferences xmlns:cook=\"http://cookbook.soa1.polytech.unice.fr/\">\n");
        builder.append("  <getCarsByPreferences>\n");
        builder.append("    <place>"     + req.getPlace()  + "</place>\n");
        builder.append("    <dateStart>" + req.getDateStart() + "</dateStart>\n");
        builder.append("    <dateEnd>"   + req.getDateEnd()   + "</dateEnd>\n");
        builder.append("  </getCarsByPreferences>\n");
        builder.append("</cook:getCarsByPreferences>");
        return builder.toString();
    }

}