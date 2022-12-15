package src.metier;

import src.Controleur;

import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GererXML {

	private Controleur       ctrl;
	private ArrayList<Noeud> lstNoeuds;
	private ArrayList<Arete> lstAretes;
	private ArrayList<CarteObjectif> lstCarteObjectifs;

	public GererXML(Controleur ctrl){

		this.ctrl = ctrl;
		this.lstNoeuds = new ArrayList<Noeud>();

		this.lstNoeuds.add(new Noeud("A", 0, 0));
		this.lstNoeuds.add(new Noeud("B", 0, 1));
		this.lstNoeuds.add(new Noeud("C", 0, 2));

		this.lstAretes = new ArrayList<Arete>();

		this.lstAretes.add(new Arete(this.lstNoeuds.get(0), this.lstNoeuds.get(1), 1, new Type("marron")));
		this.lstAretes.add(new Arete(this.lstNoeuds.get(1), this.lstNoeuds.get(2), 1, new Type("rouge")));
		
		this.lstCarteObjectifs = new ArrayList<CarteObjectif>();

		this.lstCarteObjectifs.add(new CarteObjectif(lstNoeuds.get(0), lstNoeuds.get(1), 5));
		this.lstCarteObjectifs.add(new CarteObjectif(lstNoeuds.get(1), lstNoeuds.get(2), 3));
	}

	public void ecrireXML(){
		try{
			/*ImagesCarteObj 1 fois
			 * recto
			 * verso
			 * 
			 * Thème principale image de fond
			 * Nom Mappe
			 */
			File file = new File("./src/data/mappe/mappe.xml");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n\n");

			bw.write("<regles>\n" + 
					 "\t<nombreJoueurMinimum>" + this.ctrl.getNombreJoueurMinimum() + "</nombreJoueurMinimum>\n" +
					 "\t<nombreJoueurMaximum >" + this.ctrl.getNombreJoueurMaximum() + "</nombreJoueurMaximum>\n" +
					 "\t<nombreJoueurMiniDoubleRoute >" + this.ctrl.getNombreJoueurMiniDoubleRoute() + "</nombreJoueurMiniDoubleRoute>\n" +
					 "</regles>\n");

			bw.write("<lstNoeuds>"                         		 + "\n");

			

			for(Noeud n : this.lstNoeuds){
				bw.write("	<noeud>"                +"\n" +
						   "\t\t<nom>" + n.getNom() + "</nom>" + "\n" +
						   "\t\t<x>"   + n.getX()   + "</x>"   + "\n" +
						   "\t\t<y>"   + n.getY()   + "</y>"   + "\n" +
						"    </noeud>\n");
			}
			bw.write("</lstNoeuds>\n");

			bw.write("<lstAretes>\n");
			for(Arete a : this.lstAretes){
				bw.write("	<arete>"                +"\n" +
						   "\t\t<noeud1>" + a.getNoeud1() + "</noeud1>" + "\n" +
						   "\t\t<noeud2>" + a.getNoeud2() + "</noeud2>" + "\n" +
						   "\t\t<longueur>" + a.getLongueur() + "</longueur>" + "\n" +
						   "\t\t<type>" + a.getType() + "</type>" + "\n" + 
						"    </arete>\n");
			}
			bw.write("</lstAretes>\n");

			bw.write("<lstCarteObjectifs>\n");
			for(CarteObjectif co : this.lstCarteObjectifs){
				bw.write("	<carteObjectif>"                +"\n" +
						   "\t\t<noeud1>" + co.getNoeud1() + "</noeud1>" + "\n" +
						   "\t\t<noeud2>" + co.getNoeud2() + "</noeud2>" + "\n" +
						   "\t\t<point>" + co.getPoints() + "</point>" + "\n" +"\n" +
						"    </carteObjectif>\n");
			}
			bw.write("</lstCarteObjectifs>\n");
			bw.close();
		}catch(Exception e){e.printStackTrace();}
	}

	public void lireXML(){

	}

	public static void main(String[] args){
		Controleur ctrl = new Controleur();
		GererXML g = new GererXML(ctrl);

		g.ecrireXML();
	}
}