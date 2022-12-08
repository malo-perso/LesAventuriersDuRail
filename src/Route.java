public class Route {
    Ville  ville1;
    Ville  ville2;
    int    longueur;
    Type   type;
    Joueur possession;

    public Route(Ville ville1, Ville ville2, int longueur, Type type) {
        this.ville1    = ville1;
        this.ville2    = ville2;
        this.longueur  = longueur;
        this.type      = type;
        this.possession = null;
    }

    public Ville getVille1() {
        return ville1;
    }

    public Ville getVille2() {
        return ville2;
    }

    public int getLongueur() {
        return longueur;
    }

    public Type getType() {
        return type;
    }

    public Joueur getPossession() {
        return possession;
    }

    public void setPossession(Joueur possession) {
        this.possession = possession;
    }

    
}
