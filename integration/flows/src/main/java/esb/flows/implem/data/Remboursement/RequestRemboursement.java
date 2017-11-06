package esb.flows.implem.data.Remboursement;

import java.io.Serializable;

public class RequestRemboursement implements Serializable {

    private int idEmploye;
    private int idBusinessTravel;
    private String nomImage;
    private boolean fin;
    private int prix;


    public int getIdEmploye() { return idEmploye; }
    public void setIdEmploye(int idEmploye) { this.idEmploye = idEmploye; }

    public int getIdBusinessTravel() { return idBusinessTravel; }
    public void setIdBusinessTravel(int idBusinessTravel) { this.idBusinessTravel = idBusinessTravel; }

    public String getNomImage() { return nomImage; }
    public void setNomImage(String nomImage) { this.nomImage = nomImage; }

    public boolean getFin() { return fin; }
    public void setFin(String fin) { this.fin = fin; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestRemboursement requestRemboursement = (RequestRemboursement) o;

        if (idEmploye != RequestRemboursement.idEmploye) return false;
        if (idBusinessTravel != RequestRemboursement.idBusinessTravel) return false;
        if (prix != RequestRemboursement.prix) return false;
        if (fin != RequestRemboursement.fin) return false;
        return nomImage != null ? nomImage.equals(RequestRemboursement.nomImage) : RequestRemboursement.nomImage == null;
    }
}