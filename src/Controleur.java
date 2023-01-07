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
import src.ihm.FrameAfficheCarteObjectif;
import src.metier.Joueur;
import src.metier.Metier;
import src.metier.Noeud;
import src.metier.Pioche;
import src.metier.Type;

public class Controleur {

    private Metier metier;
    private FrameAcceuil ihmAcceuil;
    private FramePrincipale ihm;
    private FrameAfficheCarteObjectif frameAfficheCarteObjectif;
    private BufferedImage imagePlateau;

    private Joueur joueurCourant;
    private int nbWagon;
    private int actionEnCours; //1 = piocher Wagon, 2 = piocher Objectif, 3 = poser Wagon
    private int nbAction;

    public Controleur() {
        this.metier = new Metier(this);
        this.ihmAcceuil = new FrameAcceuil(this);
        nbAction = 1;
    }

    public void lancerPartie() {
        this.ihmAcceuil.setVisible(false);
        this.joueurCourant = this.metier.getLstJoueurs().get(0);
        this.ihm = new FramePrincipale(this);
        this.frameAfficheCarteObjectif = new FrameAfficheCarteObjectif(this);
        setImagePlateau(imagePlateau);
        this.ihm.setVisible(true);
        this.majPioche();
        this.ihm.afficherJoueur(this.joueurCourant.getNom(), nbAction);
        this.ihm.majIHM();

    }

    public void majIHM() {
        this.ihm.majIHM();
    }

    public void resetMetier(){
        this.metier.reset();
    }

    public FramePrincipale getIHM(){
        return this.ihm;
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

    public ArrayList<Integer> getCarteobjectifChoisie(){
        return this.frameAfficheCarteObjectif.getCarteObjectifChoisie();
    }

    public void majPioche() {
        this.ihm.majPioche();
    }

    public Boolean verifFinDePartie() {
        return this.metier.verifFinDePartie();
    }

    public void finDuTour() {
        //if (this.verifFinDePartie()) 
            //this.ihm.finDePartie();
        this.nbWagon = 0;
        this.joueurCourant = this.metier.getLstJoueurs().get((this.metier.getLstJoueurs().indexOf(this.joueurCourant)+1)%this.metier.getLstJoueurs().size());
        nbAction++;
        this.ihm.afficherJoueur(this.joueurCourant.getNom(), nbAction/this.metier.getLstJoueurs().size());
        this.ihm.majIHM();
        System.out.println(this.joueurCourant.getNom()+" " + this.joueurCourant.getCartesVehicule().size());
    }

    public void poserWehicule() {
        this.actionEnCours = 3;
        //this.ihm.poserVehicule();
    }

    public void piocherVehicule(int i) {
        this.actionEnCours = 1;

        if (i<6 && this.metier.verifCarteJoker(this.getMetier().getLstCartesVehicules().get(i)))
        {
            if(nbWagon==1)
                return;
            this.nbWagon=2;
        }
        else
        {
            this.nbWagon++;
        }
        
        this.joueurCourant.ajouterCarteVehicule(this.metier.getPioche().piocherVehicule(i));
        
        if(this.nbWagon==2)
        {
            this.getIHM().activerComposants();
            this.getIHM().getPanelPioche().setBtnPiocheObjectifUtilisable();
            finDuTour();
        }
    }

    public void verifCarteObjectif(Joueur joueur) {
        ArrayList<CarteObjectif> carteObjectif = joueur.getCartesObjectif();
        
    }

    public void finDePartie() {
     //   this.ihm.finDePartie();
    }

    public boolean poserWagon(Noeud noeud1, Noeud noeud2) {
        this.actionEnCours = 0;
        
        ArrayList<Arete> aretesSelect = new ArrayList<Arete>();
        Arete areteSelect = null;
        Type type = null;
        ArrayList<CarteVehicule> cateDefausse = new ArrayList<CarteVehicule>();

        for (Arete arete : this.metier.getLstAretes()) {
            if(arete.estAreteDe(noeud1, noeud2) && arete.estDisponible()){
                aretesSelect.add(arete);
            }
        }

        //verif s il a selectionne quelque chose 
        if (aretesSelect.size()==0)
            return false;

        //TO DO 
        /* si arete double , on demande à l'utilisateur de choisir entres les deux
        if(aretesSelect.size()>1)
            areteSelect = this.ihm.selectArete(aretesSelect);
        else*/
        areteSelect = aretesSelect.get(0);
        
        //TO DO
        //si arete joker on demande à l'utilisateur de choisir le type de wagon qu il va poser
        /* 
        if(this.metier.verifVoieJoker(areteSelect));
            //this.ihm.selectArete(areteSelect, joueurCourant);
        else*/
        type = areteSelect.getType();

        //verif à assez de carte vehicule pour prendre l'arete
        if (this.metier.estPrenable(areteSelect, this.joueurCourant, type.getColor()))
            return false;


        //verif si le joueur a assez de vehicule pour poser l'arete
        if (this.joueurCourant.getNbWagon()<areteSelect.getLongueur())
            return false;

        this.joueurCourant.supprimerCarteVehicule(type.getColor(), areteSelect.getLongueur(),this.metier.getCouleurJoker());
        areteSelect.setProprietaire(this.joueurCourant);

        finDuTour();
        return true;
    }

    public void piocherObjectif(ArrayList<Integer> carteChoisie) {

        this.actionEnCours = 2;
        this.getIHM().activer();
        this.metier.getPioche().deffausserCarteObjectif(joueurCourant, carteChoisie);
        this.frameAfficheCarteObjectif.majIHM();
        finDuTour();
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