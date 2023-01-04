package src.metier;

import java.util.Collection;
import java.util.List;
import java.util.Collections;


public class Pioche {
    
    private List<CarteVehicule> lstCartesVehicule;
    private List<CarteObjectif> lstCartesObjectif;
    
    public Pioche(List<CarteVehicule> lstCartesVehicule, List<CarteObjectif> lstCartesObjectif) {
        this.lstCartesVehicule = lstCartesVehicule;
        this.lstCartesObjectif = lstCartesObjectif;
    }

    public List<CarteVehicule> getLstCartesVehicule() {
        return this.lstCartesVehicule;
    }

    public List<CarteObjectif> getLstCartesObjectif() {
        return this.lstCartesObjectif;
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
