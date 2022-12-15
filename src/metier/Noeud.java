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

    public boolean setX(int x) {
        if ( x >= 0 && x <= 1200) {
            this.x = x;
            return true;
        }
        return false;
    }

    public boolean setY(int y) {
        if ( y >= 0 && y <= 1200) {
            this.y = y;
            return true;
        }
        return false;
    }

    public boolean setNomX(int NomX) {
        if ( NomX >= 0 && NomX <= 1200) {
            this.NomX = NomX;
            return true;
        }
        return false;
    }

    public boolean setNomY(int NomY) {
        if ( NomY >= 0 && NomY <= 1200) {
            this.NomY = NomY;
            return true;
        }
        return false;
    }

    public String toString(){
        return this.nom + ";" + this.x + ";" + this.y;
    }
}