package src;

import java.util.ArrayList;

import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Pioche;

public class Controleur {

    private Pioche pioche;

    public Controleur() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<CarteVehicule> getLstCartesVehicule() {
        return this.pioche.getLstCartesVehicule();
    }

    public ArrayList<CarteObjectif> getLstCartesObjectif() {
        return this.pioche.getLstCartesObjectif();
    }
}