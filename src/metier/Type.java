package src.metier;

import java.util.ArrayList;
import java.awt.Color;

public class Type {
    private static ArrayList<Color> listeCouleurs = new ArrayList();
	private Color couleurActuelle;

	private Type(Color couleur){
		this.couleurActuelle = ajouterCouleur(couleur);
	}

	private static Color ajouterCouleur(Color c) {
        for( Color coul : listeCouleurs) {
			if( coul == c ) {
				return coul;
			}
		}
		listeCouleurs.add(c);
        return c;
    }

	public static Type creerType (Color couleur) {
		
        return new Type(couleur);

	}

	public Color getColor(){
		return this.couleurActuelle;
	}

	public static ArrayList<Color> getCouleurs(){
		return listeCouleurs;
	}

	public Color getCouleurActuelle(){
		return this.couleurActuelle;
	}

	public String toString(){
		return String.valueOf(this.couleurActuelle.getRGB());
	}
}