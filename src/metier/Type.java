package src.metier;

import java.util.ArrayList;
import java.awt.Color;

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

     public Color getColor()
    {
        switch (this.couleurActuelle)
        {
            case "violet":
                return Color.decode("#8B00FF");
            case "rouge":
                return Color.decode("#FF0000");
            case "marron":
                return Color.decode("#8B4513");
            case "blanc":
                return Color.decode("#FFFFFF");
            case "bleu":
                return Color.decode("#0000FF");
            case "jaune":
                return Color.decode("#FFFF00");
            case "vert":
                return Color.decode("#00FF00");
            case "noir":
                return Color.decode("#000000");
            case "multiColore":
                return Color.decode("#FF00FF");
            default:
                return Color.decode("#000000");
        }
    }

	public String getCouleurActuelle(){
		return this.couleurActuelle;
	}

	public String toString(){
		return this.couleurActuelle;
	}
}
