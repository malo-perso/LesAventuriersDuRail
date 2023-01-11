package src.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.awt.Color;

public class Joueur implements Comparable<Joueur>{
    
    private String nom;
    private int nbWagon;
    private int nbPoint;
    private ArrayList<CarteVehicule> cartesVehicule;
    private ArrayList<CarteObjectif> cartesObjectif;
    private int RGB;

    public Joueur(String nom, int RGB, int nbWagon) {
        this.nom = nom;
        this.nbWagon = nbWagon;
        this.nbPoint = 0;
        this.cartesVehicule = new ArrayList<CarteVehicule>();
        this.cartesObjectif = new ArrayList<CarteObjectif>();
        this.RGB = RGB;        
    }

    
    /** 
     * @return String
     */
    public String getNom() {
        return this.nom;
    }

    
    /** 
     * @return int
     */
    public int getNbWagon() {
        return this.nbWagon;
    }

    
    /** 
     * @return int
     */
    public int getPoint(){
        return this.nbPoint;
    }

    
    /** 
     * @param nbPoint
     */
    public void setPoint(int nbPoint){
        this.nbPoint = nbPoint;
    }

    
    /** 
     * @param nbPoint
     */
    public void ajouterPoint(int nbPoint){
        this.nbPoint += nbPoint;
    }

    
    /** 
     * @return ArrayList<Color>
     */
    public ArrayList<Color> getListType(){
        ArrayList<Color> lstType = new ArrayList<Color>();
        for (CarteVehicule carteVehicule : this.cartesVehicule) {
            if (!lstType.contains(carteVehicule.getType().getColor()))
                lstType.add(carteVehicule.getType().getColor());
        }
        return lstType;
    }

    
    /** 
     * @return ArrayList<CarteVehicule>
     */
    public ArrayList<CarteVehicule> getCartesVehicule() {
        return this.cartesVehicule;
    }

    
    /** 
     * @return ArrayList<CarteObjectif>
     */
    public ArrayList<CarteObjectif> getCartesObjectif() {
        return this.cartesObjectif;
    }

    
    /** 
     * @param lstcarte
     */
    public void ajouterCarteVehicule(CarteVehicule lstcarte) {
        this.cartesVehicule.add(lstcarte);
    }

    
    /** 
     * @param carte
     */
    public void ajouterCarteObjectif(CarteObjectif carte) {
        this.cartesObjectif.add(carte);
    }

    
    /** 
     * @param nom
     */
    public void setNomJoueur(String nom){
        this.nom =nom;
    }

    
    /** 
     * @return Color
     */
    public Color getCouleur() {
        return new Color(this.RGB);
    }
    
    /** 
     * @param type
     * @return int
     */
    public int getNbCarteWagon(Color type)
    {   
        int nb = 0;
        HashMap<Color, ArrayList<Integer>> hashCouleur = getHashCouleur();
            if (hashCouleur.containsKey(type))
                nb= hashCouleur.get(type).size();
        return nb;
    }

    
    /** 
     * @return HashMap<Color, ArrayList<Integer>>
     */
    public HashMap<Color, ArrayList<Integer>> getHashCouleur() {
        HashMap<Color, ArrayList<Integer>> hashCouleur = new HashMap<Color, ArrayList<Integer>>();
        for (int i=0; i< this.cartesVehicule.size(); i++) {
            if (!hashCouleur.containsKey(this.cartesVehicule.get(i).getType().getColor())) {
                hashCouleur.put(this.cartesVehicule.get(i).getType().getColor(), new ArrayList<Integer>());
            }
            hashCouleur.get(this.cartesVehicule.get(i).getType().getColor()).add(i);
        }
        return hashCouleur;
    }

    
    /** 
     * @param color
     * @param nb
     * @param colorJoker
     * @return List<CarteVehicule>
     */
    public List<CarteVehicule> supprimerCarteVehicule(Color color, int nb, Color colorJoker) {
        int i=0;
        List<CarteVehicule> cartesDefausse = new ArrayList<CarteVehicule>();
        HashMap<Color, ArrayList<Integer>> hashCouleur = getHashCouleur();
        if (hashCouleur.containsKey(color)) {
            for (Integer indexe : hashCouleur.get(color)) {
                if (i<nb){
                    cartesDefausse.add(this.cartesVehicule.get((int)indexe));
                    i++;
                }   
            }
        }

        if (hashCouleur.containsKey(colorJoker)) {
            for (Integer indexe : hashCouleur.get(colorJoker)) {
                if (i<nb){
                    cartesDefausse.add(this.cartesVehicule.get((int)indexe));
                    i++;
                }  
                else 
                    break;
            }
        }
        for (CarteVehicule carteVehicule : cartesDefausse) 
            this.cartesVehicule.remove(carteVehicule);
            
        
        return cartesDefausse;
    }

    
    /** 
     * @param longueur
     */
    public void supprimerWagon(int longueur) {
        this.nbWagon -= longueur;
    }

    
    /** 
     * @param autreJoueur
     * @return int
     */
    @Override
    public int compareTo(Joueur autreJoueur) {
        return autreJoueur.getPoint() < this.getPoint() ? -1 : 1;
    }

}
