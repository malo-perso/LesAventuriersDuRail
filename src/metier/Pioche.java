package src.metier;

import java.util.ArrayList;

public class Pioche {
    
    private ArrayList<CarteVehicule> lstCartesVehicule;
    private ArrayList<CarteObjectif> lstCartesObjectif;
    
    public Pioche() {
        this.lstCartesVehicule = new ArrayList<CarteVehicule>();
        this.lstCartesObjectif = new ArrayList<CarteObjectif>();
    }

    public ArrayList<CarteVehicule> getLstCartesVehicule() {
        return this.lstCartesVehicule;
    }

    public ArrayList<CarteObjectif> getLstCartesObjectif() {
        return this.lstCartesObjectif;
    }
    

}
