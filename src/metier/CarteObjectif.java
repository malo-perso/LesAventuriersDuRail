package src.metier;

public class CarteObjectif {

    private Noeud noeud1;
    private Noeud noeud2;
    private int   points;
    private boolean estReussi;

    public CarteObjectif(Noeud noeud1, Noeud noeud2, int points) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.points = points;
        this.estReussi = false;
    }

    
    /** 
     * @return Noeud
     */
    public Noeud getNoeud1() {
        return this.noeud1;
    }

    
    /** 
     * @return Noeud
     */
    public Noeud getNoeud2() {
        return this.noeud2;
    }

    
    /** 
     * @return int
     */
    public int getPoints() {
        return this.points;
    }

    
    /** 
     * @return boolean
     */
    public boolean getEstReussi() {
        return this.estReussi;
    }

    
    /** 
     * @param estReussi
     */
    public void setEstReussi(boolean estReussi) {
        this.estReussi = estReussi;
    }


    
    /** 
     * @return String
     */
    public String toString(){
        return "Carte Objectif : " + this.noeud1.getNom() + " - " + this.noeud2.getNom() + " (" + this.points + " points)";
    }
}