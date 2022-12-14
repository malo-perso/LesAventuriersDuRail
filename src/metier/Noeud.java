package src.metier;

public class Noeud {
    private String nom;
    private int x;
    private int y;

    public Noeud(String nom, int x, int y) {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    public String getNom() {
        return this.nom;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }

    public String toString(){
        return this.nom + ";" + this.x + ";" + this.y;
    }
}