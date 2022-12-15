package src;

import src.ihm.*;
import src.metier.*;

import java.util.ArrayList;

public class Controleur{
    /*private boolean finPartie;
    private ArrayList<Joueur> lstJoueurs;
    private FramePrincipale IHM;
    private Metier metier;*/

    private int nombreJoueurMinimum;
    private int nombreJoueurMaximum;
    private int nombreJoueurMiniDoubleRoute;

    private FramePrincipale IHM;

    public Controleur() {
        /*this.finPartie = false;
        this.lstJoueurs = new ArrayList<>();
        this.metier = new Metier(this);*/
        this.IHM = new FramePrincipale(this);


    }

    public int getNombreJoueurMinimum() {
        return this.nombreJoueurMinimum;
    }

    public int getNombreJoueurMaximum() {
        return this.nombreJoueurMaximum;
    }

    public int getNombreJoueurMiniDoubleRoute() {
        return this.nombreJoueurMiniDoubleRoute;
    }

    public void setNombreJoueurMinimum(int nombreJoueurMinimum) {
        this.nombreJoueurMinimum = nombreJoueurMinimum;
    }

    public void setNombreJoueurMaximum(int nombreJoueurMaximum) {
        this.nombreJoueurMaximum = nombreJoueurMaximum;
    }

    public void setNombreJoueurMiniDoubleRoute(int nombreJoueurMiniDoubleRoute) {
        this.nombreJoueurMiniDoubleRoute = nombreJoueurMiniDoubleRoute;
    }

    public static void main(String[] args) {
        Controleur controleur = new Controleur();
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