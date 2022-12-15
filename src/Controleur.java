package src;

import src.ihm.*;
import src.metier.*;

import java.io.File;
import java.util.ArrayList;

public class Controleur{
    /*private boolean finPartie;
    private ArrayList<Joueur> lstJoueurs;
    private FramePrincipale IHM;
    private Metier metier;*/

    private String FichierPlateau;

    private int nombreJoueurMinimum;
    private int nombreJoueurMaximum;
    private int nombreJoueurMiniDoubleRoute;

    private FramePrincipale IHM;
    private GererXML gererXML;

    public Controleur() {
        /*this.finPartie = false;
        this.lstJoueurs = new ArrayList<>();
        this.metier = new Metier(this);*/
        this.FichierPlateau = this.getClass().getResource("./data/images/USA.png").getPath();
        this.IHM = new FramePrincipale(this);
        this.gererXML = new GererXML(this);
    }

    public String getFichierPlateau() { 
        return this.FichierPlateau;
    }
    public void setFichierPlateau(String fic) {
        this.FichierPlateau = fic; 
        this.IHM.majIHM();
    }

    public ArrayList<Noeud> lstNoeudXMLtoIHM() {
        //test
        ArrayList<Noeud> lstNoeud = new ArrayList<Noeud>();
        lstNoeud.add(new Noeud("test",5,10,5,10));
        lstNoeud.add(new Noeud("ville2",25,10,25,10));

        return lstNoeud;
    }

    public void lireXML(File fichier)
    {
        this.gererXML.lireXML(fichier);
    }
    public ArrayList<Arete> lstAreteXMLtoIHM() {
        //test
        ArrayList<Arete> lstArete = new ArrayList<Arete>();
        lstArete.add(new Arete( this.lstNoeudXMLtoIHM().get(0) , this.lstNoeudXMLtoIHM().get(1)  ,  10, new Type("orange")));
        
        return lstArete;
    }

    public ArrayList<CarteObjectif> lstObjectifXMLtoIHM() {
        //test
        ArrayList<CarteObjectif> lstObjectif = new ArrayList<CarteObjectif>();
        lstObjectif.add(new CarteObjectif( this.lstNoeudXMLtoIHM().get(0) , this.lstNoeudXMLtoIHM().get(1)  ,  10));
        
        return lstObjectif;
    }

    public ArrayList<Noeud> lstNoeudIHMtoXML() {
        //test
        
        return this.IHM.getNoeuds();
    }

    public ArrayList<CarteObjectif> lstObjectifIHMtoXML() {
        //test
        
        return null;//this.IHM.getObjectif();
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

    public ArrayList<Noeud> getTypes() {
        return null;
        // à implémenter ensuite mais je ne sais pas si on le fait dans l'IHM ou directement dans le controleur
    }

    public void majIHM() {
        this.IHM.majIHM();
    }


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