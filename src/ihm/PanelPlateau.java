package src.ihm;

import src.Controleur;
import src.metier.Arete;
import src.metier.Noeud;

import javax.swing.*;
import java.awt.event.*;

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
	private Color[]     tabRouge;
	private Graphics2D  NoeudCourant;
	private HashMap<Graphics2D,Noeud> hashNoeud;
	private int diametre;

	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl     = ctrl;
		//TO DO
		this.hashNoeud = new HashMap<Graphics2D,Noeud>();

		this.NoeudCourant = null;
		this.diametre = this.ctrl.getMetier().getDiametre();

		//this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(800, 600));
		this.addMouseListener(new GererSouris());
		this.addMouseMotionListener(new GererMouvementSouris());
    }


	//ajoute un noeud au plateau
	public void ajouterNoeud(int x, int y, int nomX, int nomY) {
		this.ajouterNoeud((Graphics2D) this.getGraphics(), x, y, nomX, nomY);
	}

	public void ajouterNoeud(Graphics2D g2D, int x, int y, int nomX, int nomY)
	{
		g2D.setColor(Color.RED);
		g2D.fillOval(x-(this.diametre/2), y-(this.diametre/2), diametre, diametre);
		this.hashNoeud.put(g2D, new Noeud("noeud", x, y, nomX, nomY));
		this.ctrl.majIHM();
	}


	//modifier la position d'un noeud
	public void setPositionNoeud(Noeud noeud, int x, int y, int nomX, int nomY)
	{
		for ( Graphics2D g2D : this.hashNoeud.keySet() )
			if ( this.hashNoeud.get(g2D) == noeud) {
				this.hashNoeud.get(g2D).setX(x);
				this.hashNoeud.get(g2D).setY(y);
				this.hashNoeud.get(g2D).setNomX(nomX);
				this.hashNoeud.get(g2D).setNomY(nomY);
			}

		this.ctrl.majIHM();
	}

	//PAS ENCORE UTILISE
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
	
	//supprimer un noeud
	public void removeNoeud(Graphics2D g2D)
	{
		this.hashNoeud.remove(g2D);
		this.ctrl.majIHM();
	}
	

	//retourne le noeud correspondant au clique de la souris
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

		//parcourir la liste des noeuds et les afficher les coordonnées des noeuds correspondans
		for (Graphics2D graphics2d : hashNoeud.keySet()) {
			int x = hashNoeud.get(graphics2d).getX();
			int y = hashNoeud.get(graphics2d).getY();
			g.fillOval(x-(this.diametre/2), y-(this.diametre/2), this.diametre, this.diametre);
			g.drawString(PanelPlateau.this.hashNoeud.get(graphics2d).getNom(), hashNoeud.get(graphics2d).getNomX(), hashNoeud.get(graphics2d).getNomY());
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
					PanelPlateau.this.ctrl.ajouterNoeud("Nouvelle ville", e.getX(), e.getY(), e.getX()+20, e.getY()+20);
			}

		}

		public ArrayList<Noeud> getLstNoeuds()
		{	
			ArrayList<Noeud> lstNoeuds = new ArrayList<Noeud>();
			for (Graphics2D graphics2d : hashNoeud.keySet()) {
				lstNoeuds.add(hashNoeud.get(graphics2d));
			}
			return lstNoeuds;
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
			if (PanelPlateau.this.NoeudCourant != null) {
				PanelPlateau.this.ctrl.setPositionNoeud(PanelPlateau.this.hashNoeud.get(PanelPlateau.this.NoeudCourant), e.getX(), e.getY(), e.getX()+20, e.getY()+20);
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

	//Permet de dessiner un fond d'écran 
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Image img = getToolkit().getImage(this.ctrl.getFichierPlateau());
		
		g.drawImage(img, 0, 0, this.getWidth() , this.getHeight() , this);

	}
}