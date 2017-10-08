package carRental.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Car {
        private int idCar;
        private int numSerie;
	private String place;
        private float prix;//prix de la voiture par jour
	private String marque;
        private String modele;

        public int getIdCar() {
            return idCar;
        }

        public void setIdCar(int idCar) {
            this.idCar = idCar;
        }
        
        @XmlElement(required = true)
	public int getNumSerie() { return numSerie; }
	public void setNumSerie(int numSerie) { this.numSerie = numSerie; }

	@XmlElement(required = true)
	public String getPlace() { return place; }
	public void setPlace(String place) { this.place = place; }

	@XmlElement(required = true)
	public float getPrix() { return prix; }
	public void setPrix(float prix) { this.prix = prix; }

	@XmlElement(required = true)
	public String getMarque() { return marque; }
	public void setMarque(String marque) { this.marque = marque; }

        @XmlElement(required = true)
	public String getModele() { return modele; }
	public void setModele(String modele) { this.modele = modele; }
        
        public Car clone(){
            Car c = new Car();
            c.setIdCar(idCar);
            c.setMarque(marque);
            c.setModele(modele);
            c.setNumSerie(numSerie);
            c.setPlace(place);
            c.setPrix(prix);
            
            return c;
        }
       
      
}
