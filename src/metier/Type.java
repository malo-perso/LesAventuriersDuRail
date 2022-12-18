package src.metier;

import java.util.ArrayList;

public class Type {

	private String couleurActuelle;

	private Type(String couleur){
		this.couleurActuelle = couleur;
	}

	public static Type creerType (String couleur) {
		if (!Type.getCouleurs().contains(couleur)) {
			return null;
		}
		else{
			return new Type(couleur);
		}
	}

	public static ArrayList<String> getCouleurs(){
		ArrayList<String> couleurs = new ArrayList<String>();
		couleurs.add("violet");
		couleurs.add("rouge");
		couleurs.add("marron");
		couleurs.add("blanc");
		couleurs.add("bleu");
		couleurs.add("jaune");
		couleurs.add("vert");
		couleurs.add("noir");
		couleurs.add("multiColore");
		return couleurs;
	}

	public String getCouleurActuelle(){
		return this.couleurActuelle;
	}

	public String toString(){
		return this.couleurActuelle;
	}
}
