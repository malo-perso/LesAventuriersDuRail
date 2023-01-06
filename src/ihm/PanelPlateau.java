package src.ihm;

import javax.swing.*;

import java.awt.event.*;
import java.lang.Math;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import java.awt.Image;

import java.awt.RenderingHints;
import src.Controleur;
import src.metier.Joueur;
import src.metier.Arete;
import src.metier.Noeud;

public class PanelPlateau extends JPanel {
    
    private Controleur ctrl;
	private final int HAUTEURLABEL = 15;

    PanelPlateau(Controleur ctrl) {

        this.ctrl = ctrl;

        this.setPreferredSize(new Dimension(1571,918));
    }

    public void majIHM()
	{
		super.repaint();		
	}
	
	public void paint(Graphics g) {

        int longueurWagon = this.ctrl.getMetier().getLongueurVehicule();
        double espacementWagon = this.ctrl.getMetier().getEspacementVehicule();

		super.paint(g);

		Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g1.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g1.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g1.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g1.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g1.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g1.setColor(Color.BLACK);

		for (Arete arete : this.ctrl.getMetier().getLstAretes()) {

            // calcul de la longueur entre le noeud 1 et le noeud 2
			double longueur = Math.sqrt(Math.pow(arete.getNoeud1().getX() - arete.getNoeud2().getX(), 2)	+ Math.pow(arete.getNoeud1().getY() - arete.getNoeud2().getY(), 2));
            double distanceWagon = (longueurWagon*espacementWagon) * arete.getLongueur();

            double nbArete=0;
            double indexeArete=0;
            double indiceDecalage =0 ;
            for (Arete areteD : this.ctrl.getMetier().getLstAretes()) 
                if (arete.getNoeud1().equals(areteD.getNoeud1()) && arete.getNoeud2().equals(areteD.getNoeud2()) || arete.getNoeud1().equals(areteD.getNoeud2()) && arete.getNoeud2().equals(areteD.getNoeud1()))
                    {
                        nbArete++;
                        if (arete.equals(areteD))
                            indexeArete=nbArete;
                    }
            indiceDecalage = ( -((nbArete-1)/2) + (indexeArete-1)) * longueurWagon/2 ;
            //l'angle entre le point 1 et 2 par rapport à la base du plan
            double teta = Math.atan((arete.getNoeud2().getY()-arete.getNoeud1().getY())/(arete.getNoeud2().getX()-arete.getNoeud1().getX()));

			if (longueur < distanceWagon) {

                //calcule du demi petit rayon de l ellipse
                double grandR = longueur/2;
                double demiR = Math.sqrt(Math.abs(2*Math.pow(distanceWagon/Math.PI, 2)-Math.pow(grandR,2)));
                
                //calcule des coordonné du centre du cercle
                double centreX = (arete.getNoeud2().getX() - arete.getNoeud1().getX()) / 2 + arete.getNoeud1().getX();
                double centreY = (arete.getNoeud2().getY() - arete.getNoeud1().getY()) / 2 + arete.getNoeud1().getY();
                
                for(int cpt = 0 ; cpt < arete.getLongueur();cpt++) {
                    //coordonnée polaire des deux points du wagon
                    double angleWagon1 = (Math.PI)/arete.getLongueur()*cpt;
                    double wagon1X =grandR*Math.cos(angleWagon1)+ indiceDecalage*Math.cos(angleWagon1);
                    double wagon1Y = demiR*Math.sin(angleWagon1)+ indiceDecalage*Math.cos(angleWagon1);
                    
                    double angleWagon2 = (Math.PI)/arete.getLongueur()*(cpt+1);
                    double wagon2X =  grandR*Math.cos(angleWagon2)+ indiceDecalage*Math.cos(angleWagon2);
                    double wagon2Y =  demiR*Math.sin(angleWagon2) +indiceDecalage*Math.sin(angleWagon2);
                    
                    if(arete.getOrientation())
                    {
                        wagon1Y *= -1;
                        wagon2Y *= -1;
                    }

                    //on applique la rotation et la translation
                    double w1XP = centreX +rotationX(wagon1X,wagon1Y,teta);
                    double y1YP = centreY +rotationY(wagon1X,wagon1Y,teta);
                    double x2XP = centreX +rotationX(wagon2X,wagon2Y,teta);
                    double y2YP = centreY +rotationY(wagon2X,wagon2Y,teta);

                    tracerWagon( w1XP, y1YP, x2XP, y2YP,1,g1,arete.getType().getColor(), arete.getProprietaire());
                }
            }
            else 
                {
                    if (arete.getNoeud1().getX() < arete.getNoeud2().getX())
                        teta = teta + Math.PI;
                
                    double decalageY = -indiceDecalage*Math.cos(teta);
                    double decalageX = -indiceDecalage*Math.sin(teta);
                    tracerWagon(arete.getNoeud1().getX()+decalageX, arete.getNoeud1().getCenterY()+decalageY, arete.getNoeud2().getX()+decalageX, arete.getNoeud2().getY()+decalageY, arete.getLongueur(), g1,arete.getType().getColor(), arete.getProprietaire());
                }
			} 

        
        int diametre = this.ctrl.getMetier().getDiametre();
        for (Noeud noeud : this.ctrl.getMetier().getLstNoeuds()) {
			g1.draw(noeud);
			g.setColor(Color.BLACK);
			g1.fillOval((int) noeud.getX() - diametre / 2, (int) noeud.getY() - diametre / 2, diametre, diametre);
			g.setColor(Color.WHITE);
			g1.fillRect(noeud.getNomX()-2, noeud.getNomY()-12, SwingUtilities.computeStringWidth(g.getFontMetrics(g.getFont()), noeud.getNom())+5, this.HAUTEURLABEL);
			g1.setColor(Color.BLACK);
			g1.drawString(noeud.getNom(), noeud.getNomX(), noeud.getNomY());
		}

		g.setColor(Color.RED);
    }

    private double rotationX(double nX,double nY, double teta) {
        double xM = nX;
        double yM = nY;

        double x = xM*Math.cos(teta)-yM*Math.sin(teta);
        return x;
    }
    private double rotationY(double nX,double nY, double teta) {
        double xM = nX;
        double yM = nY;

        double y = xM*Math.sin(teta)+ yM*Math.cos(teta);
        return y;
    }

    private void tracerWagon(double aX, double aY, double bX, double bY, int nbWagon, Graphics2D g1,Color color, Joueur proprietaire) {

        int hauteurWagon = this.ctrl.getMetier().getHauteurVehicule();
        int longueurWagon = this.ctrl.getMetier().getLongueurVehicule();

        double longueur = Math.sqrt(Math.pow(aX- bX, 2)	+ Math.pow(aY - bY, 2));

        //longueur des wagon cumulé
		double lWagon = nbWagon * longueurWagon;

        double ecart = (longueur - lWagon)/(nbWagon+1);
        
        double beta = Math.atan((bY-aY)/(bX-aX));

        if (bX < aX) {
            beta = beta + Math.PI;
        }

        double ecX = ecart * Math.cos(beta);
        double ecY = ecart * Math.sin(beta);
        double waX = longueurWagon * Math.cos(beta);
        double waY = longueurWagon * Math.sin(beta);

		g1.setStroke(new BasicStroke(2));
        g1.setColor(Color.BLACK);
        
        g1.setStroke(new BasicStroke(hauteurWagon));
		for (int cpt = 0; cpt < nbWagon; cpt++) 
			g1.drawLine(    (int) (aX + ecX + cpt * ( ecX + waX)),
					        (int) (aY + ecY +  cpt * ( ecY + waY)),
					        (int) (aX + (cpt+1) * ( ecX + waX)),
					        (int) (aY + (cpt+1) * ( ecY + waY)));
        
        g1.setColor(color);
        g1.setStroke(new BasicStroke(hauteurWagon-2));
		for (int cpt = 0; cpt < nbWagon; cpt++) 
			g1.drawLine(    (int) (aX + ecX + cpt * ( ecX + waX)),
					        (int) (aY + ecY +  cpt * ( ecY + waY)),
					        (int) (aX + (cpt+1) * ( ecX + waX)),
					        (int) (aY + (cpt+1) * ( ecY + waY)));

        if (proprietaire != null) {
            g1.setColor(proprietaire.getCouleur());
            g1.setStroke(new BasicStroke(hauteurWagon-5));
            for (int cpt = 0; cpt < nbWagon; cpt++) 
                g1.drawLine(    (int) (aX + ecX + cpt * ( ecX + waX)),
                                (int) (aY + ecY +  cpt * ( ecY + waY)),
                                (int) (aX + (cpt+1) * ( ecX + waX)),
                                (int) (aY + (cpt+1) * ( ecY + waY)));
        }
        g1.setColor(Color.BLACK);
        g1.setStroke(new BasicStroke(2));  
		
	}

    //Permet de dessiner un fond d'écran 
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Image img = this.ctrl.getImagePlateau();
		
		g.drawImage(img, 0, 0, 1200 , 800 , this);

	}
}