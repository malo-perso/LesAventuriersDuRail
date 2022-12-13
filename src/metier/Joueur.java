package metier;
import java.util.ArrayList;

public class Joueur {
    private String pseudo ;
    private int    nbPoints;
    private int    nbVehicule;
    private ArrayList <CarteObjectif> lstCarteObjectifs;
    private ArrayList <CarteVehicule>    lstCarteVehicules;

    public Joueur(String pseudo, int nbvehicule) {
        this.pseudo   = pseudo;
        this.nbPoints = 0;
        this.nbVehicule  = nbVehicule;
        this.lstCarteObjectifs = new ArrayList <CarteObjectif>();
        this.lstCarteVehicules    = new ArrayList <CarteVehicule>();
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public int getNbVehicule() {
        return this.nbVehicule;
    }

    public int getNbPoints() {
        return this.nbPoints;
    }

    public ArrayList <CarteObjectif> getLstCarteObjectifs() {
        return this.lstCarteObjectifs;
    }

    public ArrayList <Cartevehicule> getLstCarteVehicules() {
        return this.lstCarteVehicules;
    }

    public void setNbvehicule(int nbVehicule) {
        this.nbVehicule = nbVehicule;
    }

    public void earnPoints(int points) {
        this.nbPoints += points;
    }


}
