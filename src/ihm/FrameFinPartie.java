package src.ihm;

import src.Controleur;
import src.ihm.renderer.ColorCellRendererResultat;
import src.metier.CarteObjectif;
import src.metier.Joueur;

import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrameFinPartie extends JFrame implements ActionListener{

	private Controleur ctrl;
	private DefaultTableModel modelResultats;
	private JTable tabResultats;
	private List<Joueur> lstJoueurs;
	private JButton btnQuitter;
	private JPanel panelEnTete;
	private JPanel panelGrilleResultat;
	private JPanel panelBase;

	private String nomGagnant;

	public FrameFinPartie(Controleur ctrl, String nomGagnant){
		this.setTitle("Fin de partie");
		this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,500);
		this.setResizable(false);


		this.ctrl = ctrl;
		this.panelEnTete = new JPanel();
		this.panelGrilleResultat = new JPanel(new BorderLayout());
		this.panelBase = new JPanel();
		
		this.lstJoueurs = this.ctrl.getMetier().getLstJoueurs();

		System.out.println("Taille lstJoueurs : " + this.lstJoueurs.size());

		this.modelResultats = new DefaultTableModel(new Object[]{"Rang", "Pseudo", "Couleur","Score", "Nb Wagons", "Objectifs ✓"},0){
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};

		this.tabResultats = new JTable(this.modelResultats);
		this.tabResultats.setFillsViewportHeight(true);
		this.tabResultats.setEnabled(true);

		this.btnQuitter = new JButton("Quitter");

		if(nomGagnant != null)
			this.nomGagnant = nomGagnant + " ";
		else
			this.nomGagnant = "Personne n'";

		Collections.sort(this.lstJoueurs);
		//Création des layout et placements des éléments
		this.panelGrilleResultat.setBackground(Color.WHITE);



		//Remplissage du panelGrilleJoueur
		for(int i=0; i<this.lstJoueurs.size(); i++){
		
			int nbObj = 0;
			for(CarteObjectif co : this.lstJoueurs.get(i).getCartesObjectif())
				if(co.getEstReussi())
					nbObj++;
			
			this.modelResultats.insertRow(i, new Object[]{i+1,
														  this.lstJoueurs.get(i).getNom(),
														  this.lstJoueurs.get(i).getCouleur(),
														  this.lstJoueurs.get(i).getPoint(),
														  this.lstJoueurs.get(i).getNbWagon(),
														  nbObj});
		}
		this.tabResultats = new JTable(this.modelResultats);

		this.tabResultats.getColumnModel().getColumn(2).setCellRenderer(new ColorCellRendererResultat());

		// Ajout des composants
		this.panelBase.setLayout(new GridLayout(1,5));
		this.panelBase.add(new JLabel());
		this.panelBase.add(new JLabel());
		this.panelBase.add(this.btnQuitter);
		this.panelBase.add(new JLabel());
		this.panelBase.add(new JLabel());

		this.btnQuitter.addActionListener(this);

		this.panelEnTete.add(new JLabel("Le gagnant est "+ this.lstJoueurs.get(0).getNom(), SwingConstants.CENTER), BorderLayout.CENTER);
		this.panelGrilleResultat.add(this.tabResultats.getTableHeader(), BorderLayout.NORTH);
		this.panelGrilleResultat.add(this.tabResultats, BorderLayout.CENTER);
		this.panelGrilleResultat.add(new JLabel(this.nomGagnant + "a le chemin le plus long", SwingConstants.CENTER), BorderLayout.SOUTH);
		this.add(this.panelEnTete, BorderLayout.NORTH);
		this.add(this.panelGrilleResultat, BorderLayout.CENTER);
		this.add(this.panelBase, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	
    /** 
     * @param e
     */
    @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnQuitter){
			this.ctrl.dispose();
			this.dispose();
		}
		
	}
}
