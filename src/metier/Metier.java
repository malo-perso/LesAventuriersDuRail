package src.metier;

import src.Controleur;

import java.util.ArrayList;


import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

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
    private Boolean action;
    
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
        this.action = false;

        this.ctrl = ctrl;
        //TEMPORAIRE
        this.lstJoueurs.add(new Joueur("Eragon7237", -52,45));
        this.lstJoueurs.add(new Joueur("Bou",  2500,45));
        this.lstJoueurs.add(new Joueur("erasmamael", 556,45));
        //ajouter des cartes vehicules et objectifs dans les listes
        this.lstCarteObjectifs.add(new CarteObjectif(new Noeud("Plic", 0, 0, 0, 0), new Noeud("Ploc", 1, 1, 1, 1), 10));
        this.lstCarteObjectifs.add(new CarteObjectif(new Noeud("oui", 0, 0, 0, 0), new Noeud("non", 1, 1, 1, 1), 10));
        this.lstCarteObjectifs.add(new CarteObjectif(new Noeud("caca", 0, 0, 0, 0), new Noeud("pipi", 1, 1, 1, 1), 10));
        this.lstCarteVehicules.add(new CarteVehicule(new Type(Color.BLACK)));
        this.lstCarteVehicules.add(new CarteVehicule(new Type(Color.BLUE)));
        this.lstCarteVehicules.add(new CarteVehicule(new Type(Color.RED)));
        this.lstCarteVehicules.add(new CarteVehicule(new Type(Color.GREEN)));
        this.lstCarteVehicules.add(new CarteVehicule(new Type(this.couleurJoker)));


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
        this.lstJoueurs.get(1).ajouterCarteVehicule(new CarteVehicule(new Type(new Color(-3355444))));

/*
        this.lstJoueurs.add(new Joueur("Eragon7237", -52));
        this.lstJoueurs.add(new Joueur("Bou",  2500));
        this.lstJoueurs.add(new Joueur("erasmamael", 556));
        this.lstJoueurs.add(new Joueur("Eragon7237", -52));
        this.lstJoueurs.add(new Joueur("Bou",  2500));
        this.lstJoueurs.add(new Joueur("erasmamael", 556));
        this.lstJoueurs.add(new Joueur("Eragon7237", -52));
        this.lstJoueurs.add(new Joueur("Bou",  2500));
        this.lstJoueurs.add(new Joueur("erasmamael", 556));
        this.lstJoueurs.add(new Joueur("Eragon7237", -52));
        this.lstJoueurs.add(new Joueur("Bou",  2500));
        this.lstJoueurs.add(new Joueur("erasmamael", 556));
*/
        //game(this.ctrl);
    }
    
/*
    public void game(Controleur ctrl){
        //tant que la condition n'est pas rempli jouer
        while(!win())
        {   
            //chaque joueur joue
            for(Joueur j : this.lstJoueurs)
            {
                //tour d'un Joueur
                play(j, ctrl);
            }
        }
    }

    public void play(Joueur j, Controleur ctrl){
        while (this.action) {
                        
        }*/

        
        /* 
        piocher une carte vehicule 
            - visible
                - si on a un joker -> on peut pas prendre une deuxième carte
                - on peut en prendre une face cachée comme visible
                - on ne peut pas prendre de Joker en deuxième carte dans tout les cas
            - face cachée
                - si on veut en prendre une visible ou une invisible après
                    - si on choisit une visible, on peut pas prendre de Joker
        piocher une carte objectif
            - sélectionne 3
            - il doit en garder 1 à 3
            - celle qu'il défosse sont renvoyées en fin de pioche -> mélanger la pioche
            - celle qu'il garde sont supprimées de la pioche
        prendre possession d'une voie 
            - si on a un nombre de carte véhicule de la couleur de la voie(ou joker), égal(ou plus) au nombre de section 
            - après, on remet celle(s) non choisies en dessous de la pioche  -> mélanger la pioche     
        
        
    }



    public boolean win(){
        //vérifie si un joueur a 2 wagons ou moins
        
        return false;
    }

    /**********************/
    /* Action des joueurs */
    /**********************/
/*
    public void piocherObjectif(){
        //piocher 3 cartes objectifs
        //choisir 1 à 3
        //les autres sont remises en fin de pioche
    }




*/

    public List<Joueur> getLstJoueurs() {
        return this.lstJoueurs;
    }

    public Pioche getPioche() {
        return this.pioche;
    }

    public int getDiametre(){
        return this.DIAMETRE;
    }

    public double getEspacementVehicule() {
		return this.espacementVehicule;
	} 
    
    public int getNbrJoueurMinimum(){
        return this.nbrJoueurMinimum;
    }

    public int getNbrJoueurMaximum(){
        return this.nbrJoueurMaximum;
    }

    public int getNbrJoueurMinimumDoubleRoute(){
        return this.nbrJoueurMiniDoubleRoute;
    }

    public int getNbVehiculeJoueur(){
        return this.nbVehiculeJoueur;
    }

    public int getNbVehiculeFinPartie(){
        return this.nbVehiculeFinPartie;
    }

    public int getNbPointCheminLong(){
        return this.nbPointCheminLong;
    }

    public int getLongueurVehicule() {
        return this.longueurVehicule;
    }

    public int getHauteurVehicule() {
        return this.hauteurVehicule;
    }

    public int getNbJoker(){
        return this.nombreJoker;
    }

    public Color getCouleurJoker(){
        return this.couleurJoker;
    }

    public List<Noeud> getLstNoeuds(){
        return this.lstNoeuds;
    }

    public List<Arete> getLstAretes(){
        return this.lstAretes;
    }

    public List<Type> getLstTypes(){
        ArrayList<Type> listeType = new ArrayList();
		boolean trouve;
		for (Arete arete : this.lstAretes)
		{
			trouve = false;

			for (int i=0; i<listeType.size(); i++) {
				if (arete.getType().getColor().equals(listeType.get(i).getColor()) || arete.getType().getColor().equals(this.couleurJoker)) {
					trouve = true;
				}
			}
			if (!trouve) {
				listeType.add(arete.getType());
			}
		}

		listeType.add(Type.creerType(this.couleurJoker));

		return listeType;
    }

    public List<CarteVehicule> getLstCartesVehicules(){
        return this.lstCarteVehicules;
    }

    public List<CarteObjectif> getLstCartesObjectifs(){
        return this.lstCarteObjectifs;
    }

    public BufferedImage getImage(){
        return this.bImage;
    }

    public void ajouterJoueur(Joueur joueur) {
        this.lstJoueurs.add(joueur);
    }

    public void melangerPiocheCarteVehicule() {
        this.pioche.melangerCarteVehicule();
    }

    public void melangerPiocheCarteObjectif() {
        this.pioche.melangerCarteObjectif();
    }

    public CarteObjectif[] PiocheCarteObjectif(Joueur joueur)
    {
        CarteObjectif[] cartes = new CarteObjectif[3];
        if (this.pioche.getLstCartesObjectif().size() > 3) {
            cartes[0]=this.pioche.getLstCartesObjectif().get(0);
            cartes[1]=this.pioche.getLstCartesObjectif().get(0);
            cartes[2]=this.pioche.getLstCartesObjectif().get(0);
            return cartes;
        }
        System.out.println("Pioche Carte Objectif : La pioche est vide");
        return null;
    }

    public Boolean PiocherCarteVehicule(Joueur joueur) {
        return false;
    }

    public void ajouterJoueur(String nom, int RGB) {
        this.lstJoueurs.add(new Joueur(nom, RGB, this.nbVehiculeJoueur));
    }

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

    public Boolean verifFinDePartie() {
        for (Joueur joueur : this.lstJoueurs) {
            if (joueur.getNbWagon() <= this.nbVehiculeFinPartie) {
                return true;
            }
        }
        return false;
    }

    

    //Pas testée
    public boolean verifCarteJoker(CarteVehicule carteVehicule) {
        if(carteVehicule.getType().getColor().equals(this.couleurJoker)){
            return true;
        }
        return false;
    }

    public boolean verifVoieJoker(Arete arete) {
        if(arete.getType().getColor().equals(this.couleurJoker)){
            return true;
        }
        return false;
    }

    public boolean estPrenable(Arete arete, Joueur joueur, Color color)
    {
        if(joueur.getNbCarteWagon(color) +  joueur.getNbCarteWagon(this.couleurJoker)>= arete.getLongueur())
            return true;
        return false;
    }

    public void lireXML(File file){

        try{
            SAXBuilder sxb = new SAXBuilder();
            Document document = sxb.build(file);
            Element racine = null;
            racine = document.getRootElement();

            Element regles = racine.getChild("regles");
            this.nombreJoker             = Integer.parseInt  (regles.getChildText("nombreJoker"                ));
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
                Element courant = (Element)k.next();
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

    

      
	

}
