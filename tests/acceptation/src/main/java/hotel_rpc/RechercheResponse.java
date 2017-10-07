
package hotel_rpc;

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
 *         &lt;element name="recherche_hotel" type="{http://informatique.polytech.unice.fr/soa1/cookbook/}hotel" minOccurs="0"/&gt;
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
    protected Hotel rechercheHotel;

    /**
     * Gets the value of the rechercheHotel property.
     * 
     * @return
     *     possible object is
     *     {@link Hotel }
     *     
     */
    public Hotel getRechercheHotel() {
        return rechercheHotel;
    }

    /**
     * Sets the value of the rechercheHotel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hotel }
     *     
     */
    public void setRechercheHotel(Hotel value) {
        this.rechercheHotel = value;
    }

}
