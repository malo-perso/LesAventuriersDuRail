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

    CardLayout card;

    public FrameAcceuil(Controleur ctrl) {
        this.ctrl = ctrl;
        this.panelFormulaire = new JPanel(new CardLayout());
        
        this.panelAcceuil = new PanelAcceuil(ctrl);
        this.panelJeu = new PanelJeu(ctrl);
    

        
        this.panelFormulaire.add(this.panelAcceuil, "panelAcceuil");
        this.panelFormulaire.add(this.panelJeu,  "panelJeu");

        this.add(this.panelFormulaire);

        this.pack();
        this.setTitle("Concepteur de Plateau");
        this.setSize(500, 500);
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

    public static void main(String[] args) {
        new FrameAcceuil(null);
    }
}