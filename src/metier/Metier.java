package src.metier;

import src.Controleur;

import java.util.ArrayList;


import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;

import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javax.swing.JOptionPane;

public class Metier {
    
    private Pioche pioche;
	private final int  DIAMETRE = 20;
	private int  nbrJoueurMinimum;
	private int  nbrJoueurMaximum;
	private int  nbrJoueurMiniDoubleRoute;
	private int  nbVehiculeJoueur;
	private int  nbVehiculeFinPartie;
	private int  nbPointCheminLong; //si < 0 (=-1) alors il n y a pas la regle du chemin le plus long
    private int  longueurVehicule;
    private int  hauteurVehicule;
	private int nombreJoker;
    private Color couleurJoker;
    private double espacementVehicule;
	private List<Noeud> lstNoeuds;
    private List<Arete> lstAretes;
    private List<CarteObjectif> lstCarteObjectifs;
    private List<CarteVehicule> lstCarteVehicules;
    private List<Joueur> lstJoueurs;
    private BufferedImage bImage;

    private Joueur joueurCourant;
    private Joueur dernierJoueur;
    private int nbWagon;
    private int actionEnCours; //1 = piocher Wagon, 2 = piocher Objectif, 3 = poser Wagon
    private int nbAction;
    private Color colorSelect;
    private int[] grillePoint;
    
    private Controleur ctrl;

    public Metier(Controleur ctrl) {

        this.lstCarteObjectifs = new ArrayList<CarteObjectif>();
        this.lstCarteVehicules = new ArrayList<CarteVehicule>();

        this.pioche = new Pioche(ctrl, this.lstCarteVehicules, this.lstCarteObjectifs);
        this.nbrJoueurMinimum = 0;
        this.nbrJoueurMaximum = 0;
        this.nbrJoueurMiniDoubleRoute = 0;
        this.nbVehiculeJoueur = 0;
        this.nbVehiculeFinPartie = 0;
        this.longueurVehicule = 0;
        this.hauteurVehicule = 0;
        this.nombreJoker = 0;
        this.espacementVehicule = 0.0;
        this.couleurJoker = null;
        this.lstNoeuds = new ArrayList<Noeud>();
        this.lstAretes = new ArrayList<Arete>();

        this.lstJoueurs = new ArrayList<Joueur>();
        this.bImage = null;

        this.nbAction = 0;


        this.ctrl = ctrl;
        //TEMPORAIRE
        this.lstJoueurs.add(new Joueur("Eragon7237", -52,45));
        this.lstJoueurs.add(new Joueur("Bou",  2500,45));
        this.lstJoueurs.add(new Joueur("erasmamael", 556,45));
        
        
        this.nbAction = 0;

    }
    
    
    /** 
     * @return List<Joueur>
     */
    public List<Joueur> getLstJoueurs() {
        return this.lstJoueurs;
    }

    
    /** 
     * @return Pioche
     */
    public Pioche getPioche() {
        return this.pioche;
    }

    
    /** 
     * @return int
     */
    public int getDiametre(){
        return this.DIAMETRE;
    }

    
    /** 
     * @return double
     */
    public double getEspacementVehicule() {
		return this.espacementVehicule;
	} 
    
    
    /** 
     * @return int
     */
    public int getNbrJoueurMinimum(){
        return this.nbrJoueurMinimum;
    }

    
    /** 
     * @return int
     */
    public int getNbrJoueurMaximum(){
        return this.nbrJoueurMaximum;
    }

    
    /** 
     * @return int
     */
    public int getNbrJoueurMinimumDoubleRoute(){
        return this.nbrJoueurMiniDoubleRoute;
    }

    
    /** 
     * @return int
     */
    public int getNbVehiculeJoueur(){
        return this.nbVehiculeJoueur;
    }

    
    /** 
     * @return int
     */
    public int getNbVehiculeFinPartie(){
        return this.nbVehiculeFinPartie;
    }

    
    /** 
     * @return int
     */
    public int getNbPointCheminLong(){
        return this.nbPointCheminLong;
    }

    
    /** 
     * @return int
     */
    public int getLongueurVehicule() {
        return this.longueurVehicule;
    }

    
    /** 
     * @return int
     */
    public int getHauteurVehicule() {
        return this.hauteurVehicule;
    }

    
    /** 
     * @return int
     */
    public int getNbJoker(){
        return this.nombreJoker;
    }

    
    /** 
     * @return Color
     */
    public Color getCouleurJoker(){
        return this.couleurJoker;
    }

    
    /** 
     * @return Joueur
     */
    public Joueur getDernierJoueur() {
        return this.dernierJoueur;
    }

    
    /** 
     * @return int
     */
    public int getNbWagon() {
        return this.nbWagon;
    }

    
    /** 
     * @return int
     */
    public int getActionEnCours() {
        return this.actionEnCours;
    }

    
    /** 
     * @return int
     */
    public int getNbAction() {
        return this.nbAction;
    }

    
    /** 
     * @return Color
     */
    public Color getColorSelect() {
        return this.colorSelect;
    }

    
    /** 
     * @return int[]
     */
    public int[] getGrillePoint() {
        return this.grillePoint;
    }


    
    /** 
     * @return List<Noeud>
     */
    public List<Noeud> getLstNoeuds(){
        return this.lstNoeuds;
    }

    
    /** 
     * @return List<Arete>
     */
    public List<Arete> getLstAretes(){
        return this.lstAretes;
    }

    
    /** 
     * @return List<Type>
     */
    public List<Type> getLstTypes(){
        ArrayList<Type> listeType = new ArrayList<Type>();
		boolean trouve;
		for (Arete arete : this.lstAretes)
		{
			trouve = false;

			for (int i=0; i<listeType.size(); i++) 
				if (arete.getType().getColor().equals(listeType.get(i).getColor()) || arete.getType().getColor().equals(this.couleurJoker))
					trouve = true;
				
			
			if (!trouve) 
				listeType.add(arete.getType());
		}

		listeType.add(Type.creerType(this.couleurJoker));

		return listeType;
    }

    
    /** 
     * @return List<CarteVehicule>
     */
    public List<CarteVehicule> getLstCarteVehicules(){
        return this.lstCarteVehicules;
    }

    
    /** 
     * @return List<CarteObjectif>
     */
    public List<CarteObjectif> getLstCarteObjectifs(){
        return this.lstCarteObjectifs;
    }

    
    /** 
     * @return BufferedImage
     */
    public BufferedImage getImage(){
        return this.bImage;
    }

    
    /** 
     * @param joueur
     */
    public void ajouterJoueur(Joueur joueur) {
        this.lstJoueurs.add(joueur);
    }

    public void melangerPiocheCarteVehicule() {
        this.pioche.melangerCarteVehicule();
    }

    public void melangerPiocheCarteObjectif() {
        this.pioche.melangerCarteObjectif();
    }

    
    /** 
     * @param joueur
     * @return CarteObjectif[]
     */
    public CarteObjectif[] PiocheCarteObjectif(Joueur joueur)
    {
        CarteObjectif[] cartes = new CarteObjectif[3];
        if (this.pioche.getLstCarteObjectif().size() > 3) {
            cartes[0]=this.pioche.getLstCarteObjectif().get(0);
            cartes[1]=this.pioche.getLstCarteObjectif().get(0);
            cartes[2]=this.pioche.getLstCarteObjectif().get(0);
            return cartes;
        }
        JOptionPane.showMessageDialog(null,"Pioche Carte Objectif : La pioche est vide");
        return null;
    }

    
    /** 
     * @return Boolean
     */
    public Boolean areteDoubleActive(){
        if(this.lstJoueurs.size()> this.nbrJoueurMiniDoubleRoute)
            return true;
        return false;
    }

    
    /** 
     * @return int[]
     */
    public int[] generGrillePoint(){
        int longueurMax = 0;
        for (Arete arete : lstAretes) 
            if (arete.getLongueur() > longueurMax) 
                longueurMax = arete.getLongueur();
        int[] grillePoint = new int[longueurMax+1];
        grillePoint[0] = 1;
        for (int cpt =1; cpt<grillePoint.length; cpt++) {
            grillePoint[cpt] = grillePoint[cpt-1]+cpt-1;
            if(cpt == 5)
                grillePoint[cpt] = 10;

        }
            
        return grillePoint;
    }

    
    /** 
     * @param joueur
     * @return Boolean
     */
    public Boolean PiocherCarteVehicule(Joueur joueur) {
        return false;
    }

    
    /** 
     * @param nom
     * @param RGB
     */
    public void ajouterJoueur(String nom, int RGB) {
        this.lstJoueurs.add(new Joueur(nom, RGB, this.nbVehiculeJoueur));
    }

    
    /** 
     * @param joueur
     */
    public void supprimerJoueur(Joueur joueur){
        this.lstJoueurs.remove(joueur);
    }

    public void reset(){
        this.lstCarteObjectifs = new ArrayList<CarteObjectif>();
        this.lstCarteVehicules = new ArrayList<CarteVehicule>();
        this.pioche = new Pioche(ctrl, this.lstCarteVehicules, this.lstCarteObjectifs);
        this.nbrJoueurMinimum = 0;
        this.nbrJoueurMaximum = 0;
        this.nbrJoueurMiniDoubleRoute = 0;
        this.nbVehiculeJoueur = 0;
        this.nbVehiculeFinPartie = 0;
        this.longueurVehicule = 0;
        this.hauteurVehicule = 0;
        this.nombreJoker = 0;
        this.espacementVehicule = 0.0;
        this.couleurJoker = null;
        this.lstNoeuds = new ArrayList<Noeud>();
        this.lstAretes = new ArrayList<Arete>();
        this.lstJoueurs = new ArrayList<Joueur>();
        this.bImage = null;
    }

    
    /** 
     * @return Boolean
     */
    public Boolean verifFinDePartie() {
        for (Joueur joueur : this.lstJoueurs) {
            if (joueur.getNbWagon() <= this.nbVehiculeFinPartie) {
                return true;
            }
        }
        int nbAretesPrises = 0;
        if(areteDoubleActive()){
            for (Arete arete : this.lstAretes){
                if(!arete.estDisponible()){
                    nbAretesPrises++;
                }
            }
        }
        if(nbAretesPrises >= this.lstAretes.size()) return true;
        
        return false;
    }

    
    /** 
     * @param carteVehicule
     * @return boolean
     */
    public boolean verifCarteJoker(CarteVehicule carteVehicule) {
        if(carteVehicule.getType().getColor().equals(this.couleurJoker)){
            return true;
        }
        return false;
    }

    
    /** 
     * @param arete
     * @return boolean
     */
    public boolean verifVoieJoker(Arete arete) {
        if(arete.getType().getColor().equals(this.couleurJoker)){
            return true;
        }
        return false;
    }

    
    /** 
     * @param arete
     * @param joueur
     * @param color
     * @return boolean
     */
    public boolean estPrenable(Arete arete, Joueur joueur, Color color)
    {
        if(joueur.getNbCarteWagon(color) +  joueur.getNbCarteWagon(this.couleurJoker)>= arete.getLongueur())
            return true;
        return false;
    }

    public void lancerPartie() {
        nbWagon = 0;
        this.joueurCourant = this.lstJoueurs.get(0);
        
        distribuerCarteVehicule();
        this.ctrl.initPartie();
        this.ctrl.getIHM().setVisible(true);
        this.ctrl.majPioche();
        this.ctrl.getIHM().afficherJoueur(this.joueurCourant.getNom(), nbAction);
        
        grillePoint= this.generGrillePoint();

        this.ctrl.majIHM();


        this.ctrl.getIHM().desactiver();
        this.getPioche().piocherObjectif();
        this.ctrl.getFrameAfficheCarteObjectif().setVisible(true);
    }

    
    /** 
     * @return int
     */
    public int getNbActionJoueur() {
        if(this.lstJoueurs.size()>0)
            return nbAction/this.lstJoueurs.size();
        return nbAction;
    }

    
    /** 
     * @return Joueur
     */
    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }

    public void finDuTour() {
        if (dernierJoueur != null && this.joueurCourant.equals(dernierJoueur)) {
            this.ctrl.getIHM().afficherMsgErreur("C'est la fin de la partie!");
            finDePartie();
            return;
        }
        for (Joueur joueur : this.lstJoueurs) {
            if(joueur.getNbWagon() < this.nbVehiculeFinPartie){
                this.dernierJoueur = joueur;
                this.ctrl.getIHM().afficherMsgErreur(this.joueurCourant.getNom() + " a moins de de " + this.nbVehiculeFinPartie+" wagons. C'est le dernier tour pour tout le monde");
            }
        }
    
        this.nbWagon = 0;
        this.ctrl.getIHM().resetNoeudSelect();
        this.joueurCourant = this.lstJoueurs.get((this.lstJoueurs.indexOf(this.joueurCourant)+1)%this.lstJoueurs.size());
        this.nbAction++;
        this.ctrl.getIHM().afficherJoueur(this.joueurCourant.getNom(), this.nbAction/this.lstJoueurs.size());
        this.ctrl.getIHM().majIHM();

        if(this.nbAction/this.lstJoueurs.size()<1){
            this.ctrl.getIHM().desactiver();

            this.getPioche().piocherObjectif();
            this.ctrl.getFrameAfficheCarteObjectif().setVisible(true);
        }
    }

    public void poserVehicule() {
        this.actionEnCours = 3;
    }

    
    /** 
     * @param i
     */
    public void piocherVehicule(int i) {
        this.actionEnCours = 1;
        if(this.ctrl.getMetier().getPioche().getLstCarteVehicule().size()==0){
            this.ctrl.getIHM().activerComposants();
            this.ctrl.getIHM().getPanelPioche().setBtnPiocheObjectifUtilisable();
        }
        if (i==6 && i > this.pioche.getLstCarteVehicule().size()){
            this.ctrl.getIHM().afficherMsgErreur("Il n'y a plus de carte !");
            return;
        }
        if (i<6 && this.verifCarteJoker(this.lstCarteVehicules.get(i))){
            if(nbWagon==1)
                return;
            this.nbWagon=2;
        }
        else{
            this.nbWagon++;
        }
        
        this.joueurCourant.ajouterCarteVehicule(this.getPioche().piocherVehicule(i));
        this.ctrl.majIHM();
        this.ctrl.getIHM().setVisible(true);
        if(this.ctrl.getMetier().getPioche().getLstCarteVehicule().size()==0){
            this.ctrl.getIHM().activerComposants();
            this.ctrl.getIHM().getPanelPioche().setBtnPiocheObjectifUtilisable();
            finDuTour();
        }
        if(this.nbWagon==2)
        {
            this.ctrl.getIHM().activerComposants();
            this.ctrl.getIHM().getPanelPioche().setBtnPiocheObjectifUtilisable();
            finDuTour();
        }
    }

    public void finDePartie() {
        this.ctrl.getIHM().desactiver();
        this.ctrl.getIHM().setVisible(false);
        Joueur joueurCheminLPL=null;
        int cheminLPL = 0;
        int cheminLPLJ =0;
        String nomCLPL = null;
        for (Joueur i : this.lstJoueurs) {
                verifCarteObjectif(i);
                cheminLPLJ = CalculPoint.cheminLePlusLong(this.lstAretes, this.lstNoeuds, i);
                System.out.println("Le chemin le plus long de " + i.getNom() +" est de : " + cheminLPLJ);        
                if (cheminLPLJ >cheminLPL) {
                    joueurCheminLPL = i;
                    cheminLPL = cheminLPLJ;
                }
        }
        if (joueurCheminLPL != null && this.nbPointCheminLong != -1){
            joueurCheminLPL.ajouterPoint(this.nbPointCheminLong);
            nomCLPL = joueurCheminLPL.getNom();
        }
        this.ctrl.creerFrameFinPartie(nomCLPL);
    }

    
    /** 
     * @param noeud1
     * @param noeud2
     */
    public void verifAreteSelect(Noeud noeud1, Noeud noeud2){
        this.actionEnCours = 0;
        
        ArrayList<Arete> aretesSelect = new ArrayList<Arete>();
        Arete areteSelect = null;

        for (Arete arete : this.getLstAretes()) {
            if(arete.estAreteDe(noeud1, noeud2)){
                aretesSelect.add(arete);
            }
        }

        for (Arete arete : aretesSelect) {
            if (!arete.estDisponible() && !this.areteDoubleActive()){
                this.ctrl.getIHM().afficherMsgErreur("vous ne pouvez pas prendre cette arete");
                return;
            }
            if (arete.estDisponible() && arete.getProprietaire() == this.joueurCourant){
                this.ctrl.getIHM().afficherMsgErreur("Vous possedez deja cette arete");
                return;
            }

        }

        for (int index = 0; index < aretesSelect.size(); index++) 
            if (!aretesSelect.get(index).estDisponible())
                aretesSelect.remove(aretesSelect.get(index));
        

        //verif s il a selectionne quelque chose 
        if (aretesSelect.size()==0)
            return ;
        
        // si arete double , on demande à l'utilisateur de choisir entres les deux
        if(aretesSelect.size()>1)
            this.ctrl.creerFrameChoixArete(aretesSelect);
        else {
            areteSelect = aretesSelect.get(0);
            verifAreteMulti(areteSelect);
        }
    
    }

    
    /** 
     * @param areteSelect
     * @param couleurArete
     */
    public void verifAreteWagon(Arete areteSelect, Color couleurArete){
        List<CarteVehicule> carteDefausse = new ArrayList<CarteVehicule>();

        //verif à assez de carte vehicule pour prendre l'arete
        if (!this.estPrenable(areteSelect, this.joueurCourant, couleurArete)){
            this.ctrl.getIHM().afficherMsgErreur("Vous n'avez pas assez de carte pour poser cette arete");
            this.ctrl.getIHM().resetNoeudSelect();
            return;
        }


        //verif si le joueur a assez de vehicule pour poser l'arete
        if (this.joueurCourant.getNbWagon()<areteSelect.getLongueur()){
            this.ctrl.getIHM().afficherMsgErreur("Vous n'avez pas assez de wagon pour poser cette arete");
            this.ctrl.getIHM().resetNoeudSelect();
            return;
        }

        carteDefausse = this.joueurCourant.supprimerCarteVehicule(couleurArete, areteSelect.getLongueur(),this.getCouleurJoker());
        areteSelect.setProprietaire(this.joueurCourant);

        this.joueurCourant.supprimerWagon(areteSelect.getLongueur());
        this.joueurCourant.ajouterPoint(grillePoint[ areteSelect.getLongueur()]);
        this.pioche.ajouterCartePioche(carteDefausse);
        finDuTour();
    }

    
    /** 
     * @param color
     */
    public void setColorSelect(Color color){
        this.colorSelect = color;
    }

    
    /** 
     * @param joueur
     */
    public void verifCarteObjectif(Joueur joueur) {
        if (joueur.getCartesObjectif().size() == 0)
            return;
        for (CarteObjectif carteObjectif : joueur.getCartesObjectif()) 
            if (!carteObjectif.getEstReussi() && CalculPoint.aReussitDestination(this.lstAretes, this.lstNoeuds, joueur, carteObjectif)){
                joueur.ajouterPoint(carteObjectif.getPoints());
                carteObjectif.setEstReussi(true);
            }
            else {
                joueur.ajouterPoint(-carteObjectif.getPoints());
            }
    }

    
    /** 
     * @param areteSelect
     */
    public void verifAreteMulti(Arete areteSelect){
        if(verifVoieJoker(areteSelect))
            this.ctrl.creerFrameChoixWagon(areteSelect);
        
        else
            verifAreteWagon(areteSelect, areteSelect.getType().getColor());
        
    }

    
    /** 
     * @param carteChoisies
     */
    public void piocherObjectif(ArrayList<Integer> carteChoisies) {

        this.actionEnCours = 2;
        this.ctrl.getIHM().activer();
        this.pioche.deffausserCarteObjectif(joueurCourant, carteChoisies);
        this.ctrl.getFrameAfficheCarteObjectif().majIHM();
        this.ctrl.majIHM();
        this.ctrl.getIHM().setVisible(true);
        finDuTour();
    }

    
    /** 
     * @param file
     */
    public void lireXML(File file){

        try{
            SAXBuilder sxb = new SAXBuilder();
            Document document = sxb.build(file);
            Element racine = null;
            racine = document.getRootElement();

            Element regles = racine.getChild("regles");
            this.nombreJoker             = Integer.parseInt   (regles.getChildText("nombreJoker"                ));
            this.nbrJoueurMinimum         = Integer.parseInt  (regles.getChildText("nombreJoueurMinimum"        ));
            this.nbrJoueurMaximum         = Integer.parseInt  (regles.getChildText("nombreJoueurMaximum"        ));
            this.nbVehiculeJoueur         = Integer.parseInt  (regles.getChildText("nombreVehiculeJoueur"       ));
            this.nbrJoueurMiniDoubleRoute = Integer.parseInt  (regles.getChildText("nombreJoueurMiniDoubleRoute"));
            this.nbVehiculeFinPartie      = Integer.parseInt  (regles.getChildText("nombreVehiculeFin"          ));
            this.nbPointCheminLong        = Integer.parseInt  (regles.getChildText("nombrePointCheminLong"      ));
            this.longueurVehicule         = Integer.parseInt  (regles.getChildText("longueurVehicule"           ));
            this.hauteurVehicule          = Integer.parseInt  (regles.getChildText("hauteurVehicule"            ));
            this.espacementVehicule       = Double.parseDouble(regles.getChildText("espacementVehicule"         ));
            this.couleurJoker             = new Color(Integer.parseInt(regles.getChildText("couleurJoker"      )));

            List listNoeuds = racine.getChild("lstNoeuds").getChildren("noeud");
            Iterator i = listNoeuds.iterator();
            while(i.hasNext()){
                Element courant = (Element)i.next();
                this.lstNoeuds.add(new Noeud(courant.getChildText("nom"), (int)Double.parseDouble(courant.getChildText("x").trim()), (int)Double.parseDouble(courant.getChildText("y").trim()),
                (int)Double.parseDouble(courant.getChildText("x").trim()) + 20 , (int)Double.parseDouble(courant.getChildText("y").trim())+20 ));
            }

            List listArete = racine.getChild("lstAretes").getChildren("arete");
            Iterator j = listArete.iterator();
            while(j.hasNext()){
                Element courant = (Element)j.next();
                String nomNoeud1 = courant.getChildText("noeud1");
                String nomNoeud2 = courant.getChildText("noeud2");
                int    longueur  = Integer.parseInt(courant.getChildText("longueur"));
                Color  type      = new Color(Integer.parseInt(courant.getChildText("type")));				
                boolean orientation = Boolean.parseBoolean(courant.getChildText("orientation"));

                for(Noeud n : this.lstNoeuds)
                {
                    if(n.getNom().equals(nomNoeud1))
                    {
                        for(Noeud m : this.lstNoeuds)
                        {
                            if(m.getNom().equals(nomNoeud2))
                            {
                                this.lstAretes.add(new Arete(n, m, longueur, Type.creerType(type), orientation));
                            }
                        }
                    }
                }
            }

            List listCarteObjectif = racine.getChild("lstCarteObjectifs").getChildren("carteObjectif");
            Iterator k = listCarteObjectif.iterator();
            while(k.hasNext()){
                Element courant  = (Element)k.next();
                String nomNoeud1 = courant.getChildText("noeud1");
                String nomNoeud2 = courant.getChildText("noeud2");
                int    points    = Integer.parseInt(courant.getChildText("point"));

                for(Noeud n : this.lstNoeuds)
                {
                    if(n.getNom().equals(nomNoeud1))
                    {
                        for(Noeud m : this.lstNoeuds)
                        {
                            if(m.getNom().equals(nomNoeud2))
                            {
                                lstCarteObjectifs.add(new CarteObjectif(n, m, points));
                            }
                        }
                    }
                }
            
            }

            List listCarteVehicule = racine.getChild("hashMapCarteVehicules").getChildren("lstCarteVehicule");
            Iterator l = listCarteVehicule.iterator();
            while(l.hasNext()){
                Element courant = (Element)l.next();
                int 	nbCarte = Integer.parseInt(courant.getChildText("nbCarte"));
                Color   type = new Color(Integer.parseInt(courant.getChildText("type")));

                for(int m=0; m < nbCarte; m++){
                    lstCarteVehicules.add(new CarteVehicule(Type.creerType(type)));
                }
            }

            for(int m=0; m < this.nombreJoker; m++)
                    lstCarteVehicules.add(new CarteVehicule(Type.creerType(this.couleurJoker)));
                


            Element imagePlateau = racine.getChild("imagePlateau");

            String str = imagePlateau.getText();
            byte[] bytes = Base64.getDecoder().decode(str);		
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            this.bImage = ImageIO.read(bis);
            this.ctrl.setImagePlateau(bImage);
            this.pioche = new Pioche(this.ctrl, lstCarteVehicules,lstCarteObjectifs);
            this.pioche.melangerCarteVehicule();
            this.pioche.melangerCarteObjectif();

        }catch(Exception e){e.printStackTrace();}
    }

    public void distribuerCarteVehicule(){
        for( Joueur j : this.lstJoueurs){
            for(int i=0; i<4; i++){
                if(this.pioche.getLstCarteVehicule().size()> 0){j.ajouterCarteVehicule(this.pioche.retirerCarteVehicule(0));}
            }
        }
    }

    public void ajouterCartePourTest(){
    

        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(0).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(this.couleurJoker)));        

        this.getLstJoueurs().get(0).ajouterCarteObjectif(new CarteObjectif(this.lstNoeuds.get(0), this.lstNoeuds.get(1), 100));
    }   
    
    public void scenario2(){

        this.pioche.getLstCarteVehicule().clear();
        //partie visible

        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-154))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-26113))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-16776961))));

        //partie cachée
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-16777216))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-16777216))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-16777216))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-16777216))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-16777216))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-65485))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-65485))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-65485))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-65485))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(new Color(-65485))));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));
        this.pioche.getLstCarteVehicule().add(new CarteVehicule(new Type(this.couleurJoker)));

        this.ctrl.majPioche();
        this.ctrl.majIHM();

    }

    public void scenario3(){
        
        this.lstJoueurs.get(0).getCartesObjectif().clear();
        this.lstJoueurs.get(1).getCartesObjectif().clear();
        this.lstJoueurs.get(2).getCartesObjectif().clear();

        this.lstJoueurs.get(0).ajouterCarteObjectif(new CarteObjectif(this.lstNoeuds.get(0), this.lstNoeuds.get(1), 10));
        this.lstJoueurs.get(0).ajouterCarteObjectif(new CarteObjectif(this.lstNoeuds.get(22), this.lstNoeuds.get(1), 10));
        this.lstJoueurs.get(1).ajouterCarteObjectif(new CarteObjectif(this.lstNoeuds.get(23), this.lstNoeuds.get(30), 10));
        this.lstJoueurs.get(1).ajouterCarteObjectif(new CarteObjectif(this.lstNoeuds.get(4), this.lstNoeuds.get(2), 10));
        this.lstJoueurs.get(2).ajouterCarteObjectif(new CarteObjectif(this.lstNoeuds.get(0), this.lstNoeuds.get(1), 10));
        this.lstJoueurs.get(2).ajouterCarteObjectif(new CarteObjectif(this.lstNoeuds.get(0), this.lstNoeuds.get(1), 10));

        this.lstAretes.get(0).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(2).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(1).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(4).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(5).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(6).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(7).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(63).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(64).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(65).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(66).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(42).setProprietaire(this.lstJoueurs.get(0));
        this.lstAretes.get(43).setProprietaire(this.lstJoueurs.get(0));

        this.lstAretes.get(8).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(9).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(10).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(34).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(35).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(37).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(38).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(44).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(45).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(46).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(51).setProprietaire(this.lstJoueurs.get(1));
        this.lstAretes.get(69).setProprietaire(this.lstJoueurs.get(1));

        this.lstAretes.get(11).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(12).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(13).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(39).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(40).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(41).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(62).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(67).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(68).setProprietaire(this.lstJoueurs.get(2));
        this.lstAretes.get(70).setProprietaire(this.lstJoueurs.get(2));
        

        this.lstJoueurs.get(0).supprimerWagon(40);
        this.lstJoueurs.get(1).supprimerWagon(37);
        this.lstJoueurs.get(2).supprimerWagon(26);

        
        this.lstJoueurs.get(0).setPoint(100);
        this.lstJoueurs.get(1).setPoint(100);
        this.lstJoueurs.get(2).setPoint(100);

        this.ctrl.majPioche();
        this.ctrl.majIHM();
        
    }
	

}
