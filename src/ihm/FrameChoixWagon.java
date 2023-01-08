package src.ihm;

import src.Controleur;
import src.metier.Type;

import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class FrameChoixWagon extends JFrame implements ActionListener {

    private Controleur ctrl;
    private ArrayList<JRadioButton> lstCBTypeWagon;
    private JPanel panelHaut, panelBas;
    private JScrollPane spHaut;
    private JButton btnValider;

    public FrameChoixWagon(Controleur ctrl) {
        this.setTitle("Choix du wagon");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));

        this.ctrl = ctrl;
        this.panelHaut = new JPanel();
        this.panelBas = new JPanel();
        this.spHaut = new JScrollPane(this.panelHaut);
        this.btnValider = new JButton("Valider");



        this.btnValider.addActionListener(this);
        this.btnValider.setEnabled(false);

        this.lstCBTypeWagon = new ArrayList<JRadioButton>();
        List<src.metier.Type> lstTypeJoueur = new ArrayList<src.metier.Type>();
        for (int i = 0; i < this.ctrl.getJoueurCourant().getCartesVehicule().size(); i++) {
            lstTypeJoueur.add(this.ctrl.getJoueurCourant().getCartesVehicule().get(i).getType());
            // y'a beaucoup d'appels de méthode je verrais si c'est possible de faire plus simple
            // mais au moins y'a que les couleurs que le joueur a dans sa main sur la frame
        }

        for (int i = 0; i < lstTypeJoueur.size(); i++) {
            this.lstCBTypeWagon.add(new JRadioButton());
            this.lstCBTypeWagon.get(i).setBackground(lstTypeJoueur.get(i).getColor());
            this.lstCBTypeWagon.get(i).setOpaque(true);
            this.lstCBTypeWagon.get(i).addActionListener(this);
        }
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
                    // faire quelque chose
                    this.dispose();
                }
            }
        }
    }
}