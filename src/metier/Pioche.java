package src.metier;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

import src.Controleur;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Type;

public class Pioche {
    
    private List<CarteVehicule> lstCartesVehicule;
    private List<CarteObjectif> lstCartesObjectif;
    private Controleur ctrl;
    
    public Pioche(Controleur ctrl, List<CarteVehicule> lstCartesVehicule, List<CarteObjectif> lstCartesObjectif) {

        this.ctrl = ctrl;

        this.lstCartesVehicule = lstCartesVehicule;
        this.lstCartesObjectif = lstCartesObjectif;
    }

    public List<CarteVehicule> getLstCartesVehicule() {
        return this.lstCartesVehicule;
    }

    public ArrayList<CarteObjectif> getLstCartesObjectif() {
        ArrayList<CarteObjectif> lstObj = new ArrayList<CarteObjectif>();
        lstObj.add(new CarteObjectif(new Noeud("Plic", 0, 0, 0, 0), new Noeud("Ploc", 1, 1, 1, 1), 10));
        return lstObj;
        //return this.lstCartesObjectif;
    }

    public CarteVehicule[] majPiocheVehiculeVisible() {
        CarteVehicule[] piocheVehiculeVisible = new CarteVehicule[5];
        for (int i = 0; i < 5; i++) {
            if (i < this.lstCartesVehicule.size())
                piocheVehiculeVisible[i] = this.lstCartesVehicule.get(i);
            else
                piocheVehiculeVisible[i] = null;
        }
        return piocheVehiculeVisible;
    }

    public CarteVehicule retirerCarteVehicule(int i) {
        return  this.lstCartesVehicule.remove(i);
    }

    public CarteObjectif retirerCarteObjectif(int i) {
        return this.lstCartesObjectif.remove(i);
    }

    public void ajouterCarteVehicule(CarteVehicule carte) {
        this.lstCartesVehicule.add(carte);
    }

    public void ajouterCarteObjectif(CarteObjectif carte) {
        this.lstCartesObjectif.add(carte);
    }

    public void melangerCarteVehicule() {
        Collections.shuffle(this.lstCartesVehicule);
    }

    public void melangerCarteObjectif() {
        Collections.shuffle(this.lstCartesObjectif);
    }

    public CarteVehicule piocherVehicule(int i) {
        if (i >= 0 && i < 6 && i < this.lstCartesVehicule.size()) {
            CarteVehicule carteVehi = this.retirerCarteVehicule(i);
            this.ctrl.majPioche();
            return carteVehi;
        }
        this.ctrl.majPioche();
        return null;
    }

    public CarteObjectif piocherObjectif() {
        return this.retirerCarteObjectif(0);
    }
}
