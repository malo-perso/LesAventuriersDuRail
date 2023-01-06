package src;

import com.formdev.flatlaf.FlatLightLaf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.image.BufferedImage;

import src.ihm.FrameAcceuil;
import src.ihm.FramePrincipale;
import src.metier.Arete;
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
    private int nbWagon;
    private int actionEnCours; //1 = piocher Wagon, 2 = piocher Obejectif, 3 = poser Wagon

    public Controleur() {
        this.metier = new Metier(this);
        this.ihmAcceuil = new FrameAcceuil(this);
    }

    public void lancerPartie() {
        this.ihmAcceuil.setVisible(false);
        this.ihm = new FramePrincipale(this);
        setImagePlateau(imagePlateau);
        this.ihm.setVisible(true);
        this.majPioche();
        this.joueurCourant = this.metier.getLstJoueurs().get(0);
        this.ihm.majIHM();

    }

    public void majIHM() {
        this.ihm.majIHM();
    }

    public void resetMetier(){
        this.metier.reset();
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

    public Boolean verifFinDePartie() {
        return this.metier.verifFinDePartie();
    }

    public void gestionTour() {
        //if (this.verifFinDePartie()) 
            //this.ihm.finDePartie();
        this.nbWagon = 0;
        this.joueurCourant = this.metier.getLstJoueurs().get((this.metier.getLstJoueurs().indexOf(this.joueurCourant)+1)%this.metier.getLstJoueurs().size());
        this.ihm.majIHM();
    }

    public void poserWehicule() {
        this.actionEnCours = 3;
        //this.ihm.poserVehicule();
    }

    public void piocherVehicule(int i) {
        this.actionEnCours = 1;

        //verif joker à faire
        if (i<6 && this.metier.verifCarteJoker(this.getMetier().getLstCartesVehicules().get(i)))
            this.nbWagon=2;
        else    
            this.nbWagon++;
        
        this.joueurCourant.ajouterCarteVehicule(this.metier.getPioche().piocherVehicule(i));
        
        if(this.nbWagon==2)
            gestionTour();
    }

    public void poserWagon(Arete arete) {
        this.actionEnCours = 0;
        
        gestionTour();
    }

    public void piocherObjectif(ArrayList<Integer> carteChoisie) {

        this.actionEnCours = 2;
        this.metier.getPioche().deffausserCarteObjectif(joueurCourant, carteChoisie);
        gestionTour();
    }

     public void setImagePlateau(BufferedImage image) {
        this.imagePlateau = image; 
        //this.ihm.majIHM();
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

    public int getAction() {
        return this.actionEnCours;
    }

    public static void main (String[] args) {
        FlatLightLaf.setup();
        Controleur ctrl = new Controleur();
    }

	
}