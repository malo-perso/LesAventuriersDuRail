package src.ihm;

import src.Controleur;
import src.ihm.grilles.GrillesJoueurModel;
import src.ihm.grilles.GrillesResultatsModel;
import src.metier.Resultat;
import src.metier.CarteObjectif;
import src.metier.Joueur;

import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.*;

public class FrameFinPartie extends JFrame implements ActionListener{

	private Controleur ctrl;
	private GrillesResultatsModel modelResultats;
	private JTable tabResultats;
	private List<Joueur> lstJoueurs;
	private JButton btnQuitter;
	private JPanel panelGrilleResultat;
	private JPanel panelSp;
	private JPanel panelBase;

	public FrameFinPartie(Controleur ctrl){
		this.setTitle("Fin de partie");
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);

		this.ctrl = ctrl;
		this.panelGrilleResultat = new JPanel(new GridLayout(1,3));
		this.panelSp = new JPanel();
		this.panelBase = new JPanel();

		this.modelResultats = new GrillesResultatsModel(this.ctrl);
		this.tabResultats = new JTable(this.modelResultats);

		this.lstJoueurs = this.ctrl.getMetier().getLstJoueurs();

		this.btnQuitter = new JButton("Quitter");

		Collections.sort(this.lstJoueurs);
		//Création des layout et placements des éléments
		this.panelSp.setLayout(new BorderLayout());
		this.panelGrilleResultat.setBackground(Color.WHITE);

		//Remplissage du panelGrilleJoueur
		for(int i=0; i<this.lstJoueurs.size(); i++){
			this.modelResultats.setValueAt(i,i,0);
			this.modelResultats.setValueAt(this.lstJoueurs.get(i).getNom(),i,1);
			this.modelResultats.setValueAt(this.lstJoueurs.get(i).getPoint(),i,2);
			this.modelResultats.setValueAt(this.lstJoueurs.get(i).getNbWagon(),i,3);
			int nbObj = 0;
			for(CarteObjectif co : this.lstJoueurs.get(i).getCartesObjectif())
				if(co.getEstReussi())
					nbObj++;
			this.modelResultats.setValueAt(nbObj,i,4);
		}

		
		this.panelBase.setLayout(new GridLayout(1,5));
		this.panelBase.add(new JLabel());
		this.panelBase.add(new JLabel());
		this.panelBase.add(this.btnQuitter);
		this.panelBase.add(new JLabel());
		this.panelBase.add(new JLabel());

		this.btnQuitter.addActionListener(this);

		this.panelGrilleResultat.add(new JLabel());
		this.panelGrilleResultat.add(this.tabResultats);
		this.panelGrilleResultat.add(new JLabel());


		this.panelSp.add(this.panelGrilleResultat);


		JScrollPane sp = new JScrollPane(this.panelSp);

		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(sp, BorderLayout.CENTER);
		this.add(this.panelBase, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnQuitter){
			this.dispose();
		}
		
	}
}
