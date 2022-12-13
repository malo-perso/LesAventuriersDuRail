package IHM;

import IHM.asuppr.PanelCreationMappe;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

public class FramePrincipale extends JFrame 
{
	private PanelCreationMappe panelAccueil;
	private Image        imgBackground;

	public FramePrincipale()
	{
		//parametres de la fenetre
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int hauteur = (int) (tailleEcran.getHeight());
        int largeur = (int) (tailleEcran.getWidth());

		this.setSize(largeur, hauteur);
		this.setTitle("Les aventuriers du IIIe Reich");
		this.setLocation(500, 500);
		this.setLayout(new FlowLayout());
		

		// Création des panels 
		this.panelAccueil = new PanelCreationMappe();
		// Positionnement des composants
		this.add(panelAccueil);

		// Affichage de la fenêtre
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	
    public void paintComponent(Graphics g)
    {
        g.drawImage(this.imgBackground,0,0,null);
    }

    public static void main(String[] args) 
    {
        new FramePrincipale();
    }
}

