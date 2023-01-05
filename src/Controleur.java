package src;

import com.formdev.flatlaf.FlatLightLaf;

import java.io.File;
import java.util.List;
import java.awt.Image;
import java.awt.image.BufferedImage;

import src.ihm.FrameAcceuil;
import src.ihm.FramePrincipale;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Joueur;
import src.metier.Metier;
import src.metier.Pioche;

public class Controleur {

    private Metier metier;
    private FrameAcceuil ihmAcceuil;
    private FramePrincipale ihm;
    private BufferedImage imagePlateau;


    private Joueur joueurCourant;

    public Controleur() {
        this.metier = new Metier(this);
        this.ihmAcceuil = new FrameAcceuil(this);
        this.ihm = new FramePrincipale(this);

        this.joueurCourant = new Joueur("Pedrolito", 54786);
        //this.joueurCourant = this.metier.getLstJoueurs().get(0);
    }

    public void majIHM() {
        this.ihm.majIHM();
    }

    public Metier getMetier(){
        return this.metier;
    }

    public Pioche getPioche() {
        return this.metier.getPioche();
    }

    public List<Joueur> getLstJoueurs() {
        return this.metier.getLstJoueurs();
    }

    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }

    public FrameAcceuil getIHMAcceuil(){
        return this.ihmAcceuil;
    }
    public void majPioche() {
        this.ihm.majPioche();
    }

    public void piocherVehicule(int i) {
        this.joueurCourant.ajouterCarteVehicule(this.metier.getPioche().piocherVehicule(i));
    }

    // public void piocherObjectif() {
    //     this.joueurCourant.ajouterCarteObjectif(this.metier.getPioche().piocherObjectif());
    // }

    
    //
     public void setImagePlateau(BufferedImage image) {
        this.imagePlateau = image; 
        this.ihm.majIHM();
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

    public static void main (String[] args) {
        //FlatLightLaf.setup();
        Controleur ctrl = new Controleur();
        //ctrl.getMetier().lireXML(new File("./src/data/mappe/Europe.xml"));
        //ctrl.majIHM();
    }

	
}