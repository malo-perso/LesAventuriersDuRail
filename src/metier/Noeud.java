package src.metier;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Noeud extends Ellipse2D {
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

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getNomX() {
        return this.NomX;
    }

    public int getNomY() {
        return this.NomY;
    }

    public String toString(){
        return this.nom + ";" + this.x + ";" + this.y;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double arg0, double arg1, double arg2, double arg3) {
    }
}