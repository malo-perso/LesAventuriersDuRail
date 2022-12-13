package src.metier;

import java.time.temporal.JulianFields;

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
}
