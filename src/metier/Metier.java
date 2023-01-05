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
	private int  diametre;
	private int  nbrJoueurMinimum;
	private int  nbrJoueurMaximum;
	private int  nbrJoueurMiniDoubleRoute;
	private int  nbVehiculeJoueur;
	private int  nbVehiculeFinPartie;
	private int  nbPointCheminLong; //si < 0 (=-1) alors il n y a pas la regle du chemin le plus long
    private int  longueurVehicule;
    private int  hauteurVehicule;
	private int nombreJoker;
    private double espacementVehicule;
	private List<Noeud> lstNoeuds;
    private List<Arete> lstAretes;
    private List<CarteObjectif> lstCarteObjectifs;
    private List<CarteVehicule> lstCarteVehicules;
    private List<Joueur> lstJoueurs;
    private BufferedImage bImage;
    
    private Controleur ctrl;

    public Metier(Controleur ctrl) {
        this.pioche = null;//a faire
        this.diametre = 0;
        this.nbrJoueurMinimum = 0;
        this.nbrJoueurMaximum = 0;
        this.nbrJoueurMiniDoubleRoute = 0;
        this.nbVehiculeJoueur = 0;
        this.nbVehiculeFinPartie = 0;
        this.longueurVehicule = 0;
        this.hauteurVehicule = 0;
        this.nombreJoker = 0;
        this.espacementVehicule = 0.0;
        this.lstNoeuds = new ArrayList<Noeud>();
        this.lstAretes = new ArrayList<Arete>();
        this.lstCarteObjectifs = new ArrayList<CarteObjectif>();
        this.lstCarteVehicules = new ArrayList<CarteVehicule>();
        this.lstJoueurs = new ArrayList<Joueur>();
        this.bImage = null;

        this.ctrl = ctrl;

        
    }

    public List<Joueur> getLstJoueurs() {
        return this.lstJoueurs;
    }

    public Pioche getPioche() {
        return this.pioche;
    }

    public void ajouterJoueur(Joueur joueur) {
        this.lstJoueurs.add(joueur);
    }

    public Boolean Piocher(Joueur joueur) {
        if (this.pioche.getLstCartesVehicule().size() > 0) {
            joueur.getCartesVehicule().add(this.pioche.getLstCartesVehicule().get(0));
            this.pioche.getLstCartesVehicule().remove(0);
            return true;
        }
        return false;
    }

    public void melangerPiocheCarteVehicule() {
        this.pioche.melangerCarteVehicule();
    }

    public void melangerPiocheCarteObjectif() {
        this.pioche.melangerCarteObjectif();
    }

    public void lireXML(File file){

        try{
            SAXBuilder sxb = new SAXBuilder();
            Document document = sxb.build(file);
            Element racine = null;
            racine = document.getRootElement();

            Element regles = racine.getChild("regles");
            this.nbrJoueurMinimum         = Integer.parseInt  (regles.getChildText("nombreJoueurMinimum"        ));
            this.nbrJoueurMaximum         = Integer.parseInt  (regles.getChildText("nombreJoueurMaximum"        ));
            this.nbVehiculeJoueur         = Integer.parseInt  (regles.getChildText("nombreVehiculeJoueur"       ));
            this.nbrJoueurMiniDoubleRoute = Integer.parseInt  (regles.getChildText("nombreJoueurMiniDoubleRoute"));
            this.nbVehiculeFinPartie      = Integer.parseInt  (regles.getChildText("nombreVehiculeFin"          ));
            this.nbPointCheminLong        = Integer.parseInt  (regles.getChildText("nombrePointCheminLong"      ));
            this.longueurVehicule         = Integer.parseInt  (regles.getChildText("longueurVehicule"           ));
            this.hauteurVehicule          = Integer.parseInt  (regles.getChildText("hauteurVehicule"            ));
            this.espacementVehicule       = Double.parseDouble(regles.getChildText("espacementVehicule"         ));

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

            List listCarteVehicule = racine.getChildren("lstCarteVehicules");
            Iterator l = listCarteVehicule.iterator();
            while(l.hasNext()){
                Element courant = (Element)l.next();
                int 	nbCarte = Integer.parseInt(courant.getChildText("type"));
                Color   type = new Color(Integer.parseInt(courant.getChildText("nombre")));

                for(int m=0; m < nbCarte; m++){
                    lstCarteVehicules.add(new CarteVehicule(Type.creerType(type)));
                }
            }


            Element imagePlateau = racine.getChild("imagePlateau");

            String str = imagePlateau.getText();
            byte[] bytes = Base64.getDecoder().decode(str);		
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            this.bImage = ImageIO.read(bis);

            this.pioche = new Pioche(lstCarteVehicules,lstCarteObjectifs);

        }catch(Exception e){e.printStackTrace();}
    } 

}
