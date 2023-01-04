package metier;

import java.util.ArrayList;
import java.awt.Color;

public class Joueur {
    
    private String nom;
    private int nbCarte;
    private ArrayList<CarteVehicule> cartesVehicule;
    private ArrayList<CarteObjectif> cartesObjectif;
    private Color couleur;

    public Joueur(String nom, Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
        this.nbCarte = 4;
        this.cartesVehicule = new ArrayList<CarteVehicule>();
        this.cartesObjectif = new ArrayList<CarteObjectif>();
    }

    public String getNom() {
        return this.nom;
    }

    public int getNbCarte() {
        return this.nbCarte;
    }

    public ArrayList<CarteVehicule> getCartesVehicule() {
        return this.cartesVehicule;
    }

    public ArrayList<CarteObjectif> getCartesObjectif() {
        return this.cartesObjectif;
    }

    public Color getCouleur() {
        return this.couleur;
    }

}
