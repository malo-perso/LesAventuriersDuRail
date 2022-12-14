package src.ihm;

import src.Controleur;
import src.metier.Noeud;
import src.metier.Type;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class PanelAretes extends JPanel implements ActionListener {

    private Controleur ctrl;

    private Image  imgFond;
    
    private JPanel panelTable;
    private JTable tabAretes;

    private JPanel panelValidation;
    private JButton btnRetour;
    private JButton btnSuivant;

    public PanelAretes(Controleur ctrl) {

        // création des composants
        JScrollPane spTabAretes; 

        this.ctrl = ctrl;
        
        this.panelTable = new JPanel();
        this.tabAretes = new JTable();
        this.panelValidation = new JPanel();
        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");

        this.setLayout(new BorderLayout());
        this.panelTable.setLayout(new BorderLayout());
        this.panelValidation.setLayout(new GridLayout(1,2, 10, 10));

        spTabAretes = new JScrollPane(this.tabAretes);

        this.imgFond = getToolkit().getImage ( "../data/images/logo.png" );

        // activation des composants
        this.btnRetour.addActionListener(this);

        // ajout des composants
        this.panelTable.add(new JLabel("Carte objectif", SwingConstants.CENTER), BorderLayout.NORTH);
        this.panelTable.add(spTabAretes, BorderLayout.CENTER);

        this.panelValidation.add(this.btnRetour);
        this.panelValidation.add(this.btnSuivant);

        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);

        this.btnRetour.addActionListener(this);
        this.btnSuivant.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnRetour) {
            this.ctrl.changerPanel("panelRegleJeu");
        }
        if ( e.getSource() == this.btnSuivant) {
            this.ctrl.changerPanel("panelListeObjectif");
        }
    }


    public void ajouterArete(Noeud noeud1, Noeud noeud2, int longueur, Type type) {
        
    }
        

    public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g.drawImage ( this.imgFond, 0, 0, this );
	}
}