package src;

import com.formdev.flatlaf.FlatLightLaf;

import java.io.File;
import java.util.List;
import java.awt.Image;
import java.awt.image.BufferedImage;

import src.ihm.FramePrincipale;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;
import src.metier.Joueur;
import src.metier.Metier;
import src.metier.Pioche;

public class Controleur {

    private Metier metier;
    private FramePrincipale ihm;
    private BufferedImage imagePlateau;



    public Controleur() {
        this.metier = new Metier(this);
        this.ihm = new FramePrincipale(this);
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

     public void setImagePlateau(BufferedImage image) {
        this.imagePlateau = image; 
        this.ihm.majIHM();
    }

    public BufferedImage getImagePlateau() {
		return this.imagePlateau;
	}

    public static void main (String[] args) {
        FlatLightLaf.setup();
        Controleur ctrl = new Controleur();
        ctrl.getMetier().lireXML(new File("./src/data/mappe/Europe.xml"));
        ctrl.majIHM();
    }

	
}