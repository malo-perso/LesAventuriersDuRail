import javax.swing.*;

//import javafx.scene.layout.Border;

import java.awt.*; 
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Dimension;

public class FramePrincipale extends JFrame 
{
	private PanelAccueil panelAccueil;
	private Image        imgBackground;

	public FramePrincipale()
	{
		//parametres de la fenetre
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int hauteur = (int) (tailleEcran.getHeight()*50/100);
        int largeur = (int) (tailleEcran.getWidth()*40/100);

		this.setSize(largeur, hauteur);
		this.setTitle("Les aventuriers du IIIe Reich");
		this.setLocation(50, 50);
		this.setLayout(new BorderLayout());	

		// Création des panels 
		this.panelAccueil = new PanelAccueil();
		// Positionnement des composants
		this.add(this.panelAccueil);
		

		// Affichage de la fenêtre
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//this.pack();
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

