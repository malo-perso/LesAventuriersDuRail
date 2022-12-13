package livrable.Metier;

public class Ville{
    private String nom;
    private int x;
    private int y;

    public Ville(String nom, int x, int y) {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setx(int x) {
        this.x = y;
    }

    public void sety(int y) {
        this.y = y;
    }
}