package hotelFinder.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


public class Hotel implements Comparable<Hotel>  {

	private String lieu;
	private int prix;
	private String nom;
	private int identifier;

	public Hotel(int id,String lieu,int prix, String nom){
		this.identifier = id;
                this.lieu=lieu;
		this.prix=prix;
		//this.dure=dure;
		this.nom=nom;
	}
	
	@XmlElement
	public String getLieu() { return lieu; }
	public void setLieu(String lieu) { this.lieu = lieu; }

	//public int getDure() { return dure; }
	//public void setDate(int dure) { this.dure = dure; }
	@XmlElement
	public int getPrix() { return prix; }
	public void setPrix(int prix) { this.prix = prix; }
	@XmlElement
	public int getIdentifier() { return identifier; }
	public void setIdentifier(int identifier) { this.identifier = identifier; }
	@XmlElement
	public String getNom() { return nom;}
	public void setNom(String nom) {this.nom = nom; }

        @Override
        public int compareTo(Hotel H) {
            if(this.prix > H.prix)
                return 1;
            else if(this.prix < H.prix)
                return -1;
            return 0;
        }
        
        @Override
        public Hotel clone(){
            Hotel hClone = new Hotel(this.identifier,this.lieu,this.prix,this.nom);  
            return hClone;  
        }


}
