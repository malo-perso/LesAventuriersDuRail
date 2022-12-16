package src;

import src.ihm.*;
import src.metier.*;

import java.io.File;
import java.util.ArrayList;

public class Controleur{

    private String FichierPlateau;

    private GererXML gererXML;

    private FramePrincipale IHM;

    public Controleur() {
        this.FichierPlateau = this.getClass().getResource("./data/images/USA.png").getPath();
        this.gererXML       = new GererXML(this);
        this.IHM            = new FramePrincipale(this);
    }
    
    //geter et seter du fichier de la carte
    public String getFichierPlateau() { 
        return this.FichierPlateau;
    }

    public void setFichierPlateau(String fic) {
        this.FichierPlateau = fic; 
        this.IHM.majIHM();
    }

    //permet au Controleur de lire le fichier xml
    public void lireXML(File fichier)
    {
        this.gererXML.lireXML(fichier);
        //this.IHM.majHashNoeud();
        this.setFichierPlateau("./src/data/images/mappe.png");
    }

    //PROVISOIRE
    //retourne la liste des types de cartes
    public ArrayList<String> getTypes() {
        ArrayList<String> couleurs = new ArrayList<String>();
        couleurs.add("violet");
        couleurs.add("rouge");
        couleurs.add("marron");
        couleurs.add("blanc");
        couleurs.add("bleu");
        couleurs.add("jaune");
        couleurs.add("vert");
        couleurs.add("noir");
        couleurs.add("multicouleur");

		return couleurs;
    }


    public void majIHM() {
        this.IHM.majIHM();
    }

    public FramePrincipale getIHM() {
        return this.IHM;
    }

    public GererXML getMetier() {
        return this.gererXML;
    }

    /*****************/
    /*     NOEUD     */
    /*****************/

    public void ajouterNoeud(String nom, int x, int y, int nomX, int nomY) {
        //ajoute le noeud a l'arraylist
        this.gererXML.ajouterNoeud(nom, x, y, nomX, nomY);
        //majNoeud
        this.majIHM(); // modifié
    }

    public void supprimerNoeud(Noeud n) {
        this.gererXML.supprimerNoeud(n);
        this.majIHM();
    }

    public void setPositionNoeud(Noeud n, int x, int y, int nomX, int nomY)
    {
        this.gererXML.setPositionNoeud(n,x,y,nomX,nomY);
    }

    public void majNoeud() {
        this.IHM.majNoeud();
    }
    
    /*****************/
    /*     Arete     */
    /*****************/

    public void ajouterArete(Noeud n1, Noeud n2, int n) {
        this.gererXML.ajouterArete(n1,n2,n);
        this.majArete();
    }
    
    public void majArete()
    {   
        //TO DO
    }

    /*****************/
    /*    CarteObj   */
    /*****************/

    public void majCarteObjectif()
    {   
        //TO DO
    }



    

    //GROS BORDEL EN DESSOUS
    //              || ||
    //              || ||
    //              \/ \/
    //BAHAHAHAHH
    
    
    //ancienne methode
    /*
    public void setPositionNoeud(Noeud noeud, int x, int y, int nomX, int nomY) {
        this.IHM.setPositionNoeud(noeud, x, y, nomX, nomY);
    }*/

    

    //TO DO
	public void enregistrer() {
        System.out.println("Enregistrer");
	}

    public ArrayList<Noeud> getLstNoeuds() {
        return this.gererXML.getLstNoeuds();
    }

    public ArrayList<Arete> lstAreteXMLtoIHM() {
        //test
        ArrayList<Arete> lstArete = new ArrayList<Arete>();
        lstArete.add(new Arete( this.getLstNoeuds().get(0) , this.getLstNoeuds().get(1)  ,  10, new Type("orange")));
        
        return lstArete;
    }

    public ArrayList<CarteObjectif> lstObjectifXMLtoIHM() {
        //test
        ArrayList<CarteObjectif> lstObjectif = new ArrayList<CarteObjectif>();
        lstObjectif.add(new CarteObjectif( this.getLstNoeuds().get(0) , this.getLstNoeuds().get(1)  ,  10));
        
        return lstObjectif;
    }
    

    public ArrayList<CarteObjectif> lstObjectifIHMtoXML() {
        return null;//this.IHM.getObjectif();
    }

    public ArrayList<String> getNomNoeuds() {
        ArrayList<Noeud> noeuds = this.gererXML.getLstNoeuds();
        ArrayList<String> nomNoeuds = new ArrayList<String>();
        for (Noeud n : noeuds) {
            nomNoeuds.add(n.getNom());
        };

        return nomNoeuds;
    }

    public boolean majPosNoeud ( int ligne, int colonne, int val ) {
        return this.gererXML.majPosNoeud  ( ligne, colonne, val );
    }

    public static void main(String[] args) {
        Controleur controleur = new Controleur();
    }
    
}