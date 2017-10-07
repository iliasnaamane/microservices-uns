
package hotel_rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recherche complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recherche"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lieu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dure" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="arg2" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recherche", propOrder = {
    "lieu",
    "dure",
    "arg2"
})
public class Recherche {

    protected String lieu;
    protected int dure;
    protected boolean arg2;

    /**
     * Gets the value of the lieu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * Sets the value of the lieu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLieu(String value) {
        this.lieu = value;
    }

    /**
     * Gets the value of the dure property.
     * 
     */
    public int getDure() {
        return dure;
    }

    /**
     * Sets the value of the dure property.
     * 
     */
    public void setDure(int value) {
        this.dure = value;
    }

    /**
     * Gets the value of the arg2 property.
     * 
     */
    public boolean isArg2() {
        return arg2;
    }

    /**
     * Sets the value of the arg2 property.
     * 
     */
    public void setArg2(boolean value) {
        this.arg2 = value;
    }

}
