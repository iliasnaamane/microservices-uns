package esb.flows.implem.data;

import java.io.Serializable;

public class CarForm implements Serializable {

    private int idCar;
    private int numSerie;
    private float prix;

    private String marque;
    private String modele;
    private String place;


    public int getIdCar() { return idCar; }
    public void setIdCar(int idCar) { this.idCar = idCar; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public int getNumSerie() { return numSerie; }
    public void setNumSerie(int numSerie) { this.numSerie = numSerie; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarForm carForm = (CarForm) o;

        if (idCar != carForm.idCar) return false;
        if (numSerie != carForm.numSerie) return false;
        if (prix != carForm.prix) return false;
        if (modele != null ? !modele.equals(carForm.modele) : carForm.modele != null) return false;
        if (marque != null ? !marque.equals(carForm.marque) : carForm.marque != null) return false;
        return place != null ? place.equals(carForm.place) : carForm.place == null;
    }
}