package src.metier;

import java.awt.Color;

import java.util.ArrayList;

import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Type;

public class Pioche {
    
    private ArrayList<CarteVehicule> lstCartesVehicule;
    private ArrayList<CarteObjectif> lstCartesObjectif;
    
    public Pioche() {
        this.lstCartesVehicule = new ArrayList<CarteVehicule>();
        this.lstCartesObjectif = new ArrayList<CarteObjectif>();
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
    

}
