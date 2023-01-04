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

    public Noeud getNoeud1() {
        return this.noeud1;
    }

    public Noeud getNoeud2() {
        return this.noeud2;
    }

    public int getLongueur() {
        return this.longueur;
    }

    public Type getType() {
        return this.type;
    }

    public Joueur getProprietaire() {
        return this.proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }       

    public Boolean getOrientation()
    {
        return this.orientation;
    }

    public Boolean estPrenable(Joueur joueur)
    {
        if (this.proprietaire == null)
            return true;
        else
            return false;
    }

}