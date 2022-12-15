package src.metier;

public class Noeud {
    private String nom;
    private int x;
    private int y;
    private int NomX;
    private int NomY;

    public Noeud(String nom, int x, int y, int NomX, int NomY) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.NomX = NomX;
        this.NomY = NomY;
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

    public int getNomX() {
        return this.NomX;
    }

    public int getNomY() {
        return this.NomY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNomX(int NomX) {
        this.NomX = NomX;
    }

    public void setNomY(int NomY) {
        this.NomY = NomY;
    }

    public String toString(){
        return this.nom + ";" + this.x + ";" + this.y;
    }
}