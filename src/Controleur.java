package src;
import com.formdev.flatlaf.FlatLightLaf;

import src.ihm.*;
import src.metier.*;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controleur{

    private BufferedImage imagePlateau;

    private GererXML gererXML;

    private FramePrincipale IHM;

    public Controleur() {
        //this.imagePlateau = this.getClass().getResource("./data/images/USA.png").getPath();
        /*try {
            this.imagePlateau = ImageIO.read(new File(this.getClass().getResource("./data/images/USA.png").getPath()));
        } catch (Exception e) {
            this.imagePlateau = null;
            e.printStackTrace();
        }*/
        this.gererXML       = new GererXML(this);
        this.IHM            = new FramePrincipale(this);
    }
    
    //geter et seter du fichier de la carte
    public BufferedImage getImagePlateau() { 
        return this.imagePlateau;
    }

    public void setImagePlateau(BufferedImage image) {
        this.imagePlateau = image; 
        this.IHM.majIHM();
    }

    //permet au Controleur de lire le fichier xml
    public void lireXML(File fichier)
    {
        this.gererXML.lireXML(fichier);
        //this.IHM.majHashNoeud();
    }

    //supprime toute les arrêtes
    public void supprimerAretes()
    {
        this.gererXML.supprimerLstAretes();
    }

    //supprime tout les noeuds
    public void supprimerNoeuds()
    {
        this.gererXML.supprimerLstNoeuds();
    }

    //supprime tout les objectifs
    public void supprimerCartesObjectif()
    {
        this.gererXML.supprimerLstCarteObjectifs();
    }

    //retourne la liste des types de cartes
    public ArrayList<Color> getTypes() {
        return Type.getCouleurs();
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
    /*     Noeud     */
    /*****************/

    public void ajouterNoeud(String nom, int x, int y, int nomX, int nomY) {
        //ajoute le noeud a l'arraylist
        this.gererXML.ajouterNoeud(nom, x, y, nomX, nomY);
        //majNoeud
        this.majIHM();
        this.majNoeud();
    }

    public void supprimerNoeud(Noeud n) {
        this.gererXML.supprimerNoeud(n);
        this.majIHM();
        this.majNoeud();
    }

    public void setPositionNoeud(Noeud n, int x, int y, int nomX, int nomY)
    {
        this.gererXML.setPositionNoeud(n,x,y,nomX,nomY);
        this.majNoeud();
    }

    public void majNoeud() {
        this.IHM.majNoeud();
    }
    
    /*****************/
    /*     Arete     */
    /*****************/

    public void ajouterArete(Noeud n1, Noeud n2, int n, Color c) {
        this.gererXML.ajouterArete(n1,n2,n,c);
        this.majIHM();
        this.majArete();
    }

    public void supprimerArete(Arete a) {
        this.gererXML.supprimerArete(a);
        this.majIHM();
        this.majArete();
    }
    
    public void majArete()
    {
        this.IHM.majArete();
    }

    public List<Type> getLstType(){
        return this.gererXML.getLstType();
    }

    /*****************/
    /*    CarteObj   */
    /*****************/

    public void ajouterCarteObjectif(Noeud n1, Noeud n2, int points){
        this.gererXML.ajouterCarteObjectif(n1, n2, points);
        this.majIHM();
        this.majCarteObjectif();
    }

    public void supprimerCarteObjectif(CarteObjectif cObjectif){
        this.gererXML.supprimerCarteObjectif(cObjectif);
        this.majIHM();
        this.majCarteObjectif();
    }

    public void majCarteObjectif()
    {   
        this.IHM.majCarteObjectif();
    }

    /*****************/
    /*    Ecrire     */
    /*****************/

    public void ecrireXML(String path){

        this.gererXML.ecrireXML(this.imagePlateau, path);
    }
    /*****************/
    /* CarteVehicule */
    /*****************/
    /*
    public HashMap<Integer, Color> getHashVehicules()
    {
        return this.gererXML.getHashVehicules();
    }*/

    public void setNbCarte (int nbCarte) {
		//TO DO
	}
	public void setCouleur (Color c) {
		//TO DO
	}

    
	public void enregistrer() {
        System.out.println("Enregistrer");
	}

    public ArrayList<Noeud> getLstNoeuds() {
        return this.gererXML.getLstNoeuds();
    }

    public ArrayList<Arete> getLstAretes() {
        return this.gererXML.getLstAretes();
    }

    public ArrayList<CarteObjectif> getLstObjectifs() {
        return this.gererXML.getLstCarteObjectifs();
    }

    public ArrayList<Arete> lstAreteXMLtoIHM() {
        //test
        ArrayList<Arete> lstArete = new ArrayList<Arete>();
        //lstArete.add(new Arete( this.getLstNoeuds().get(0) , this.getLstNoeuds().get(1)  ,  10, Type.creerType("orange")));
        
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

    /*****************/
    /*      Main     */
    /*****************/

    public static void main(String[] args) {
        FlatLightLaf.setup();
        Controleur controleur = new Controleur();
    }
    
}