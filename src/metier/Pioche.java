package src.metier;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Type;

public class Pioche {
    
    private List<CarteVehicule> lstCartesVehicule;
    private List<CarteObjectif> lstCartesObjectif;
    
    public Pioche(List<CarteVehicule> lstCartesVehicule, List<CarteObjectif> lstCartesObjectif) {
        this.lstCartesVehicule = lstCartesVehicule;
        this.lstCartesObjectif = lstCartesObjectif;
    }

    public ArrayList<CarteVehicule> getLstCartesVehicule() {
        ArrayList<CarteVehicule> lstVehi = new ArrayList<CarteVehicule>();
        lstVehi.add(new CarteVehicule(Type.creerType(Color.RED)));
        lstVehi.add(new CarteVehicule(Type.creerType(Color.BLUE)));
        lstVehi.add(new CarteVehicule(Type.creerType(Color.GREEN)));
        lstVehi.add(new CarteVehicule(Type.creerType(Color.PINK)));
        lstVehi.add(new CarteVehicule(Type.creerType(Color.ORANGE)));

        return lstVehi;
        // return this.lstCartesVehicule;
    }

    public ArrayList<CarteObjectif> getLstCartesObjectif() {
        ArrayList<CarteObjectif> lstObj = new ArrayList<CarteObjectif>();
        lstObj.add(new CarteObjectif(new Noeud("Plic", 0, 0, 0, 0), new Noeud("Ploc", 1, 1, 1, 1), 10));
        return lstObj;
        //return this.lstCartesObjectif;
    }

    public CarteVehicule retirerCarteVehicule() {
        CarteVehicule carteVehicule = this.lstCartesVehicule.get(0);
        this.lstCartesVehicule.remove(0);
            return carteVehicule;
    }

    public CarteObjectif retirerCarteObjectif() {
        CarteObjectif carteObjectif = this.lstCartesObjectif.get(0);
        this.lstCartesObjectif.remove(0);
            return carteObjectif;
    }

    public void melangerCarteVehicule() {
        Collections.shuffle(this.lstCartesVehicule);
    }

    public void melangerCarteObjectif() {
        Collections.shuffle(this.lstCartesObjectif);
    }


}
