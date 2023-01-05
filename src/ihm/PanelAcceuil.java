package src.ihm;

import src.Controleur;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PanelAcceuil extends JPanel implements ActionListener{
    
    private Controleur ctrl;

    private File reglePDF;

    private JButton btnJouer;
    private JButton btnRegles;
    private JButton btnQuitter;

    PanelAcceuil(Controleur ctrl){
        this.ctrl = ctrl;
        this.setLayout(new GridLayout(5, 3, 0, 50));
        //this.setLayout(new FlowLayout());
        //this.setLayout(new BoxLayout());
        //this.setLayout(new VboxLayout());

        this.btnJouer   = new JButton("Jouer");
        this.btnRegles  = new JButton("Regles");
        this.btnQuitter = new JButton("Quitter");

        this.reglePDF = new File("./src/data/PDF/Regles.pdf");
    
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnJouer);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnRegles);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnQuitter);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
    
        this.btnJouer.addActionListener(this);
        this.btnRegles.addActionListener(this);
        this.btnQuitter.addActionListener(this);
        System.out.println("PanelAcceuil");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnJouer ) {
            this.ctrl.getIHMAcceuil().changePanel("panelJeu");
        }
        if(e.getSource() == this.btnRegles){

            try{
                Desktop.getDesktop().open(reglePDF);
            }catch(Exception erreur){erreur.printStackTrace();}
        }
        else if ( e.getSource() == this.btnQuitter ) {
            System.exit(0);
        }
    }
}