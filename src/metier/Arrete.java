package src.metier;
public class Arrete {
    Noeud  Noeud1;
    Noeud  Noeud2;
    int    longueur;
    Type   type;
    Joueur possession;

    public Arrete(Noeud noeud1, Noeud noeud2, int longueur, Type type) {
        this.noeud1    = noeud1;
        this.noeud2    = noeud2;
        this.longueur  = longueur;
        this.type      = type;
        this.possession = null;
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

    public Joueur getPossession() {
        return this.possession;
    }

    public void setPossession(Joueur possession) {
        this.possession = possession;
    }

    
}
