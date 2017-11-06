
package stubs.car_rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour car complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété idCar.
     * 
     */
    public int getIdCar() {
        return idCar;
    }

    /**
     * Définit la valeur de la propriété idCar.
     * 
     */
    public void setIdCar(int value) {
        this.idCar = value;
    }

    /**
     * Obtient la valeur de la propriété marque.
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
     * Définit la valeur de la propriété marque.
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
     * Obtient la valeur de la propriété modele.
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
     * Définit la valeur de la propriété modele.
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
     * Obtient la valeur de la propriété numSerie.
     * 
     */
    public int getNumSerie() {
        return numSerie;
    }

    /**
     * Définit la valeur de la propriété numSerie.
     * 
     */
    public void setNumSerie(int value) {
        this.numSerie = value;
    }

    /**
     * Obtient la valeur de la propriété place.
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
     * Définit la valeur de la propriété place.
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
     * Obtient la valeur de la propriété prix.
     * 
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Définit la valeur de la propriété prix.
     * 
     */
    public void setPrix(float value) {
        this.prix = value;
    }

}
