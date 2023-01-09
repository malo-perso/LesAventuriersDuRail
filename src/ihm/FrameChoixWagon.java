package src.ihm;

import src.Controleur;
import src.metier.Arete;
import src.metier.Type;

import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;

public class FrameChoixWagon extends JFrame implements ActionListener {

    private Controleur ctrl;
    private ArrayList<JRadioButton> lstCBTypeWagon;
    private JPanel panelHaut, panelBas;
    private JScrollPane spHaut;
    private JButton btnValider;
    private ArrayList<Color> lstTypeJoueur;
    private Arete areteSelect;

    public FrameChoixWagon(Controleur ctrl, Arete areteSelect , ArrayList<Color> lstTypeJoueur) {
        this.setTitle("Choix du wagon");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.lstTypeJoueur = lstTypeJoueur;
        this.areteSelect =areteSelect;


        // création des composants
        this.ctrl = ctrl;
        this.panelHaut = new JPanel();
        this.panelBas = new JPanel();
        this.spHaut = new JScrollPane(this.panelHaut);
        this.btnValider = new JButton("Valider");


        // activation des composants
        this.btnValider.addActionListener(this);
        this.btnValider.setEnabled(false);

        this.lstCBTypeWagon = new ArrayList<JRadioButton>();
        for (int i = 0; i < lstTypeJoueur.size(); i++) {
            this.lstCBTypeWagon.add(new JRadioButton());
            this.lstCBTypeWagon.get(i).setBackground(lstTypeJoueur.get(i));
            this.lstCBTypeWagon.get(i).setOpaque(true);
            this.lstCBTypeWagon.get(i).addActionListener(this);
        }


        // ajout des composants
        for (int i = 0; i < this.lstCBTypeWagon.size(); i++) {
            this.panelHaut.add(this.lstCBTypeWagon.get(i));
        }

        this.panelBas.add(this.btnValider);

        this.add(this.spHaut);
        this.add(this.panelBas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.lstCBTypeWagon.size(); i++) {
            if (e.getSource() == this.lstCBTypeWagon.get(i)) {
                this.btnValider.setEnabled(true);
            }
        }
        
        if (e.getSource() == this.btnValider) {
            for (int i = 0; i < this.lstCBTypeWagon.size(); i++) {
                if (this.lstCBTypeWagon.get(i).isSelected()) {
                    this.ctrl.verifAreteWagon(this.areteSelect, this.lstTypeJoueur.get(i));
                    this.dispose();
                }
            }
        }
    }
}