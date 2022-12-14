package src.ihm;

import src.Controleur;
import src.metier.Noeud;
import src.metier.Arete;
import src.metier.Type;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public PanelAretes() {

        // création des composants
        JScrollPane spTabAretes; 
        
        this.panelTable = new JPanel();
        this.panelValidation = new JPanel();
        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");

        this.setLayout(new BorderLayout());
        this.panelTable.setLayout(new BorderLayout());
        this.panelValidation.setLayout(new GridLayout(1,2, 10, 10));

        this.imgFond = getToolkit().getImage ( "../data/images/logo.png" );

        
        String[] entetes = {"Noeud 1", "Noeud 2", "Longueur", "Type"};
        this.tabAretes = new JTable(new DefaultTableModel(entetes, 0));
        spTabAretes = new JScrollPane(this.tabAretes);

        // activation des composants
        this.btnRetour.addActionListener(this);

        // ajout des composants
        this.panelTable.add(new JLabel("Carte objectif", SwingConstants.CENTER), BorderLayout.NORTH);
        this.panelTable.add(spTabAretes, BorderLayout.CENTER);

        this.panelValidation.add(this.btnRetour);
        this.panelValidation.add(this.btnSuivant);

        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnRetour) {
            System.out.println("Retour");
        }
        else if ( e.getSource() == this.btnSuivant) {
            System.out.println("Suivant");
        }
    }


    public void ajouterArete(Noeud noeud1, Noeud noeud2, int longueur, Type type) {
        
        DefaultTableModel model = (DefaultTableModel) this.tabAretes.getModel();
        model.addRow( new Object[] {noeud1, noeud2, longueur, type} );
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g.drawImage ( this.imgFond, 0, 0, this );
	}
}