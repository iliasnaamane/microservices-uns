package Document.vols;

import org.json.JSONObject;

public class vol {
	
	private  int id;
	private String origine;
	private String destination;
	private String date;
	private double rating;
	private double prix;
	private String cmpny;
	private int nb_escales;
	private double duree;
	
	JSONObject toJson() {
        return new JSONObject()
                .put("id", id)
                .put("origine", origine)
                .put("destination", destination)
                       
                .put("date", date)  
                .put("rating", rating) 
                .put("prix", prix) 
                .put("cmpny", cmpny)
                .put("nb_escales", nb_escales)
                .put("duree", duree);
    }
	
	public vol(int id, String origine, String destination, String depart, double rating, double prix,
			String cmpny, int nb_escales, double duree) {
		super();
		this.id = id;
		this.destination = destination;
		this.origine = origine;
		this.date = depart;
		this.rating = rating;
		this.prix = prix;
		this.cmpny = cmpny;
		this.nb_escales = nb_escales;
		this.duree = duree;
	}
	
	public vol()
	{}
	
	
	public String get_cmpny() {return this.cmpny;}
	public double get_rating() {return this.rating;}
	
	public void set_cmpny(String cmpny) { this.cmpny=cmpny;}
	public void set_rating(double rating) { this.rating=rating;}
	
	public void set_destination(String destination) {
		
		this.destination=destination;
	}
	public void set_where (String origine){
		this.origine=origine;
	}
	public boolean set_Date(String depart) {
		
		this.date=depart;
		return true;
		}
	
	public String get_destination(){
		
		return this.destination;
	}
	
	public String get_where(){
		
		return this.origine;
	}

	
	public String get_date() {
		
		return this.date;
		}
	
	public vol get_vol() {		
		
		return this;
	}
	
	public int get_id() {
		return this.id;
		}
	public double get_prix() {return this.prix;}
	public int get_nb_escales() {return this.nb_escales;}
	public double get_duree() {return duree;}
	public String get_origine() {return this.origine;}
	
}
