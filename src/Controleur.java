package src;

import com.formdev.flatlaf.FlatLightLaf;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

import src.ihm.FrameAcceuil;
import src.ihm.FramePrincipale;
import src.metier.Arete;
import src.ihm.FrameAfficheCarteObjectif;
import src.ihm.FrameChoixArete;
import src.ihm.FrameChoixWagon;
import src.ihm.FrameFinPartie;
import src.metier.Joueur;
import src.metier.Metier;

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
        this.ihmAcceuil.dispose();
        this.ihm = new FramePrincipale(this);
        this.setImagePlateau(this.imagePlateau);
        this.frameAfficheCarteObjectif = new FrameAfficheCarteObjectif(this);
    }
    
    
    /** 
     * @return FramePrincipale
     */
    public FramePrincipale getIHM(){
        return this.ihm;
    }

    
    /** 
     * @return FrameAfficheCarteObjectif
     */
    public FrameAfficheCarteObjectif getFrameAfficheCarteObjectif(){
        return this.frameAfficheCarteObjectif;
    }

    
    /** 
     * @return Metier
     */
    public Metier getMetier(){
        return this.metier;
    }

    
    /** 
     * @return FrameAcceuil
     */
    public FrameAcceuil getIHMAcceuil(){
        return this.ihmAcceuil;
    }

    
    /** 
     * @return ArrayList<Integer>
     */
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

    
    /** 
     * @param lstArete
     */
    public void creerFrameChoixArete(ArrayList<Arete> lstArete){
        new FrameChoixArete(this, lstArete);
    }

    
    /** 
     * @param arete
     */
    public void creerFrameChoixWagon(Arete arete) {
        new FrameChoixWagon(this, arete, this.metier.getJoueurCourant().getListType());
    }


    public void piocherObjectif(){
        this.metier.getPioche().piocherObjectif();
        this.frameAfficheCarteObjectif.majIHM();
    }

    
    /** 
     * @param image
     */
    public void setImagePlateau(BufferedImage image) {
        this.imagePlateau = image;
    }

    
    /** 
     * @param nom
     * @param RGB
     */
    public void ajouterJoueur(String nom, int RGB){
        this.metier.ajouterJoueur(nom, RGB);
        this.ihmAcceuil.MAJjoueur();
    }

    
    /** 
     * @param joueur
     */
    public void supprimerJoueur(Joueur joueur){
        this.metier.supprimerJoueur(joueur);
        this.ihmAcceuil.MAJjoueur();
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getImagePlateau() {
		return this.imagePlateau;
	}

    public void dispose(){
        this.ihmAcceuil.dispose();
        this.ihm.dispose();
        this.ihmAcceuil.dispose();
    }
    
    
    /** 
     * @param args
     */
    public static void main (String[] args) {
        FlatLightLaf.setup();
        new Controleur();
    }	
}

