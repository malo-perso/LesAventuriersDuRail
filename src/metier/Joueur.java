package src.metier;

import java.util.ArrayList;
import java.awt.Color;

public class Joueur {
    
    private String nom;
    private int nbWagon;
    private ArrayList<CarteVehicule> cartesVehicule;
    private ArrayList<CarteObjectif> cartesObjectif;
    private int RGB;

    public Joueur(String nom, int RGB) {
        this.nom = nom;
        this.nbWagon = 0;
        this.cartesVehicule = new ArrayList<CarteVehicule>();
        this.cartesObjectif = new ArrayList<CarteObjectif>();
        this.RGB = RGB;        
    }

    public String getNom() {
        return this.nom;
    }

    public int getNbWagon() {
        return this.nbWagon;
    }

    public ArrayList<CarteVehicule> getCartesVehicule() {
        return this.cartesVehicule;
    }

    public ArrayList<CarteObjectif> getCartesObjectif() {
        return this.cartesObjectif;
    }

    public void ajouterCarteVehicule(CarteVehicule carte) {
        this.cartesVehicule.add(carte);
    }

    public void ajouterCarteObjectif(CarteObjectif carte) {
        this.cartesObjectif.add(carte);
    }

    public Color getCouleur() {
        return new Color(this.RGB);
    }

}
