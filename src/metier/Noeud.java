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

    
    /** 
     * @return String
     */
    public String getNom() {
        return this.nom;
    }

    
    /** 
     * @return double
     */
    public double getX() {
        return this.x;
    }

    
    /** 
     * @return double
     */
    public double getY() {
        return this.y;
    }

    
    /** 
     * @return int
     */
    public int getNomX() {
        return this.NomX;
    }

    
    /** 
     * @return int
     */
    public int getNomY() {
        return this.NomY;
    }

    
    /** 
     * @return String
     */
    public String toString(){
        return this.nom + ";" + this.x + ";" + this.y;
    }

    
    /** 
     * @return Rectangle2D
     */
    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    
    /** 
     * @return double
     */
    @Override
    public double getHeight() {
        return 0;
    }

    
    /** 
     * @return double
     */
    @Override
    public double getWidth() {
        return 0;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    
    /** 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     */
    @Override
    public void setFrame(double arg0, double arg1, double arg2, double arg3) {
    }
}