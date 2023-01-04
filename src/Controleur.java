package src;

import java.util.ArrayList;

import src.ihm.FramePrincipale;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Pioche;

public class Controleur {

    private Pioche pioche;
    private FramePrincipale ihm;

    public Controleur() {
        this.pioche = new Pioche();
        this.ihm = new FramePrincipale(this);
    }

    public ArrayList<CarteVehicule> getLstCartesVehicule() {
        return this.pioche.getLstCartesVehicule();
    }

    public ArrayList<CarteObjectif> getLstCartesObjectif() {
        return this.pioche.getLstCartesObjectif();
    }

    public static void main (String[] args) {
        Controleur ctrl = new Controleur();
    }
}