package livrable.IHM;
import livrable.Controleur;
import livrable.IHM.asuppr.PanelCreationMappe;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

public class FramePrincipale extends JFrame 
{
	private PanelAccueil panelAccueil;
	private Image        imgBackground;
	private Controleur ctrl;
	private PanelConcepteur pnlConcepteur;


	public FramePrincipale(Controleur ctrl)
	{
		this.ctrl = ctrl;
		//parametres de la fenetre
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int hauteur = (int) (tailleEcran.getHeight());
        int largeur = (int) (tailleEcran.getWidth());

		this.setSize(largeur, hauteur);
		this.setTitle("Les aventuriers du IIIe Reich");
		this.setLocation(500, 500);

		// Création des panels 
		this.panelAccueil = new PanelAccueil(this);
		this.pnlConcepteur = new PanelConcepteur(this.ctrl);

		// Positionnement des composants
		this.setContentPane(this.panelAccueil);

		// Affichage de la fenêtre
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	public void changerConcepteur()
	{
		this.setSize(1200,800);
		this.setLocation(50,50);
		this.setContentPane(this.pnlConcepteur);
		this.revalidate();
	}



	
    public void paintComponent(Graphics g)
    {
        g.drawImage(this.imgBackground,0,0,null);
    }

}

