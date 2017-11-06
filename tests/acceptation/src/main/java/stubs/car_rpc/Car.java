
package stubs.car_rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for car complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="car"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idCar" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="marque" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="modele" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numSerie" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="place" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="prix" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
    "idCar",
    "marque",
    "modele",
    "numSerie",
    "place",
    "prix"
})
public class Car {

    protected int idCar;
    @XmlElement(required = true)
    protected String marque;
    @XmlElement(required = true)
    protected String modele;
    protected int numSerie;
    @XmlElement(required = true)
    protected String place;
    protected float prix;

    /**
     * Gets the value of the idCar property.
     * 
     */
    public int getIdCar() {
        return idCar;
    }

    /**
     * Sets the value of the idCar property.
     * 
     */
    public void setIdCar(int value) {
        this.idCar = value;
    }

    /**
     * Gets the value of the marque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Sets the value of the marque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarque(String value) {
        this.marque = value;
    }

    /**
     * Gets the value of the modele property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModele() {
        return modele;
    }

    /**
     * Sets the value of the modele property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModele(String value) {
        this.modele = value;
    }

    /**
     * Gets the value of the numSerie property.
     * 
     */
    public int getNumSerie() {
        return numSerie;
    }

    /**
     * Sets the value of the numSerie property.
     * 
     */
    public void setNumSerie(int value) {
        this.numSerie = value;
    }

    /**
     * Gets the value of the place property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlace() {
        return place;
    }

    /**
     * Sets the value of the place property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlace(String value) {
        this.place = value;
    }

    /**
     * Gets the value of the prix property.
     * 
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Sets the value of the prix property.
     * 
     */
    public void setPrix(float value) {
        this.prix = value;
    }

}
