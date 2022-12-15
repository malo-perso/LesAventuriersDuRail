package src.metier;

import src.Controleur;

import java.awt.image.BufferedImage;
import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

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
			File file = new File("./src/data/mappe/mappe.xml");
			File fileImage = new File("./src/data/images/93baf29414f5feedacbe9eb51f1354e7.jpg");

			byte[] bytes = Files.readAllBytes(fileImage.toPath());


			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");

			bw.write("<mappe>\n");

			bw.write("\t<regles>\n" + 
					 "\t\t<nombreJoueurMinimum>" + this.ctrl.getNombreJoueurMinimum() + "</nombreJoueurMinimum>\n" +
					 "\t\t<nombreJoueurMaximum >" + this.ctrl.getNombreJoueurMaximum() + "</nombreJoueurMaximum>\n" +
					 "\t\t<nombreJoueurMiniDoubleRoute >" + this.ctrl.getNombreJoueurMiniDoubleRoute() + "</nombreJoueurMiniDoubleRoute>\n" +
					 "\t</regles>\n");

			bw.write("\t<lstNoeuds>"                         		 + "\n");

			

			for(Noeud n : this.lstNoeuds)
			{
				bw.write("	\t<noeud>"                +"\n" +
						   "\t\t\t<nom>" + n.getNom() + "</nom>" + "\n" +
						   "\t\t\t<x>"   + n.getX()   + "</x>"   + "\n" +
						   "\t\t\t<y>"   + n.getY()   + "</y>"   + "\n" +
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

			bw.write("\t<imagePlateau>" + "\n\t\t" + Base64.getEncoder().encodeToString(bytes) +"\n" +
					 "\t</imagePlateau>\n");

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
			this.ctrl.setNombreJoueurMinimum(Integer.parseInt(regles.getChildText("nombreJoueurMinimum")));
			this.ctrl.setNombreJoueurMaximum(Integer.parseInt(regles.getChildText("nombreJoueurMaximum")));
			this.ctrl.setNombreJoueurMiniDoubleRoute(Integer.parseInt(regles.getChildText("nombreJoueurMiniDoubleRoute")));

			List listNoeud = racine.getChild("lstNoeuds").getChildren("noeud");
			Iterator i = listNoeud.iterator();
			while(i.hasNext()){
				Element courant = (Element)i.next();
				this.lstNoeuds.add(new Noeud(courant.getChildText("nom"), Integer.parseInt(courant.getChildText("x").trim()), Integer.parseInt(courant.getChildText("y").trim())));
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
								this.lstAretes.add(new Arete(n, m, longueur, new Type(nomType)));
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
				
		}catch(Exception e){e.printStackTrace();}
		/* 

		Element imagePlateau = racine.getChild("imagePlateau");

		byte[] bytes = Base64.getDecoder().decode(imagePlateau.getText());
		System.out.println(bytes);
		try{
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			BufferedImage bImage2 = ImageIO.read(bis);
			ImageIO.write(bImage2, "png", new File("mappe.png") );
			System.out.println("Fini");
		}catch(Exception e){e.printStackTrace();}*/
	}

	public static void main(String[] args){
		Controleur ctrl = new Controleur();
		GererXML g = new GererXML(ctrl);

		g.ecrireXML();
		try{
			g.lireXML(new File("./src/data/mappe/mappe.xml"));
		}catch(Exception e){e.printStackTrace();}
	}
}