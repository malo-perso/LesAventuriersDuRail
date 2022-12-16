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

    //met à jour l'IHM
    public void majIHM() {
        this.IHM.majIHM();
    }

    //geter de l IHM
    public FramePrincipale getIHM() {
        return this.IHM;
    }

    //geter du metier
    public GererXML getMetier() {
        return this.gererXML;
    }

    //GROS BORDEL EN DESSOUS
    //              || ||
    //              || ||
    //              \/ \/
    //BAHAHAHAHH
    
    public void ajouterNoeud(String nom, int x, int y, int nomX, int nomY) {
        this.gererXML.ajouterNoeud(nom, x, y, nomX, nomY);
        this.majIHM(); // modifié
    }

    public void setPositionNoeud(Noeud noeud, int x, int y, int nomX, int nomY) {
        this.IHM.setPositionNoeud(noeud, x, y, nomX, nomY);
    }

    public void supprimerNoeud(int lig) {
        this.gererXML.supprimerNoeud(lig);
        this.majIHM();
    }

    //TO DO
	public void enregistrer() {
        System.out.println("Enregistrer");
	}

    public ArrayList<Noeud> lstNoeudXMLtoIHM() {
        //test
        ArrayList<Noeud> lstNoeud = new ArrayList<Noeud>();
        lstNoeud.add(new Noeud("test",5,10,5,10));
        lstNoeud.add(new Noeud("ville2",25,10,25,10));

        return this.gererXML.getLstNoeuds();
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
        return this.IHM.getNoeuds();
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