package src.metier;

import java.time.temporal.JulianFields;
import java.util.ArrayList;

public class Type {

	private String couleurActuelle;

	public Type(String couleur){
		
		if(couleur != "violet" && couleur != "rouge" && couleur != "marron" && couleur != "blanc"
		&& couleur != "bleu" && couleur != "jaune" && couleur != "vert" && couleur != "noir" &&
		couleur != "multiColore"){
			System.out.println("Erreur : la couleur n'est pas valide");
		}
		else{
			this.couleurActuelle = couleur;
		}
	}

	public ArrayList<String> getCouleur(){
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

	public String toString(){
		return this.couleurActuelle;
	}
}
