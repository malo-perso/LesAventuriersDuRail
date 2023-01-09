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
	private JPanel panelGrilleResultat;
	private JPanel panelBase;

	public FrameFinPartie(Controleur ctrl){
		this.setTitle("Fin de partie");
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);

		this.ctrl = ctrl;
		this.panelGrilleResultat = new JPanel();
		this.panelBase = new JPanel();

		this.modelResultats = new DefaultTableModel(new Object[]{"Rang", "Nom", "Points", "Nb wagons", "Nb objectifs"}, 5);

		this.tabResultats = new JTable(this.modelResultats);
		this.tabResultats.setFillsViewportHeight(true);
		this.tabResultats.setEnabled(true);

		this.lstJoueurs = this.ctrl.getMetier().getLstJoueurs();

		this.btnQuitter = new JButton("Quitter");

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

		this.panelGrilleResultat.add(this.tabResultats.getTableHeader(), BorderLayout.NORTH);
		this.panelGrilleResultat.add(this.tabResultats, BorderLayout.CENTER);
		this.add(this.panelGrilleResultat, BorderLayout.CENTER);
		this.add(this.panelBase, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnQuitter){
			this.ctrl.dispose();
			this.dispose();
		}
		
	}
}
