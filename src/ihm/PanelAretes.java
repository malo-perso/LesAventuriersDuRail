package src.ihm;

import src.Controleur;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class PanelAretes extends JPanel implements ActionListener {

    private Controleur ctrl;
    
    private JPanel panelTable;
    private JTable tabAretes;

    private JPanel panelValidation;
    private JButton btnRetour;
    private JButton btnSuivant;

    public PanelAretes() {

        // création des composants
        JScrollPane spTabAretes; 
        
        this.panelTable = new JPanel();
        this.tabAretes = new JTable();
        this.panelValidation = new JPanel();
        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");

        this.setLayout(new BorderLayout());
        this.panelTable.setLayout(new BorderLayout());
        this.panelValidation.setLayout(new GridLayout(1,2, 10, 10));

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
}