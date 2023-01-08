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

    public void ajouterPoint(int nbPoint){
        this.nbPoint += nbPoint;
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

    public List<CarteVehicule> supprimerCarteVehicule(Color color, int nb, Color colorJoker) {
        int i;
        List<CarteVehicule> cartesDefausse = new ArrayList<CarteVehicule>();
        for (i = 0; i < nb; i++) {
            for (int j = 0; j < this.cartesVehicule.size(); j++) {
                if (this.cartesVehicule.get(0).getType().getColor() == color) 
                    cartesDefausse.add(this.cartesVehicule.remove(j));
                
            }
        }

        for (int j = 0; j < this.cartesVehicule.size(); j++) {
            if (this.cartesVehicule.get(0).getType().getColor() == colorJoker)
                    cartesDefausse.add(this.cartesVehicule.remove(j));
            
        }

        return cartesDefausse;
    }

    public void supprimerWagon(int longueur) {
        this.nbWagon -= longueur;
    }

}
