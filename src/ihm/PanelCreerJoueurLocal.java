package src.ihm;

import src.Controleur;
import src.ihm.grilles.GrillesJoueurModel;
import src.ihm.renderer.ColorCellRenderer;
import src.metier.Joueur;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.util.List;

public class PanelCreerJoueurLocal extends JPanel implements ActionListener{

    private Controleur  ctrl;
	private JTextField  txtNomPartie, txtNomJoueur;
	private JButton     btnAjouterJoueur, btnSupprJoueur,btnCouleurJoueur,btnLancerPartie;
	private JButton     btnRetour;
	private JTable      tabJoueur;
	private JPanel 		panelTitreJeu, panelBoutonHaut, panelBoutonSuivant, panelCentre;
	private Color       c;

    private GrillesJoueurModel model;
	
	public PanelCreerJoueurLocal(Controleur ctrl)  {
		this.ctrl = ctrl;
        this.setLayout(new BorderLayout(10,10));
		
		this.panelTitreJeu	  	= new JPanel(new GridLayout(1,3,10,10));
		this.panelBoutonSuivant = new JPanel(new GridLayout(1,3,10,10));
		this.panelCentre        = new JPanel(new BorderLayout(10,10));
		this.panelBoutonHaut    = new JPanel(new GridLayout(2,3,10,10));

        this.txtNomPartie  		= new JTextField ("Nom de la partie");
		this.txtNomJoueur 		= new JTextField ();
		this.btnAjouterJoueur 	= new JButton("Ajouter");
		this.btnSupprJoueur 	= new JButton("Suppr");
		this.btnCouleurJoueur 	= new JButton("Couleur");
		this.btnLancerPartie 	= new JButton("Lancer Partie");
		this.btnRetour 			= new JButton("Retour à l'écran d'accueil");
		

		this.model 	= new GrillesJoueurModel(this.ctrl);
		this.tabJoueur = new JTable(this.model);
		this.tabJoueur.setFillsViewportHeight(true);
		this.tabJoueur.setEnabled(true);
		this.txtNomPartie.setHorizontalAlignment(SwingConstants.CENTER);

		this.panelBoutonSuivant.add(this.btnRetour);
		this.panelBoutonSuivant.add(new JLabel());
		this.panelBoutonSuivant.add(this.btnLancerPartie);

		this.tabJoueur.setDefaultRenderer(Color.class, new ColorCellRenderer());
        this.tabJoueur.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer());

		this.btnAjouterJoueur.addActionListener(this);
		this.btnSupprJoueur.addActionListener(this);
		this.txtNomJoueur.addActionListener(this);
		this.btnCouleurJoueur.addActionListener(this);
		this.btnLancerPartie.addActionListener(this);
		this.btnRetour.addActionListener(this);


		this.panelBoutonHaut.add(this.txtNomJoueur);
		this.panelBoutonHaut.add(new JLabel());
		this.panelBoutonHaut.add(this.btnCouleurJoueur);
		this.panelBoutonHaut.add(this.btnAjouterJoueur);
		this.panelBoutonHaut.add(new JLabel());
		this.panelBoutonHaut.add(this.btnSupprJoueur);
		
		this.panelCentre.add(this.panelBoutonHaut, BorderLayout.NORTH);
		this.panelCentre.add(this.tabJoueur, BorderLayout.CENTER);

		this.add(this.txtNomPartie, BorderLayout.NORTH);
		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.panelBoutonSuivant, BorderLayout.SOUTH);
	}

	public void majTable(List<Joueur> lstJoueur){
		this.model.majTable(lstJoueur);
		this.txtNomJoueur.setText("");
		this.btnCouleurJoueur.setBackground(null);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btnCouleurJoueur){
			this.c = JColorChooser.showDialog(this, "Choisissez une couleur", this.btnCouleurJoueur.getBackground());
			this.btnCouleurJoueur.setBackground(c);
		}

		if (e.getSource() == this.btnAjouterJoueur ){
			if (this.c != null && this.txtNomJoueur.getText() != ""){
				this.ctrl.getMetier().ajouterJoueur(this.txtNomJoueur.getText(), this.c.getRGB());
				this.majTable(this.ctrl.getLstJoueurs());
			}
		}
		
		if(e.getSource() == this.btnSupprJoueur){
			this.ctrl.supprimerJoueur(this.ctrl.getLstJoueurs().get(this.tabJoueur.getSelectedRow()));
		}

		if(e.getSource() == this.btnRetour){
			this.ctrl.getIHMAcceuil().changePanel("panelJeu");
		}

		if(e.getSource() == this.btnLancerPartie){
			if(this.model.getRowCount() < this.ctrl.getMetier().getNbrJoueurMinimum()){
				JOptionPane.showMessageDialog(null,"Il faut plus de joueur");
			}
		}
	}

}
