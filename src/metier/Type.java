package src.metier;
import java.util.ArrayList;
import java.awt.Color;

public class Type {
    private static ArrayList<Color> listeCouleurs = new ArrayList<Color>();
	private Color couleurActuelle;

	Type(Color couleur){
		this.couleurActuelle = ajouterCouleur(couleur);
	}

	
    /** 
     * @param c
     * @return Color
     */
    private static Color ajouterCouleur(Color c) {
        for( Color coul : listeCouleurs) {
			if( coul == c ) {
				return coul;
			}
		}
		listeCouleurs.add(c);
        return c;
    }

	
    /** 
     * @param couleur
     * @return Type
     */
    public static Type creerType (Color couleur) {
		
        return new Type(couleur);

	}

	
    /** 
     * @return Color
     */
    public Color getColor(){
		return this.couleurActuelle;
	}

	
    /** 
     * @return ArrayList<Color>
     */
    public static ArrayList<Color> getCouleurs(){
		return Type.listeCouleurs;
	}

	
    /** 
     * @return Color
     */
    public Color getCouleurActuelle(){
		return this.couleurActuelle;
	}

	
    /** 
     * @return String
     */
    public String toString(){
		return String.valueOf(this.couleurActuelle.getRGB());
	}
}