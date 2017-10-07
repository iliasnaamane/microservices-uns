package Document.vols;

import java.util.Date;

import org.json.JSONObject;

public class vol {
	
	private  int id;
	private String destination;
	private String origine;
	private Date Arivee;
	private Date depart;
	private double rating;
	private double distance;
	private String cmpny;
	
	JSONObject toJson() {
        return new JSONObject()
                .put("id", id)
                .put("origine", origine)
                .put("destination", destination)
                .put("Arivee", Arivee)
                .put("depart", depart)          
                .put("distance", distance)
                .put("cmpny", cmpny)
                .put("rating", rating);
    }
	
	public vol()
	{}
	
	public double get_distance() {return this.distance;}
	public String get_cmpny() {return this.cmpny;}
	public double get_rating() {return this.rating;}
	
	public boolean set_distance(double distance) { this.distance=distance;return true;}
	public boolean set_cmpny(String cmpny) { this.cmpny=cmpny;return true;}
	public boolean set_rating(double rating) { this.rating=rating; return true;}
	
	public boolean set_destination(String destination) {
		
		this.destination=destination;
		return true;
	}
	public boolean set_where (String origine){
		this.origine=origine;
		return true;
	}
	public boolean set_Dates(Date arrivee , Date depart) {
		
		this.Arivee=arrivee;
		this.depart=depart;
		return true;
		}
	
	public String get_destination(){
		
		return this.destination;
	}
	
	public String get_where(){
		
		return this.origine;
	}
	public Date get_arivee() {
		
		return this.Arivee;
		}
	
	public Date get_depart() {
		
		return this.depart;
		}
	
	public vol get_vol() {		
		
		return this;
	}
	
	
}
