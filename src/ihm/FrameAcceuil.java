package src.ihm;

import src.Controleur;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class FrameAcceuil extends JFrame {
    
    private Controleur ctrl;

    private JPanel panelFormulaire;
    private PanelAcceuil panelAcceuil;
    private PanelJeu panelJeu;
    private PanelCreerJoueurLocal panelCreerJoueur;

    CardLayout card;

    public FrameAcceuil(Controleur ctrl) {
        this.ctrl = ctrl;
        this.panelFormulaire = new JPanel(new CardLayout());
        
        this.panelAcceuil = new PanelAcceuil(ctrl);
        this.panelJeu = new PanelJeu(ctrl);
        this.panelCreerJoueur = new PanelCreerJoueurLocal(ctrl);
    

        
        this.panelFormulaire.add(this.panelAcceuil, "panelAcceuil");
        this.panelFormulaire.add(this.panelJeu,  "panelJeu");
        this.panelFormulaire.add(this.panelCreerJoueur, "panelCreerJoueur");


        this.add(this.panelFormulaire);

        this.pack();
        this.setTitle("Accueil les aventuriers du rail");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void changePanel(){
        this.setContentPane(this.panelFormulaire);
        this.revalidate();
    }

    public void changePanel(String nomPanel){
        this.card = (CardLayout) this.panelFormulaire.getLayout();
        this.card.show(this.panelFormulaire,nomPanel);
    }

    public void MAJjoueur(){
        this.panelCreerJoueur.majTable(this.ctrl.getLstJoueurs());
    }
}