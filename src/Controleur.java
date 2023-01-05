package src;

import java.util.List;

import src.ihm.FramePrincipale;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Joueur;
import src.metier.Metier;
import src.metier.Pioche;

public class Controleur {

    private Metier metier;
    private FramePrincipale ihm;

    private Joueur joueurCourant;


    public Controleur() {
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);

        this.joueurCourant = new Joueur("Pedrolito", 54786);
        //this.joueurCourant = this.metier.getLstJoueurs().get(0);
    }

    public Metier getMetier(){
        return this.metier;
    }

    public Pioche getPioche() {
        return this.metier.getPioche();
    }

    public List<Joueur> getLstJoueurs() {
        return this.metier.getLstJoueurs();
    }

    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }

    public void majPioche() {
        this.ihm.majPioche();
    }

    public void piocherVehicule(int i) {
        this.joueurCourant.ajouterCarteVehicule(this.metier.getPioche().piocherVehicule(i));
    }

    public void piocherObjectif() {
        this.joueurCourant.ajouterCarteObjectif(this.metier.getPioche().piocherObjectif());
    }

    public static void main (String[] args) {
        Controleur ctrl = new Controleur();
    }
}