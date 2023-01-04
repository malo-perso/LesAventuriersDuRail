package metier;

import java.util.ArrayList;

public class Metier {
    
    private ArrayList<Joueur> lstJoueurs;
    private Pioche pioche;

    public Metier() {
        this.lstJoueurs = new ArrayList<Joueur>();
        this.pioche = new Pioche();
    }

}
