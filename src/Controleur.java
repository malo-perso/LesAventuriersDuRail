package src;

import java.util.List;

import src.metier.Joueur;
import src.metier.Metier;
import src.metier.Pioche;

public class Controleur {

    private Metier metier;
    
    public Controleur() {
        this.metier = new Metier(this);
    }

    public Pioche getPioche() {
        return this.metier.getPioche();
    }

    public List<Joueur> getLstJoueurs() {
        return this.metier.getLstJoueurs();
    }

}