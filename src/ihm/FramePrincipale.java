package src.ihm;

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

<<<<<<< Updated upstream
	
    public void paintComponent(Graphics g)
    {
        g.drawImage(this.imgBackground,0,0,null);
    }

    public static void main(String[] args) 
    {
        new FramePrincipale();
    }
=======

    }

    /*****************/
    /*     NOEUD     */
    /*****************/

    public void majNoeud()
    {
        //TO DO
    }


    public void ajouterNoeud(String nom, int x, int y, int nomX, int nomY) {
        this.panelRegleJeu.maJTable(this.ctrl.getLstNoeuds()); // modifié
        this.panelPlateau.majIHM();
    }

    public void setPositionNoeud(Noeud noeud, int x, int y, int nomX, int nomY) {
        //this.panelRegleJeu.setPositionNoeud(noeud.getNom(), x, y, nomX, nomY);
        this.panelPlateau.majIHM();
    }
    
    /*****************/
    /*     Arete     */
    /*****************/
    
    public void majArete()
    {   
        //TO DO
    }

    /*****************/
    /*    CarteObj   */
    /*****************/

    public void majCarteObjectif()
    {   
        //TO DO
    }

    //Permet de changer de panel 
    //nomPanel : nom du panel a afficher
    public void changePanel(String nomPanel){
        this.card = (CardLayout) this.panelFormulaire.getLayout();
        this.card.show(this.panelFormulaire,nomPanel);
    }


    public void majIHM() {
        this.panelPlateau.majIHM();
        this.panelPlateau.repaint(); 
        this.panelRegleJeu.maJTable(this.ctrl.getLstNoeuds());
        //this.panelPlateau.majTEST(this.ctrl.getLstNoeuds());
        this.panelListeObjectif.majTableNoeud(this.ctrl.getLstNoeuds());
    }

    
    public void actionPerformed( ActionEvent e){

        if(e.getSource() == this.menuRegles){

            try{
                System.out.println("Aide");
                Desktop.getDesktop().open(reglePDF);
            }catch(Exception erreur){erreur.printStackTrace();}
        }
		if(e.getSource() == this.menuNouveau){
			try{
				System.out.println("Nouveau");
				JFileChooser chooser = new JFileChooser(".");
				
				int res = chooser.showOpenDialog(this);
                if(res == JFileChooser.APPROVE_OPTION)
                    this.ctrl.setFichierPlateau(chooser.getSelectedFile().getPath());
			}catch(Exception erreur){erreur.printStackTrace();}
		}
        if(e.getSource() == this.menuOuvrir){
            try{
				System.out.println("Ouvrir");
				JFileChooser chooser = new JFileChooser(".");
				
				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION)
					this.ctrl.lireXML(chooser.getSelectedFile());

            }catch(Exception erreur){erreur.printStackTrace();}
        }
    }

>>>>>>> Stashed changes
}

