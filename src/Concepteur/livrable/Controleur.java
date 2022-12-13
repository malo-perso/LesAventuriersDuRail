package livrable;

import livrable.IHM.FramePrincipale;
import livrable.Metier.Joueur;
import livrable.Metier.Metier;

import java.util.ArrayList;

public class Controleur{
    private boolean finPartie;
    private ArrayList<Joueur> lstJoueurs;
    private FramePrincipale IHM;
    private Metier metier;
    public Controleur() {
        this.finPartie = false;
        this.lstJoueurs = new ArrayList<>();
        this.metier = new Metier(this);

        this.IHM = new FramePrincipale(this);
    }
    /*
    public boolean PrendreRoute()
    {
        return true;
    }

    public boolean PrendreCarteWagon()
    {
        return true;
    }

    public int gagnerPoints(Joueur joueur, int taille)
    {
        switch (taille) {
            case 1:
                joueur.earnPoints(taille);
                break;
        
            default:
                break;
        }
        return 0;
    }*/


}