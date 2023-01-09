package src.ihm;

import src.Controleur;
import src.metier.Resultat;
import src.metier.Joueur;

import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FrameFinPartie extends JFrame{

	private Controleur ctrl;
	// private HashMap<Joueur, Integer> mapScore;
	// private ArrayList<Integer>  lstScore;
	private List<Joueur> lstJoueurs;
	private JButton btnContinuer;
	private JPanel panelGrilleResultat;
	private JPanel panelSp;

	public FrameFinPartie(Controleur ctrl){
		this.ctrl = ctrl;
		this.panelGrilleResultat = new JPanel();
		this.panelSp = new JPanel();
		this.setLayout(new BorderLayout());
		// this.mapScore = new HashMap<Joueur, Integer>();
		// this.lstScore = new ArrayList<Integer>();

		this.lstJoueurs = this.ctrl.getMetier().getLstJoueurs();

		Collections.sort(this.lstJoueurs);
		//Création des layout et placements des éléments
		this.panelSp.setLayout(new BorderLayout());
		this.panelGrilleResultat.setBackground(Color.WHITE);

		//A revoir, faire des tests pour savoir jusqu'à combien de jouers ça devient illisible
		if ( this.lstJoueurs.size()-1 < 10){
			this.panelGrilleResultat.setLayout(new GridLayout(1,9, 4, 0 ));

			//Remplissage du panelGrilleJoueur
			for(int i=0; i<this.lstJoueurs.size(); i++){
				this.panelGrilleResultat.add(new Resultat(
					i,
					this.lstJoueurs.get(i).getNom(),
					this.lstJoueurs.get(i).getPoint(),
					this.lstJoueurs.get(i).getNbWagon(),
					this.lstJoueurs.get(i).getCartesObjectif().size()
				));
			}
			for(int i=this.lstJoueurs.size()-1; i<5; i++){
				this.panelGrilleResultat.add(new JLabel());
			}
		}
		else{
			this.panelGrilleResultat.setLayout(new GridLayout(1,this.lstJoueurs.size()-1, 4, 0));

			//Remplissage du panelGrilleResultat
			for(int i=0; i<this.lstJoueurs.size(); i++){
				this.panelGrilleResultat.add(new Resultat(
					i,
					this.lstJoueurs.get(i).getNom(),
					this.lstJoueurs.get(i).getPoint(),
					this.lstJoueurs.get(i).getNbWagon(),
					this.lstJoueurs.get(i).getCartesObjectif().size()
				));
			}
		}

		this.panelSp.add(this.panelGrilleResultat);

		JScrollPane sp = new JScrollPane(this.panelSp);

		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(sp, BorderLayout.CENTER);

		this.btnContinuer = new JButton("Continuer");
		this.setVisible(true);
	}
}
