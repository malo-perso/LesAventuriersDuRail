package src.metier;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Joueur {
    
    private String nom;
    private int nbWagon;
    private int nbPoint;
    private ArrayList<CarteVehicule> cartesVehicule;
    private ArrayList<CarteObjectif> cartesObjectif;
    private int RGB;

    public Joueur(String nom, int RGB, int nbWagon) {
        this.nom = nom;
        this.nbWagon = nbWagon;
        this.nbPoint = 0;
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

    public int getPoint(){
        return this.nbPoint;
    }

    public ArrayList<CarteVehicule> getCartesVehicule() {
        return this.cartesVehicule;
    }

    public ArrayList<CarteObjectif> getCartesObjectif() {
        return this.cartesObjectif;
    }

    public void ajouterCarteVehicule(CarteVehicule lstcarte) {
        this.cartesVehicule.add(lstcarte);
    }

    public void ajouterCarteObjectif(CarteObjectif carte) {
        this.cartesObjectif.add(carte);
    }

    public void setNomJoueur(String nom){
        this.nom =nom;
    }

    public Color getCouleur() {
        return new Color(this.RGB);
    }

    public int getNbCarteWagon(Color type)
    {
        int nb = 0;
        for (CarteVehicule carte : this.cartesVehicule) {
            if (carte.getType().getColor() == type) {
                nb++;
            }
        }
        return nb;
    }

    public void supprimerCarteVehicule(Color color, int nb, Color colorJoker) {
        int i;
        for (i = 0; i < nb; i++) {
            for (CarteVehicule carte : this.cartesVehicule) {
                if (carte.getType().getColor() == color) {
                    this.cartesVehicule.remove(carte);
                    break;
                }
            }
        }

        for (int j =i ; j < nb; j++) {
            for (CarteVehicule carte : this.cartesVehicule) {
                if (carte.getType().getColor() == colorJoker) {
                    this.cartesVehicule.remove(carte);
                    break;
                }
            }
        }
    }

}
