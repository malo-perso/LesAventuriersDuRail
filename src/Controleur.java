package src;

import src.ihm.*;
import src.metier.*;

import java.util.ArrayList;

public class Controleur{

    private boolean finPartie;
    
    private ArrayList<Joueur> lstJoueurs;
    private FramePrincipale IHM;
    //private Metier metier;

    public Controleur() {
        
        this.finPartie = false;
        this.lstJoueurs = new ArrayList<>();
        //this.metier = new Metier(this);
        this.IHM = new FramePrincipale(this);


    }

    //public void setFichierPlateau(String fic) {this.FichierPlateau = fic; this.ihm.maj();}

    /*public void changerPanel(String panel){
        FramePrincipale.setContentPane(panel);
    }*/
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

    public void changerPanel(String panel){
        IHM.changePanel(panel);
    }

    

	public void enregistrer() {
        System.out.println("Enregistrer");
	}

    public static void main(String[] args) {
        Controleur controleur = new Controleur();
    }


}