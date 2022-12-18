package src.metier;

public class Arete {
    Noeud  noeud1;
    Noeud  noeud2;
    int    longueur;
    Type   type;

    public Arete(Noeud noeud1, Noeud noeud2, int longueur, Type type) {
        this.noeud1    = noeud1;
        this.noeud2    = noeud2;
        this.longueur  = longueur;
        this.type      = type;
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

    public boolean setLongueur(int longueur) {
        if (longueur < 0)
            return false;
        this.longueur = longueur;
        return true;
    }

    public boolean setType(Type type) {
        if (type == null)
            return false;
        this.type = type;
        return true;
    }
}
