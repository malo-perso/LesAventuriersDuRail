package src.ihm;

import src.Controleur;
import src.ihm.grilles.GrillesJoueurModel;
import src.ihm.renderer.ColorCellRenderer;
import src.metier.Joueur;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.util.List;

public class PanelCreerJoueurLocal extends JPanel implements ActionListener, MouseListener{

    private Controleur  ctrl;
	private JTextField  txtNomJoueur;
	private JButton     btnAjouterJoueur, btnSupprJoueur,btnCouleurJoueur,btnLancerPartie;
	private JButton     btnRetour;
	private JTable      tabJoueur;
	private JPanel 		panelBoutonHaut, panelBoutonSuivant, panelCentre;
	private Color       c;

    private GrillesJoueurModel model;
	
	public PanelCreerJoueurLocal(Controleur ctrl)  {
		this.ctrl = ctrl;

        this.setLayout(new BorderLayout(10,10));
		
		this.panelBoutonSuivant = new JPanel(new GridLayout(1,3,10,10));
		this.panelCentre        = new JPanel(new BorderLayout(10,10));
		this.panelBoutonHaut    = new JPanel(new GridLayout(2,3,10,10));

		this.txtNomJoueur 		= new JTextField ("Nom du joueur");
		this.btnAjouterJoueur 	= new JButton("Ajouter");
		this.btnSupprJoueur 	= new JButton("Suppr");
		this.btnCouleurJoueur 	= new JButton("Couleur");
		this.btnLancerPartie 	= new JButton("Lancer Partie");
		this.btnRetour 			= new JButton("Retour à l'écran d'accueil");
		

		this.model 	= new GrillesJoueurModel(this.ctrl);
		this.tabJoueur = new JTable(this.model);
		this.tabJoueur.setFillsViewportHeight(true);
		this.tabJoueur.setEnabled(true);

		this.panelBoutonSuivant.add(this.btnRetour);
		this.panelBoutonSuivant.add(new JLabel());
		this.panelBoutonSuivant.add(this.btnLancerPartie);

		this.tabJoueur.setDefaultRenderer(Color.class, new ColorCellRenderer());
        this.tabJoueur.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer());

		this.txtNomJoueur.setEditable(false);

		this.btnAjouterJoueur.addActionListener(this);
		this.btnSupprJoueur.addActionListener(this);
		this.txtNomJoueur.addMouseListener(this);
		this.btnCouleurJoueur.addActionListener(this);
		this.btnLancerPartie.addActionListener(this);
		this.btnRetour.addActionListener(this);


		this.panelBoutonHaut.add(this.txtNomJoueur);
		this.panelBoutonHaut.add(new JLabel());
		this.panelBoutonHaut.add(this.btnCouleurJoueur);
		this.panelBoutonHaut.add(this.btnAjouterJoueur);
		this.panelBoutonHaut.add(new JLabel());
		this.panelBoutonHaut.add(this.btnSupprJoueur);

		JScrollPane sp = new JScrollPane(this.tabJoueur);
		
		this.panelCentre.add(this.panelBoutonHaut, BorderLayout.NORTH);
		this.panelCentre.add(sp, BorderLayout.CENTER);

		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.panelBoutonSuivant, BorderLayout.SOUTH);
	}

	
    /** 
     * @param lstJoueur
     */
    public void majTable(List<Joueur> lstJoueur){
		this.model.majTable(lstJoueur);
		this.txtNomJoueur.setText("Nom du joueur");
		this.txtNomJoueur.setEditable(true);
		this.btnCouleurJoueur.setBackground(null);
	}

	
    /** 
     * @param e
     */
    public void actionPerformed(ActionEvent e){
		if(e.getSource() == this.btnCouleurJoueur){
			this.c = JColorChooser.showDialog(this, "Choisissez une couleur", this.btnCouleurJoueur.getBackground());
			this.btnCouleurJoueur.setBackground(c);
		}

		if (e.getSource() == this.btnAjouterJoueur ){
			if (this.c != null && !this.txtNomJoueur.getText().equals("") && 
			    !this.txtNomJoueur.getText().equals("Nom du joueur") &&
			    this.ctrl.getMetier().getLstJoueurs().size()<this.ctrl.getMetier().getNbrJoueurMaximum()){
				boolean joueurExistePas = true;

				for(Joueur j : this.ctrl.getMetier().getLstJoueurs()){
					if(j.getNom().equals(this.txtNomJoueur.getText()) || j.getCouleur().getRGB() == this.c.getRGB()) joueurExistePas=false;
				}

				if(this.txtNomJoueur.getText().length() > 14){
					JOptionPane.showMessageDialog(this, "Le nom du joueur ne doit pas dépasser 14 caractères", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else if(joueurExistePas){
					this.ctrl.getMetier().ajouterJoueur(this.txtNomJoueur.getText(), this.c.getRGB());
					this.majTable(this.ctrl.getMetier().getLstJoueurs());
					this.txtNomJoueur.setText("Nom du joueur");
					this.c = null;
				}
			}
			this.txtNomJoueur.setText("Nom du joueur");
		}
		
		if(e.getSource() == this.btnSupprJoueur){
			if (this.tabJoueur.getSelectedRow() >=0 ){
				this.ctrl.supprimerJoueur(this.ctrl.getMetier().getLstJoueurs().get(this.tabJoueur.getSelectedRow()));
			}
		}

		if(e.getSource() == this.btnRetour){
			this.ctrl.resetMetier();
			this.majTable(this.ctrl.getMetier().getLstJoueurs());
            this.txtNomJoueur.setEditable(false);

			this.ctrl.getIHMAcceuil().changePanel("panelJeu");

		}

		if(e.getSource() == this.btnLancerPartie){
			if(this.model.getRowCount() < this.ctrl.getMetier().getNbrJoueurMinimum()){
				JOptionPane.showMessageDialog(null,"Il faut plus de joueur");
			}else{
				this.ctrl.lancerPartie();
			}

		}
	}

	
    /** 
     * @param e
     */
    @Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.txtNomJoueur){	
			this.txtNomJoueur.setEditable(true);
			this.txtNomJoueur.setText("");
		}
	}

	
    /** 
     * @param arg0
     */
    @Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
    /** 
     * @param arg0
     */
    @Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
    /** 
     * @param arg0
     */
    @Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
    /** 
     * @param arg0
     */
    @Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
