package src.ihm;

import src.Controleur;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PanelJeu extends JPanel implements ActionListener {
    
    private Controleur ctrl;

    private JButton btnLocal;
    private JButton btnHebergerOnline;
    private JButton btnRejoindreOnline;
    private JButton btnRetour;
    
    public PanelJeu (Controleur ctrl) {
        this.ctrl = ctrl;
        this.setLayout(new GridLayout(6, 1, 25, 25));

        this.btnLocal = new JButton("Local");
        this.btnHebergerOnline = new JButton("Heberger en ligne");
        this.btnRejoindreOnline = new JButton("Rejoindre en ligne");
        this.btnRetour = new JButton("Retour");

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnLocal);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnHebergerOnline);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnRejoindreOnline);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnRetour);

        this.btnLocal.addActionListener(this);
        this.btnHebergerOnline.addActionListener(this);
        this.btnRejoindreOnline.addActionListener(this);
        this.btnRetour.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnLocal ) {
            System.out.println("Local");
        }
        else if ( e.getSource() == this.btnHebergerOnline ) {
            System.out.println("Heberger en ligne");
        }
        else if ( e.getSource() == this.btnRejoindreOnline ) {
            System.out.println("Rejoindre en ligne");
        }
        else if ( e.getSource() == this.btnRetour ) {
            System.out.println("Retour");
            this.ctrl.getIHMAcceuil().changePanel("panelAcceuil");
        }
    }
}
