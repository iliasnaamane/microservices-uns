
package hotel_rpc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rechercheResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rechercheResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="recherche_hotel" type="{http://informatique.polytech.unice.fr/soa1/cookbook/}hotel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rechercheResponse", propOrder = {
    "rechercheHotel"
})
public class RechercheResponse {

    @XmlElement(name = "recherche_hotel")
    protected List<Hotel> rechercheHotel;

    /**
     * Gets the value of the rechercheHotel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rechercheHotel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRechercheHotel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hotel }
     * 
     * 
     */
    public List<Hotel> getRechercheHotel() {
        if (rechercheHotel == null) {
            rechercheHotel = new ArrayList<Hotel>();
        }
        return this.rechercheHotel;
    }

}
