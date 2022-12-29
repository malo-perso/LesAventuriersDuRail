package src.metier;

import src.Controleur;

import java.awt.image.BufferedImage;
import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.awt.image.WritableRaster;

import javax.imageio.ImageIO;

public class GererXML {

	private Controleur       ctrl;
	private int              diametre;
	private int  nbrJoueurMinimum;
	private int  nbrJoueurMaximum;
	private int  nbrJoueurMiniDoubleRoute;
	private int  nbWagonJoueur;
	private int  nbWagonFinPartie;
	private ArrayList<Noeud> lstNoeuds;
	private ArrayList<Arete> lstAretes;
	private ArrayList<CarteObjectif> lstCarteObjectifs;
	private ArrayList<CarteVehicule> lstCarteVehicules;
	private HashMap<Type, ArrayList<CarteVehicule>> hashMapCarteVehicules;

	public GererXML(Controleur ctrl){

		this.nbrJoueurMaximum = 5;
		this.nbrJoueurMinimum = 2;
		this.nbrJoueurMiniDoubleRoute = 3;
		this.nbWagonJoueur = 45;
		this.nbWagonFinPartie = 2;

		this.ctrl = ctrl;

		this.diametre = 20;

		this.lstNoeuds = new ArrayList<Noeud>();
		this.hashMapCarteVehicules = new HashMap<Type, ArrayList<CarteVehicule>>();

		this.lstNoeuds.add(new Noeud("A", 200, 100, 220, 120));
		this.lstNoeuds.add(new Noeud("B", 1000, 200, 1100, 220));
		this.lstNoeuds.add(new Noeud("C", 800, 90, 850, 100));
		

		Type marron = Type.creerType("marron");
		Type rouge = Type.creerType("rouge");

		ArrayList<CarteVehicule> lstCarteMarron = new ArrayList<CarteVehicule>();
		lstCarteMarron.add(new CarteVehicule(Type.creerType("Marron")));
		lstCarteMarron.add(new CarteVehicule(Type.creerType("Marron")));
		lstCarteMarron.add(new CarteVehicule(Type.creerType("Marron")));

		ArrayList<CarteVehicule> lstCarteRouge = new ArrayList<CarteVehicule>();
		lstCarteRouge.add(new CarteVehicule(Type.creerType("Rouge")));
		lstCarteRouge.add(new CarteVehicule(Type.creerType("Rouge")));
		lstCarteRouge.add(new CarteVehicule(Type.creerType("Rouge")));
		lstCarteRouge.add(new CarteVehicule(Type.creerType("Rouge")));
		lstCarteRouge.add(new CarteVehicule(Type.creerType("Rouge")));
		lstCarteRouge.add(new CarteVehicule(Type.creerType("Rouge")));

		this.hashMapCarteVehicules.put(marron, lstCarteMarron);

		
		this.hashMapCarteVehicules.put(rouge, lstCarteRouge);

		this.lstCarteVehicules = new ArrayList<CarteVehicule>();

		this.lstAretes = new ArrayList<Arete>();

		this.lstAretes.add(new Arete(this.lstNoeuds.get(0), this.lstNoeuds.get(1), 4, Type.creerType("marron")));
		this.lstAretes.add(new Arete(this.lstNoeuds.get(1), this.lstNoeuds.get(2), 5, Type.creerType("rouge")));
		this.lstAretes.add(new Arete(this.lstNoeuds.get(2), this.lstNoeuds.get(0), 7, Type.creerType("bleu")));

		this.lstCarteObjectifs = new ArrayList<CarteObjectif>();

		this.lstCarteObjectifs.add(new CarteObjectif(lstNoeuds.get(0), lstNoeuds.get(1), 5));
		this.lstCarteObjectifs.add(new CarteObjectif(lstNoeuds.get(1), lstNoeuds.get(2), 3));
	}

	public int getDiametre(){
		return this.diametre;
	}

	public int setDiametre(int diametre){
		return this.diametre = diametre;
	}

	public int getNombreJoueurMinimum(){
		return this.nbrJoueurMinimum;
	}

	public int setNombreJoueurMinimum(int nbrJoueurMinimum){
		return this.nbrJoueurMinimum = nbrJoueurMinimum;
	}

	public int getNombreJoueurMaximum(){
		return this.nbrJoueurMaximum;
	}

	public int setNombreJoueurMaximum(int nbrJoueurMaximum){
		return this.nbrJoueurMaximum = nbrJoueurMaximum;
	}

	public int getNombreJoueurMiniDoubleRoute(){
		return this.nbrJoueurMiniDoubleRoute;
	}

	public int setNombreJoueurMiniDoubleRoute(int nbrJoueurMiniDoubleRoute){
		return this.nbrJoueurMiniDoubleRoute = nbrJoueurMiniDoubleRoute;
	}

	public int getNbWagonJoueur(){
		return this.nbWagonJoueur;
	}

	public int setNbWagonJoueur(int nbWagonJoueur){
		return this.nbWagonJoueur = nbWagonJoueur;
	}

	public int getNbWagonFinPartie(){
		return this.nbWagonFinPartie;
	}

	public int setNbWagonFinPartie(int nbWagonFinPartie){
		return this.nbWagonFinPartie = nbWagonFinPartie;
	}

	/*****************/
    /*     NOEUD     */
    /*****************/

	public void ajouterNoeud(String nom, int x, int y, int nomX, int nomY){
		this.lstNoeuds.add(new Noeud(nom, x, y, nomX, nomY));
	}

	public void supprimerNoeud (Noeud n) {
		this.lstNoeuds.remove(n);
	}

	public void setPositionNoeud(Noeud n, int x, int y, int nomX, int nomY)
	{	
		for (Noeud noeud : this.lstNoeuds)
		{
			if(noeud.equals(n))
			{	
				noeud.setX(x);
				noeud.setY(y);
				noeud.setNomX(nomX);
				noeud.setNomY(nomY);
				return;
			}
			this.ctrl.majNoeud();
		}
	}

	/*****************/
    /*     Arete     */
    /*****************/

	public void ajouterArete(Noeud n1, Noeud n2, int longueur, String coulType){
		this.lstAretes.add(new Arete(n1, n2, longueur, Type.creerType(coulType)));
	}

	public void supprimerArete (Arete a) {
		this.lstAretes.remove(a);
	}

	public void setLongueurArete(Arete a, int longueur)
	{
		for (Arete arete : this.lstAretes)
		{
			if(arete.equals(a))
			{
				arete.setLongueur(longueur);
				return;
			}
			this.ctrl.majArete();
		}
	}

	/*****************/
    /* CarteObjectif */
    /*****************/

	public void ajouterCarteObjectif(Noeud n1, Noeud n2, int points){
		this.lstCarteObjectifs.add(new CarteObjectif(n1, n2, points));
	}

	public void supprimerCarteObjectif(CarteObjectif co){
		this.lstCarteObjectifs.remove(co);
	}

	public void setPointCarteObjectif(CarteObjectif cObjectif, int points){

		for(CarteObjectif co : this.lstCarteObjectifs){
			if(co.equals(cObjectif)){
				co.setPoints(points);
				return;
			}
			this.ctrl.majCarteObjectif();
		}
	}

	/*****************/
	/*     Ecrire    */
	/*****************/

	public void ecrireXML(String chemin){
		try{
			File file = new File("./src/data/mappe/mappe.xml");
			File fileImage = new File(chemin);

			byte[] bytes = Files.readAllBytes(fileImage.toPath());


			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");

			bw.write("<mappe>\n");

			bw.write("\t<regles>\n" + 
					 "\t\t<nombreJoueurMinimum>" + this.getNombreJoueurMinimum() + "</nombreJoueurMinimum>\n" +
					 "\t\t<nombreJoueurMaximum >" + this.getNombreJoueurMaximum() + "</nombreJoueurMaximum>\n" +
					 "\t\t<nombreJoueurMiniDoubleRoute >" + this.getNombreJoueurMiniDoubleRoute() + "</nombreJoueurMiniDoubleRoute>\n" +
					 "\t</regles>\n");

			bw.write("\t<lstNoeuds>"                         		 + "\n");

			

			for(Noeud n : this.lstNoeuds)
			{
				bw.write("	\t<noeud>"                +"\n" +
						   "\t\t\t<nom>" + n.getNom() + "</nom>" + "\n" +
						   "\t\t\t<x>"   + n.getX()   + "</x>"   + "\n" +
						   "\t\t\t<y>"   + n.getY()   + "</y>"   + "\n" +
						   "\t\t\t<NomX>" + n.getNomX() + "</NomX>" + "\n" +
						   "\t\t\t<NomY>" + n.getNomY() + "</NomY>" + "\n" +
						"    \t</noeud>\n");
			}
			bw.write("\t</lstNoeuds>\n");

			bw.write("\t<lstAretes>\n");
			for(Arete a : this.lstAretes){
				bw.write("	\t<arete>"                +"\n" +
						   "\t\t\t<noeud1>" + a.getNoeud1().getNom() + "</noeud1>" + "\n" +
						   "\t\t\t<noeud2>" + a.getNoeud2().getNom() + "</noeud2>" + "\n" +
						   "\t\t\t<longueur>" + a.getLongueur() + "</longueur>" + "\n" +
						   "\t\t\t<type>" + a.getType() + "</type>" + "\n" + 
						"    \t</arete>\n");
			}
			bw.write("\t</lstAretes>\n");

			bw.write("\t<lstCarteObjectifs>\n");
			for(CarteObjectif co : this.lstCarteObjectifs){
				bw.write("	\t<carteObjectif>"                +"\n" +
						   "\t\t\t<noeud1>" + co.getNoeud1().getNom() + "</noeud1>" + "\n" +
						   "\t\t\t<noeud2>" + co.getNoeud2().getNom() + "</noeud2>" + "\n" +
						   "\t\t\t<point>" + co.getPoints() + "</point>" + "\n" +
						"    \t</carteObjectif>\n");
			}
			bw.write("\t</lstCarteObjectifs>\n");

			bw.write("\t<hashMapCarteVehicules>\n");

			for(Type t : this.hashMapCarteVehicules.keySet()){
				bw.write("	\t<lstCarteVehicule>"                +"\n" +
						   "\t\t\t<nbCarte>" + this.hashMapCarteVehicules.get(t).size() + "</nbCarte>" + "\n" +
						   "\t\t\t<Type>" + t + "</Type>" + "\n" +
						"    \t</lstCarteVehicule>\n");
			}

			bw.write("\t</hashMapCarteVehicules>\n");

			bw.write("\t<imagePlateau>" + Base64.getEncoder().encodeToString(bytes) +
					 "</imagePlateau>\n");
					 
			bw.write("</mappe>");
			bw.close();
		}catch(Exception e){e.printStackTrace();}
	}

	public void lireXML(File file){

		try{
			SAXBuilder sxb = new SAXBuilder();
			Document document = sxb.build(file);
			Element racine = null;
			racine = document.getRootElement();

			Element regles = racine.getChild("regles");
			this.setNombreJoueurMinimum(Integer.parseInt(regles.getChildText("nombreJoueurMinimum")));
			this.setNombreJoueurMaximum(Integer.parseInt(regles.getChildText("nombreJoueurMaximum")));
			this.setNombreJoueurMiniDoubleRoute(Integer.parseInt(regles.getChildText("nombreJoueurMiniDoubleRoute")));

			List listNoeud = racine.getChild("lstNoeuds").getChildren("noeud");
			Iterator i = listNoeud.iterator();
			while(i.hasNext()){
				Element courant = (Element)i.next();
				this.lstNoeuds.add(new Noeud(courant.getChildText("nom"), Integer.parseInt(courant.getChildText("x").trim()), Integer.parseInt(courant.getChildText("y").trim()),
				Integer.parseInt(courant.getChildText("x").trim()) + 20 , Integer.parseInt(courant.getChildText("y").trim())+20 ));
			}

			List listArete = racine.getChild("lstAretes").getChildren("arrete");
			Iterator j = listArete.iterator();
			while(j.hasNext()){
				Element courant = (Element)j.next();
				String nomNoeud1 = courant.getChildText("noeud1");
				String nomNoeud2 = courant.getChildText("noeud2");
				int    longueur  = Integer.parseInt(courant.getChildText("longueur"));
				String nomType      = courant.getChildText("type");

				for(Noeud n : this.lstNoeuds)
				{
					if(n.getNom().equals(nomNoeud1))
					{
						for(Noeud m : this.lstNoeuds)
						{
							if(m.getNom().equals(nomNoeud2))
							{
								this.lstAretes.add(new Arete(n, m, longueur, Type.creerType(nomType)));
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
								this.lstCarteObjectifs.add(new CarteObjectif(n, m, points));
							}
						}
					}
				}
			
			}

			List listCarteVehicule = racine.getChild("lstCarteVehicules").getChildren("carteVehicule");
			Iterator l = listCarteVehicule.iterator();
			while(l.hasNext()){
				Element courant = (Element)l.next();
				int 	nbCarte = Integer.parseInt(courant.getChildText("type"));
				String  nomType = courant.getChildText("nombre");

				for(int m=0; m < nbCarte; m++){
					this.lstCarteVehicules.add(new CarteVehicule(Type.creerType(nomType)));
				}

				this.hashMapCarteVehicules.put(Type.creerType(nomType), this.lstCarteVehicules);
			}


			Element imagePlateau = racine.getChild("imagePlateau");

			String str = imagePlateau.getText();
			byte[] bytes = Base64.getDecoder().decode(str);		
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			BufferedImage bImage2 = ImageIO.read(bis);
			ImageIO.write(bImage2, "png", new File("./src/data/images/mappe.png") );
		}catch(Exception e){e.printStackTrace();} 
	}

	public ArrayList<Noeud> getLstNoeuds(){
		return this.lstNoeuds;
	}

	public ArrayList<Arete> getLstAretes(){
		return this.lstAretes;
	}

	public ArrayList<CarteObjectif> getLstCarteObjectifs(){
		return this.lstCarteObjectifs;
	}

	public static void main(String[] args){

		Controleur ctrl = new Controleur();
		GererXML g = new GererXML(ctrl);

		g.ecrireXML("./src/data/images/logo.png");
		try{
			//g.lireXML(new File("./src/data/mappe/mappe.xml"));
		}catch(Exception e){e.printStackTrace();}
	}

	public void ajouterArete(Noeud n1, Noeud n2, int n) {
		//TO DO
	}
}