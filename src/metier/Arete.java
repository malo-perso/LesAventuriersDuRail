package src.metier;

public class Arete {
    Noeud  noeud1;
    Noeud  noeud2;
    int    longueur;
    Type   type;
    Boolean orientation;
    Joueur proprietaire;

    public Arete(Noeud noeud1, Noeud noeud2, int longueur, Type type) {
        this.noeud1    = noeud1;
        this.noeud2    = noeud2;
        this.longueur  = longueur;
        this.type      = type;
        this.orientation = true;
        this.proprietaire = null;
    }

    public Arete(Noeud noeud1, Noeud noeud2, int longueur, Type type, boolean orientation) {
        this.noeud1    = noeud1;
        this.noeud2    = noeud2;
        this.longueur  = longueur;
        this.type      = type;
        this.orientation = orientation;
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
    public int getLongueur() {
        return this.longueur;
    }

    
    /** 
     * @return Type
     */
    public Type getType() {
        return this.type;
    }

    
    /** 
     * @return Joueur
     */
    public Joueur getProprietaire() {
        return this.proprietaire;
    }

    
    /** 
     * @param proprietaire
     */
    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }       

    
    /** 
     * @return Boolean
     */
    public Boolean getOrientation()
    {
        return this.orientation;
    }

    
    /** 
     * @return Boolean
     */
    public Boolean estDisponible()
    {
        if (this.proprietaire == null)
            return true;
        else
            return false;
    }
    
    
    /** 
     * @param noeud1
     * @param noeud2
     * @return boolean
     */
    public boolean estAreteDe(Noeud noeud1, Noeud noeud2) {
        if ( (noeud1.equals(this.noeud1) && noeud2.equals(this.noeud2)) || (noeud2.equals(this.noeud1) && noeud1.equals(this.noeud2)) )
            return true;
        return false;
    }

}