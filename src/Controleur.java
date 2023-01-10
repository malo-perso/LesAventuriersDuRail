package src;

import com.formdev.flatlaf.FlatLightLaf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;

import src.ihm.FrameAcceuil;
import src.ihm.FramePrincipale;
import src.metier.Arete;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.ihm.FrameAfficheCarteObjectif;
import src.ihm.FrameChoixArete;
import src.ihm.FrameChoixWagon;
import src.ihm.FrameFinPartie;
import src.metier.Joueur;
import src.metier.Metier;
import src.metier.Noeud;
import src.metier.Pioche;
import src.metier.Type;
import src.metier.CalculPoint;

public class Controleur {

    private Metier metier;
    private FrameAcceuil ihmAcceuil;
    private FramePrincipale ihm;
    private FrameAfficheCarteObjectif frameAfficheCarteObjectif;
    private BufferedImage imagePlateau;

    public Controleur() {
        this.metier = new Metier(this);
        this.ihmAcceuil = new FrameAcceuil(this);
    }

    public void lancerPartie(){
        this.metier.lancerPartie();
    }

    public void majIHM() {
        this.ihm.majIHM();
    }

    public void resetMetier(){
        this.metier.reset();
    }

    public void initPartie(){
        this.ihm = new FramePrincipale(this);
        this.setImagePlateau(this.imagePlateau);
        this.frameAfficheCarteObjectif = new FrameAfficheCarteObjectif(this);
    }
    
    public FramePrincipale getIHM(){
        return this.ihm;
    }

    public FrameAfficheCarteObjectif getFrameAfficheCarteObjectif(){
        return this.frameAfficheCarteObjectif;
    }

    public Metier getMetier(){
        return this.metier;
    }

    public FrameAcceuil getIHMAcceuil(){
        return this.ihmAcceuil;
    }

    public ArrayList<Integer> getCarteobjectifChoisie(){
        return this.frameAfficheCarteObjectif.getCarteObjectifChoisie();
    }

    public void majPioche() {
        this.ihm.majPioche();
    }

    public void creerFrameFinPartie(){
        FrameFinPartie frameFinPartie = new FrameFinPartie(this);
        frameFinPartie.setVisible(true);
    }

    public void creerFrameChoixArete(ArrayList<Arete> lstArete){
        FrameChoixArete frameChoixArete = new FrameChoixArete(this, lstArete);
    }

    public void creerFrameChoixWagon(Arete arete) {
        new FrameChoixWagon(this, arete, this.metier.getJoueurCourant().getListType());
    }


    public void piocherObjectif(){
        this.metier.getPioche().piocherObjectif();
        this.frameAfficheCarteObjectif.majIHM();
    }

    public void setImagePlateau(BufferedImage image) {
        this.imagePlateau = image;
    }

    public void ajouterJoueur(String nom, int RGB){
        this.metier.ajouterJoueur(nom, RGB);
        this.ihmAcceuil.MAJjoueur();
    }

    public void supprimerJoueur(Joueur joueur){
        this.metier.supprimerJoueur(joueur);
        this.ihmAcceuil.MAJjoueur();
    }

    public BufferedImage getImagePlateau() {
		return this.imagePlateau;
	}

    public void dispose(){
        this.ihmAcceuil.dispose();
        this.ihm.dispose();
        this.ihmAcceuil.dispose();
    }
    
    public static void main (String[] args) {
        FlatLightLaf.setup();
        Controleur ctrl = new Controleur();
    }	
}

