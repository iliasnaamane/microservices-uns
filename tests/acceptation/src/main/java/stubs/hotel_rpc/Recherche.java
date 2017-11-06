
package stubs.hotel_rpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour recherche complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété lieu.
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
     * Définit la valeur de la propriété lieu.
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
     * Obtient la valeur de la propriété dure.
     * 
     */
    public int getDure() {
        return dure;
    }

    /**
     * Définit la valeur de la propriété dure.
     * 
     */
    public void setDure(int value) {
        this.dure = value;
    }

    /**
     * Obtient la valeur de la propriété arg2.
     * 
     */
    public boolean isArg2() {
        return arg2;
    }

    /**
     * Définit la valeur de la propriété arg2.
     * 
     */
    public void setArg2(boolean value) {
        this.arg2 = value;
    }

}
