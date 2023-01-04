package src;

import java.util.List;

import src.ihm.FramePrincipale;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Joueur;
<<<<<<< Updated upstream
import src.metier.Metier;
=======
>>>>>>> Stashed changes
import src.metier.Pioche;

public class Controleur {

    private Metier metier;
    private FramePrincipale ihm;


    public Controleur() {
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
    }

    public Pioche getPioche() {
        return this.metier.getPioche();
    }

    public List<Joueur> getLstJoueurs() {
        return this.metier.getLstJoueurs();
    }

    public static void main (String[] args) {
        Controleur ctrl = new Controleur();
    }
}