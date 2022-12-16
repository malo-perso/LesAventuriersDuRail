package src.ihm;

import src.Controleur;
import src.metier.Noeud;

import javax.swing.*;

import javafx.scene.shape.Ellipse;

import java.awt.event.*;

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

public class PanelPlateau extends JPanel
{
	private Controleur  ctrl;
	private Noeud  NoeudCourant;
	private int diametre;

	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl     = ctrl;
<<<<<<< Updated upstream

		this.hashNoeud = new HashMap<Graphics2D,Noeud>();
=======
>>>>>>> Stashed changes

		this.NoeudCourant = null;
		this.diametre = 100;

		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(800, 600));
		this.addMouseListener(new GererSouris());
		this.addMouseMotionListener(new GererMouvementSouris());
    }
<<<<<<< Updated upstream



	public void addNoeud(Graphics2D g2D, int x, int y)
	{
		g2D.setColor(Color.RED);
		g2D.fillOval(x-(this.diametre/2), y-(this.diametre/2), diametre, diametre);
		this.hashNoeud.put(g2D, new Noeud("noeud", x, y));
		majIHM();
	}

	public void removeNoeud(Graphics2D g2D)
	{
		this.hashNoeud.remove(g2D);
		majIHM();
	}
	
	public Graphics2D clickSurNoeud(int x, int y)
	{
		for (Graphics2D graphics2d : hashNoeud.keySet()) {
			int xNoeud = hashNoeud.get(graphics2d).getX();
			int yNoeud = hashNoeud.get(graphics2d).getY();
			if (xNoeud-(this.diametre/2) <= x && x <= xNoeud+(this.diametre/2) && yNoeud-(this.diametre/2) <= y && y <= yNoeud+(this.diametre/2)) {
				return graphics2d;
			}			
=======
	
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

	//ajoute un noeud au plateau
	public void ajouterNoeud(int x, int y, int nomX, int nomY) {
		this.ctrl.ajouterNoeud("nom", x, y, nomX, nomY);
		//this.ajouterNoeud((Graphics2D) this.getGraphics(), x, y, nomX, nomY);
	}

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
	public Noeud SourisSurNoeud(int x, int y){
		for (Noeud n : this.ctrl.getLstNoeuds())
		{
			int xNoeud = (int) n.getX();
			int yNoeud = (int) n.getY();
			if (xNoeud-(this.diametre/2) <= x && x <= xNoeud+(this.diametre/2) && yNoeud-(this.diametre/2) <= y && y <= yNoeud+(this.diametre/2)) {
				return n;
			}
>>>>>>> Stashed changes
		}
		return null;
	}

	public void majIHM()
	{
		super.repaint();		
	}

	//PAS ENCORE UTILISE
	/*
	//trace les aretes entre les noeuds
	public void tracerArete(Graphics2D g2D, int x, int y)
	{
		for (Arete aret : this.ctrl.getMetier().getLstAretes()) {
			if (aret.getNoeud1().getX() == x && aret.getNoeud1().getY() == y) {
				g2D.setColor(Color.RED);
				g2D.setStroke(new BasicStroke(3));
				g2D.drawLine(aret.getNoeud1().getX(), aret.getNoeud1().getY(), aret.getNoeud2().getX(), aret.getNoeud2().getY());
			}
		}	
	}
	*/
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.RED);

<<<<<<< Updated upstream
		//parcourir la liste des noeuds et les afficher les coordonnées des noeus correspondans
		for (Graphics2D graphics2d : hashNoeud.keySet()) {
			int x = hashNoeud.get(graphics2d).getX();
			int y = hashNoeud.get(graphics2d).getY();
			g.fillOval(x-(this.diametre/2), y-(this.diametre/2), this.diametre, this.diametre);
			//graphics2d.draw(new Ellipse2D.Double(hashNoeud.get(graphics2d).getX(), hashNoeud.get(graphics2d).getY(), 20, 20));
=======
		Graphics2D g1 = (Graphics2D)g;
		//parcourir la liste des noeuds et les afficher les coordonnées des noeuds correspondans
		for (Noeud noeud : this.ctrl.getLstNoeuds())
		{
			g1.draw(noeud);
			g.setColor(Color.BLACK);
			g1.fillOval((int) noeud.getX()-this.diametre/2, (int) noeud.getY()-this.diametre/2, this.diametre, this.diametre);
			g1.drawString(noeud.getNom(), noeud.getNomX(), noeud.getNomY());
>>>>>>> Stashed changes
		}

		g.setColor(Color.RED);

	}

	private class GererSouris extends MouseAdapter
	{
		public void mousePressed (MouseEvent e)	
		{
			//if clic droit, supprimer le noeud
			if (e.getButton() == MouseEvent.BUTTON3)
			{
				//vérifier si on a cliqué sur un noeud et le supprimer
				if (SourisSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = SourisSurNoeud(e.getX(), e.getY());
					PanelPlateau.this.supprimerNoeud(NoeudCourant);
					PanelPlateau.this.NoeudCourant = null;
			}
			//if click gauche, ajouter un noeud
			else if (e.getButton() == MouseEvent.BUTTON1)
			{
				//vérifier si on a cliqué sur un noeud et le sélectionner en noeud courant
				if (SourisSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = SourisSurNoeud(e.getX(), e.getY());
				else //ajouter un noeud
					PanelPlateau.this.addNoeud((Graphics2D) PanelPlateau.this.getGraphics(), e.getX(), e.getY());
			}

<<<<<<< Updated upstream
		}

=======
>>>>>>> Stashed changes
		//si le clic souris est relaché, on ne sélectionne plus de noeud
		public void mouseReleased(MouseEvent e) 
		{
			PanelPlateau.this.NoeudCourant = null;
		}

		public void mouseClicked(MouseEvent e)	
			{
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				System.out.println("double clicked");
				
				//vérifier si on a cliqué sur un noeud et le sélectionner en noeud courant
				if (SourisSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = SourisSurNoeud(e.getX(), e.getY());
			}
		}
		
	}

	private class GererMouvementSouris extends MouseMotionAdapter
	{
		public void mouseDragged(MouseEvent e) 
		{
			//bouger la position si un noeud est sélectionné
<<<<<<< Updated upstream
			if (PanelPlateau.this.NoeudCourant != null)
			{
				hashNoeud.get(PanelPlateau.this.NoeudCourant).setX(e.getX());
				hashNoeud.get(PanelPlateau.this.NoeudCourant).setY(e.getY());
=======
			if (PanelPlateau.this.NoeudCourant != null) {
				PanelPlateau.this.ctrl.setPositionNoeud(PanelPlateau.this.NoeudCourant, e.getX(), e.getY(), e.getX()+20, e.getY()+20);
>>>>>>> Stashed changes
				majIHM();
			}
		}

		public void mouseMoved(MouseEvent e) 
		{
			//si on survole un noeud avec la souris, le sélectionner
			if (SourisSurNoeud(e.getX(), e.getY())!= null)
				System.out.println("survol");

		}
	}

}