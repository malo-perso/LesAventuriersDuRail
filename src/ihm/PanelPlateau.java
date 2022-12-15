package src.ihm;

import src.Controleur;
import src.metier.Noeud;

import javax.swing.*;
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
import java.awt.Image;

public class PanelPlateau extends JPanel
{
	private Controleur  ctrl;
	private Color[]     tabRouge;
	private Graphics2D  NoeudCourant;
	private HashMap<Graphics2D,Noeud> hashNoeud;
	private int diametre;

	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl     = ctrl;

		this.hashNoeud = new HashMap<Graphics2D,Noeud>();

		this.NoeudCourant = null;
		this.diametre = 100;

		//this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(800, 600));
		this.addMouseListener(new GererSouris());
		this.addMouseMotionListener(new GererMouvementSouris());
    }



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
		}
		return null;
	}

	public void majIHM()
	{
		super.repaint();		
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.RED);

		//parcourir la liste des noeuds et les afficher les coordonnées des noeus correspondans
		for (Graphics2D graphics2d : hashNoeud.keySet()) {
			int x = hashNoeud.get(graphics2d).getX();
			int y = hashNoeud.get(graphics2d).getY();
			g.fillOval(x-(this.diametre/2), y-(this.diametre/2), this.diametre, this.diametre);
			//graphics2d.draw(new Ellipse2D.Double(hashNoeud.get(graphics2d).getX(), hashNoeud.get(graphics2d).getY(), 20, 20));
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
				if (clickSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.removeNoeud(clickSurNoeud(e.getX(), e.getY()));
			}
			//if click gauche, ajouter un noeud
			else if (e.getButton() == MouseEvent.BUTTON1)
			{
				//vérifier si on a cliqué sur un noeud et le sélectionner en noeud courant
				if (clickSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = clickSurNoeud(e.getX(), e.getY());
				else //ajouter un noeud
					PanelPlateau.this.addNoeud((Graphics2D) PanelPlateau.this.getGraphics(), e.getX(), e.getY());
			}

		}

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
				if (clickSurNoeud(e.getX(), e.getY())!= null)
					PanelPlateau.this.NoeudCourant = clickSurNoeud(e.getX(), e.getY());

			}
		}
		
	}

	private class GererMouvementSouris extends MouseMotionAdapter
	{
		public void mouseDragged(MouseEvent e) 
		{
			//bouger la position si un noeud est sélectionné
			if (PanelPlateau.this.NoeudCourant != null)
			{
				hashNoeud.get(PanelPlateau.this.NoeudCourant).setX(e.getX());
				hashNoeud.get(PanelPlateau.this.NoeudCourant).setY(e.getY());
				majIHM();
			}
		}

		public void mouseMoved(MouseEvent e) 
		{
			//si on survole un noeud avec la souris, le sélectionner
			if (clickSurNoeud(e.getX(), e.getY())!= null)
				System.out.println("survol");

		}
	}
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		Image img = getToolkit().getImage(this.ctrl.getFichierPlateau());

		g.drawImage(img, 0, 0, this);

	}
}