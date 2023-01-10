package src.ihm;

import src.Controleur;
import src.metier.Type;
import src.metier.Arete;

import javax.swing.*;

import java.awt.event.*;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class FrameChoixArete extends JFrame implements ActionListener {

    private Controleur ctrl;
    private ArrayList<JRadioButton> lstCBArete;
    private JPanel panelHaut, panelBas;
    private JButton btnValider;
    private ButtonGroup bg;
    private ArrayList<Arete> lstArete;

    public FrameChoixArete(Controleur ctrl, ArrayList<Arete> lstArete) {
        this.setTitle("Choix de l'arête");
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));

        this.lstArete =lstArete;

        // création des composants
        this.ctrl = ctrl;
        this.panelHaut = new JPanel();
        this.panelBas = new JPanel();
        this.bg = new ButtonGroup();
        this.btnValider = new JButton("Valider");


        // activation des composants
        this.btnValider.addActionListener(this);
        this.btnValider.setEnabled(false);

        this.lstCBArete = new ArrayList<JRadioButton>();
        for (int i = 0; i < lstArete.size(); i++) {
            this.lstCBArete.add(new JRadioButton());
            this.lstCBArete.get(i).setBackground(lstArete.get(i).getType().getColor());
            this.lstCBArete.get(i).setOpaque(true);
            this.lstCBArete.get(i).addActionListener(this);
            this.lstCBArete.get(i).setSize(20,20);
            this.bg.add(this.lstCBArete.get(i));
        }

        
        // ajout des composants
        for (int i = 0; i < this.lstCBArete.size(); i++) {
            this.panelHaut.add(this.lstCBArete.get(i));
        }

        this.panelBas.add(this.btnValider);

        this.add(this.panelHaut);
        this.add(this.panelBas);
    }

    
    /** 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.lstCBArete.size(); i++) {
            if (e.getSource() == this.lstCBArete.get(i)) {
                this.btnValider.setEnabled(true);
            }
        }
        
        if (e.getSource() == this.btnValider) {
            for (int i = 0; i < this.lstCBArete.size(); i++) {
                if (this.lstCBArete.get(i).isSelected()) {
                    ctrl.getMetier().verifAreteMulti(lstArete.get(i));
                    this.dispose();
                }
            }
        }
    }
}