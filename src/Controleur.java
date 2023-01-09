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
import src.ihm.grilles.ListDialog;
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
import src.metier.point;

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
    private Color colorSelect;
    private int[] grillePoint;

    public Controleur() {
        this.metier = new Metier(this);
        this.ihmAcceuil = new FrameAcceuil(this);
        this.nbAction = 0;
    }

    
    public void lancerPartie() {
        nbWagon = 0;
        this.ihmAcceuil.setVisible(false);
        this.joueurCourant = this.metier.getLstJoueurs().get(0);
        //provisoire
        //this.metier.ajouterCartePourTest();
        //
        this.metier.distribuerCarteVehicule();
        this.ihm = new FramePrincipale(this);
        this.frameAfficheCarteObjectif = new FrameAfficheCarteObjectif(this);
        setImagePlateau(imagePlateau);
        this.ihm.setVisible(true);
        this.majPioche();
        this.ihm.afficherJoueur(this.joueurCourant.getNom(), nbAction);
        
        grillePoint= this.metier.generGrillePoint();

        this.ihm.majIHM();


    
        this.ihm.desactiver();
        this.getPioche().piocherObjectif();
        this.frameAfficheCarteObjectif.setVisible(true);
    
        
    }

    public int getNbActionJoueur() {
        if(this.metier.getLstJoueurs().size()>0)
            return nbAction/this.metier.getLstJoueurs().size();
        return nbAction;
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

    public List<Type> getLstTypes() {
        return this.metier.getLstTypes();
    }

    public ArrayList<CarteObjectif> getLstCarteObjectifs(){
        ArrayList<CarteObjectif> carteObjectifs = new ArrayList<CarteObjectif>(this.metier.getLstCartesObjectifs());
        return carteObjectifs;
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
        System.out.println("le chemin le plus long de " + this.getJoueurCourant().getNom() +" est de : " + point.cheminLePlusLong(this.metier.getLstAretes(), this.metier.getLstNoeuds(), joueurCourant));

        verifCarteObjectif();
        this.nbWagon = 0;
        this.ihm.resetNoeudSelect();
        this.joueurCourant = this.metier.getLstJoueurs().get((this.metier.getLstJoueurs().indexOf(this.joueurCourant)+1)%this.metier.getLstJoueurs().size());
        this.nbAction++;
        this.ihm.afficherJoueur(this.joueurCourant.getNom(), this.nbAction/this.metier.getLstJoueurs().size());
        this.ihm.majIHM();

        if(this.nbAction/this.metier.getLstJoueurs().size()<1){
            this.ihm.desactiver();

            this.getPioche().piocherObjectif();
            this.frameAfficheCarteObjectif.setVisible(true);
        }
    }

    public void poserWehicule() {
        this.actionEnCours = 3;
        //this.ihm.poserVehicule();
    }

    public void piocherVehicule(int i) {
        this.actionEnCours = 1;
        System.out.println(nbWagon);
        if (i<6 && this.metier.verifCarteJoker(this.getMetier().getLstCartesVehicules().get(i))){
            if(nbWagon==1)
                return;
            this.nbWagon=2;
        }
        else{
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

    public void finDePartie() {
        this.ihm.desactiver();
        this.ihm.setVisible(false);
        FrameFinPartie frameFinPartie = new FrameFinPartie(this);
    }

    public void verifAreteSelect(Noeud noeud1, Noeud noeud2){
        this.actionEnCours = 0;
        
        ArrayList<Arete> aretesSelect = new ArrayList<Arete>();
        Arete areteSelect = null;

        for (Arete arete : this.metier.getLstAretes()) {
            if(arete.estAreteDe(noeud1, noeud2)){
                aretesSelect.add(arete);
            }
        }

        for (Arete arete : aretesSelect) {
            if (!arete.estDisponible() && !this.metier.areteDoubleActive()){
                this.ihm.afficherMsgErreur("vous ne pouvez pas prendre cette arete");
                return;
            }
            if (arete.estDisponible() && arete.getProprietaire() == this.joueurCourant){
                this.ihm.afficherMsgErreur("Vous possedez deja cette arete");
                return;
            }

        }

        for (Arete arete : aretesSelect) {
            if (!arete.estDisponible()){
                aretesSelect.remove(arete);
            }
        }

        //verif s il a selectionne quelque chose 
        if (aretesSelect.size()==0)
            return ;
        
        // si arete double , on demande à l'utilisateur de choisir entres les deux
        if(aretesSelect.size()>1)
            new FrameChoixArete(this, aretesSelect);
        else {
            areteSelect = aretesSelect.get(0);
            verifAreteMulti(areteSelect);
        }
    
    }

    public void verifAreteMulti(Arete areteSelect){
        if(this.metier.verifVoieJoker(areteSelect))
            new FrameChoixWagon(this, areteSelect, this.joueurCourant.getListType());
        
        else
            verifAreteWagon(areteSelect, areteSelect.getType().getColor());
        
    }

    public void verifAreteWagon(Arete areteSelect, Color couleurArete){
        List<CarteVehicule> cateDefausse = new ArrayList<CarteVehicule>();

        //verif à assez de carte vehicule pour prendre l'arete
        if (!this.metier.estPrenable(areteSelect, this.joueurCourant, couleurArete)){
            this.ihm.afficherMsgErreur("Vous n'avez pas assez de carte pour poser cette arete");
            this.ihm.resetNoeudSelect();
            return;
        }


        //verif si le joueur a assez de vehicule pour poser l'arete
        if (this.joueurCourant.getNbWagon()<areteSelect.getLongueur()){
            this.ihm.afficherMsgErreur("Vous n'avez pas assez de wagon pour poser cette arete");
            this.ihm.resetNoeudSelect();
            return;
        }

        cateDefausse = this.joueurCourant.supprimerCarteVehicule(couleurArete, areteSelect.getLongueur(),this.metier.getCouleurJoker());
        areteSelect.setProprietaire(this.joueurCourant);

        this.joueurCourant.supprimerWagon(areteSelect.getLongueur());
        this.joueurCourant.ajouterPoint(grillePoint[ areteSelect.getLongueur()]);
        this.metier.getPioche().ajouterCartePioche(cateDefausse);
        finDuTour();
    }

    public void setColorSelect(Color color){
        this.colorSelect = color;
    }

    public void verifCarteObjectif() {
        if (this.joueurCourant.getCartesObjectif().size() == 0)
            return;
        for (CarteObjectif carteObjectif : this.joueurCourant.getCartesObjectif()) 
            if (!carteObjectif.getEstReussi() && point.aReussitDestination(this.metier.getLstAretes(), this.metier.getLstNoeuds(), joueurCourant, carteObjectif)){
                this.joueurCourant.ajouterPoint(carteObjectif.getPoints());
                carteObjectif.setEstReussi(true);
                System.out.println("Carte Objectif reussi de "+ carteObjectif.getNoeud1().getNom() + " à " + carteObjectif.getNoeud2().getNom());
            }
    }

    public void piocherObjectif(){
        this.metier.getPioche().piocherObjectif();
        this.frameAfficheCarteObjectif.majIHM();
    }

    public void piocherObjectif(ArrayList<Integer> carteChoisies) {

        this.actionEnCours = 2;
        this.getIHM().activer();
        this.metier.getPioche().deffausserCarteObjectif(joueurCourant, carteChoisies);
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