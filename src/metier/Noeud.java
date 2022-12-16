package src.metier;
<<<<<<< Updated upstream
public class Noeud{
=======

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Noeud extends Ellipse2D {
>>>>>>> Stashed changes
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

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }

    @Override
    public Rectangle2D getBounds2D() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getWidth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFrame(double arg0, double arg1, double arg2, double arg3) {
        // TODO Auto-generated method stub
        
    }
}