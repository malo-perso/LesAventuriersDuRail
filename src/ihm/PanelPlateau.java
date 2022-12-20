package src.ihm;

import src.Controleur;
import src.metier.Arete;
import src.metier.Noeud;

import javax.swing.*;

import java.awt.event.*;
import java.lang.Math;

import java.awt.geom.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Image;

public class PanelPlateau extends JPanel
{
	private Controleur  ctrl;
	private Noeud  NoeudCourant;
	private int diametre;

    //PROVISOIRE
    private final int TAILLEWAGON = 50;

	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl     = ctrl;

		this.NoeudCourant = null;
		this.diametre = this.ctrl.getMetier().getDiametre();

		//this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(800, 600));
		this.addMouseListener(new GererSouris());
		this.addMouseMotionListener(new GererMouvementSouris());
    }
	
	/*
	//afficher les noeuds
    public void majTEST(ArrayList<Noeud> lNoeuds)
    {
		this.ctrl.getLstNoeuds();
        this.hashNoeud = new HashMap<Graphics2D,Noeud>();
    	for (Noeud noeud : lNoeuds)
    		this.ajouterNoeud(noeud.getX(), noeud.getY(), noeud.getNomX(), noeud.getNomY());
        majIHM();
    }*/

	//supprimer un noeud
	public void supprimerNoeud(Noeud n)
	{
		this.ctrl.supprimerNoeud(n);
		this.ctrl.majIHM();
	}

	//modifier la position d'un noeud lors d'un deplacem
	public void setPositionNoeud(int x, int y, int nomX, int nomY)
	{
		this.ctrl.setPositionNoeud(NoeudCourant,x, y, nomX, nomY);
		this.ctrl.majIHM();
	}


	//retourne le noeud correspondant au clique de la souris
	public Noeud sourisSurNoeud(int x, int y){
		for (Noeud n : this.ctrl.getLstNoeuds())
		{
			int xNoeud = (int) n.getX();
			int yNoeud = (int) n.getY();
			if (xNoeud-(this.diametre/2) <= x && x <= xNoeud+(this.diametre/2) && yNoeud-(this.diametre/2) <= y && y <= yNoeud+(this.diametre/2)) {
				return n;
			}
		}
		return null;
	}

	public void majIHM()
	{
		super.repaint();		
	}
	
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g1 = (Graphics2D) g;
		g1.setColor(Color.BLACK);

		for (Arete arete : this.ctrl.getLstAretes()) {

            // calcul de la longueur entre le noeud 1 et le noeud 2
			double longueur = Math.sqrt(Math.pow(arete.getNoeud1().getX() - arete.getNoeud2().getX(), 2)	+ Math.pow(arete.getNoeud1().getY() - arete.getNoeud2().getY(), 2));
            double distanceWagon = TAILLEWAGON * arete.getLongueur()*1.5;

			if (longueur < distanceWagon) {


                //calcule du demi petit rayon de l ellipse
                double grandR = longueur/2/1.1;
                double demiR = Math.sqrt(2*Math.pow(distanceWagon/Math.PI, 2)-Math.pow(grandR,2)*1.2);
                
                //calcule des coordonné du centre du cercle
                double centreX = (arete.getNoeud2().getX() - arete.getNoeud1().getX()) / 2 + arete.getNoeud1().getX();
                double centreY = (arete.getNoeud2().getY() - arete.getNoeud1().getY()) / 2 + arete.getNoeud1().getY();
                
                //l'angle entre la base du plan et l ellipse
                double teta = Math.atan((arete.getNoeud2().getY()-arete.getNoeud1().getY())/(arete.getNoeud2().getX()-arete.getNoeud1().getX()));

                for(int cpt = 0 ; cpt < arete.getLongueur();cpt++) {
                    //coordonnée polaire des deux points du wagon
                    double wagon1X =grandR*Math.cos((Math.PI)/arete.getLongueur()*cpt);
                    double wagon1Y =demiR*Math.sin((Math.PI)/arete.getLongueur()*cpt);
                    
                    double wagon2X =  grandR*Math.cos((Math.PI)/arete.getLongueur()*(cpt+1));
                    double wagon2Y =  demiR*Math.sin((Math.PI)/arete.getLongueur()*(cpt+1));
                    
                    //on applique la rotation et la translation
                    double w1XP = centreX +rotationX(wagon1X,wagon1Y,teta);
                    double y1YP = centreY +rotationY(wagon1X,wagon1Y,teta);
                    double x2XP = centreX +rotationX(wagon2X,wagon2Y,teta);
                    double y2YP = centreY +rotationY(wagon2X,wagon2Y,teta);

                    tracerWagon( w1XP, y1YP, x2XP, y2YP,1,g1,arete.getType().getColor());
                }
            }
            else 
				tracerWagon(arete.getNoeud1().getX(), arete.getNoeud1().getCenterY(), arete.getNoeud2().getX(), arete.getNoeud2().getY(), arete.getLongueur(), g1,arete.getType().getColor());
			
			} 
            
        for (Noeud noeud : this.ctrl.getLstNoeuds()) {
			g1.draw(noeud);
			g.setColor(Color.BLACK);
			g1.fillOval((int) noeud.getX() - this.diametre / 2, (int) noeud.getY() - this.diametre / 2, this.diametre, this.diametre);
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


    private void tracerWagon(double aX, double aY, double bX, double bY, int nbWagon, Graphics2D g1,Color color) {
		double tX1 = ((bX - aX) / nbWagon);
		double tY1 = ((bY - aY) / nbWagon);

		double hypo = (int) Math.sqrt(Math.pow(tX1, 2) + Math.pow(tY1, 2));
		double var = ((hypo - this.TAILLEWAGON) / 2) / hypo;

		double ratioX = tX1 * var;
		double ratioY = tY1 * var;

		g1.setStroke(new BasicStroke(2));
        g1.setColor(Color.BLACK);
		g1.drawLine((int) aX, (int) aY,(int) bX, (int) bY);
        
        g1.setStroke(new BasicStroke(10));
		for (int cpt = 0; cpt < nbWagon; cpt++) 
			g1.drawLine((int) (aX + cpt * tX1 + ratioX),
					(int) (aY + cpt * tY1 + ratioY),
					(int) (aX + cpt * tX1 + tX1 - ratioX),
					(int) (aY + cpt * tY1 + tY1 - ratioY));
        
        g1.setColor(color);
        g1.setStroke(new BasicStroke(8));
		for (int cpt = 0; cpt < nbWagon; cpt++) 
			g1.drawLine((int) (aX + cpt * tX1 + ratioX),
					(int) (aY + cpt * tY1 + ratioY),
					(int) (aX + cpt * tX1 + tX1 - ratioX),
					(int) (aY + cpt * tY1 + tY1 - ratioY));
        g1.setColor(Color.BLACK);
        g1.setStroke(new BasicStroke(2));  
		
	}


	private class GererSouris extends MouseAdapter
	{
		public void mousePressed (MouseEvent e)	
		{
			//if clic droit, supprimer le noeud
			if (e.getButton() == MouseEvent.BUTTON3)
			{
				//vérifier si on a cliqué sur un noeud et le supprimer
				if (sourisSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = sourisSurNoeud(e.getX(), e.getY());
					PanelPlateau.this.supprimerNoeud(NoeudCourant);
					PanelPlateau.this.NoeudCourant = null;
			}
			//if click gauche, ajouter un noeud
			else if (e.getButton() == MouseEvent.BUTTON1)
			{
				//vérifier si on a cliqué sur un noeud et le sélectionner en noeud courant
				if (sourisSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = sourisSurNoeud(e.getX(), e.getY());
				else //ajouter un noeud
                {
                    String nomVille = JOptionPane.showInputDialog("Nom de la ville");
					if(nomVille == null || nomVille.equals(""))
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nom de ville");
					else
                    	PanelPlateau.this.ctrl.ajouterNoeud(nomVille, e.getX(), e.getY(), e.getX()+20, e.getY()+20);
                }
            }
		}

		//si le clic souris est relaché, on ne sélectionne plus de noeud
		public void mouseReleased(MouseEvent e) 
		{
			PanelPlateau.this.NoeudCourant = null;
		}

		//to do
		public void mouseClicked(MouseEvent e)	
			{
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				System.out.println("double clicked");
				
				//vérifier si on a cliqué sur un noeud et le sélectionner en noeud courant
				if (sourisSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = sourisSurNoeud(e.getX(), e.getY());
			}
		}
		
	}

	private class GererMouvementSouris extends MouseMotionAdapter
	{
		public void mouseDragged(MouseEvent e) 
		{
			int largeur = e.getX();
			int largeurNom = (int)(e.getX()+(NoeudCourant.getNomX()-NoeudCourant.getX()));		
			int hauteur = e.getY();
			int hauteurNom = (int)(e.getY()+(NoeudCourant.getNomY()-NoeudCourant.getY()));
			//bouger la position si un noeud est sélectionné
			if (PanelPlateau.this.NoeudCourant != null) {
				//PanelPlateau.this.ctrl.setPositionNoeud(PanelPlateau.this.NoeudCourant, e.getX(), e.getY(), e.getX(), e.getY());
				PanelPlateau.this.NoeudCourant.setFrame( e.getX() / 30d , e.getY() / 30d, PanelPlateau.this.diametre /1d, PanelPlateau.this.diametre /1d);
				
				//Si on dépasse à droite du Panel
				if(e.getX()+(NoeudCourant.getNomX()-NoeudCourant.getX()) > PanelPlateau.this.getWidth())
				{
					largeur = PanelPlateau.this.getWidth();
					largeurNom = (int)(PanelPlateau.this.getWidth()-(NoeudCourant.getNomX()-NoeudCourant.getX()));
					//Si on dépasse à droite et en bas du Panel
					if(e.getY()+(NoeudCourant.getNomY()-NoeudCourant.getY()) > PanelPlateau.this.getHeight())
					{
						hauteur = PanelPlateau.this.getHeight();
						hauteurNom = (int)(PanelPlateau.this.getHeight()-(NoeudCourant.getNomY()-NoeudCourant.getY()));
					}
				}
				//Si on dépasse en bas du Panel
				else if(e.getY()+(NoeudCourant.getNomY()-NoeudCourant.getY()) > PanelPlateau.this.getHeight())
				{
					hauteur = PanelPlateau.this.getHeight();
					hauteurNom = (int)(PanelPlateau.this.getHeight()-(NoeudCourant.getNomY()-NoeudCourant.getY()));
					//Si on dépasse à droite et en bas du Panel
					if(e.getX()+(NoeudCourant.getNomX()-NoeudCourant.getX()) > PanelPlateau.this.getWidth())
					{
						largeur = PanelPlateau.this.getWidth();
						largeurNom = (int)(PanelPlateau.this.getWidth()-(NoeudCourant.getNomX()-NoeudCourant.getX())-20);
					}
				}
				PanelPlateau.this.ctrl.setPositionNoeud(
														PanelPlateau.this.NoeudCourant,
														largeur,
														hauteur,
														largeurNom,
														hauteurNom
													   );
				majIHM();
			}
		}

		public void mouseMoved(MouseEvent e) 
		{
			//si on survole un noeud avec la souris, le sélectionner
			//if (sourisSurNoeud(e.getX(), e.getY())!= null)
			//	System.out.println("survol");
		}
	}

	//Permet de dessiner un fond d'écran 
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Image img = getToolkit().getImage(this.ctrl.getFichierPlateau());
		
		g.drawImage(img, 0, 0, this.getWidth() , this.getHeight() , this);

	}
}