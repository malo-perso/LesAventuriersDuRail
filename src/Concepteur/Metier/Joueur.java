package Metier;

import java.util.ArrayList;

public class Joueur {
    private String pseudo ;
    private int    nbPoints;
    private int    nbWagon;
    private ArrayList <CarteObjectif> lstCarteObjectifs;
    private ArrayList <CarteWagon>    lstCarteWagons;

    public Joueur(String pseudo, int nbWagon) {
        this.pseudo   = pseudo;
        this.nbPoints = 0;
        this.nbWagon  = nbWagon;
        this.lstCarteObjectifs = new ArrayList <CarteObjectif>();
        this.lstCarteWagons    = new ArrayList <CarteWagon>();
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNbWagon() {
        return nbWagon;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public ArrayList <CarteObjectif> getLstCarteObjectifs() {
        return lstCarteObjectifs;
    }

    public ArrayList <CarteWagon> getLstCarteWagons() {
        return lstCarteWagons;
    }

    public void setNbWagon(int nbWagon) {
        this.nbWagon = nbWagon;
    }

    public void earnPoints(int points) {
        this.nbPoints += points;
    }


}
