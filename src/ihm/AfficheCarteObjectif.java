package src.ihm;

import src.Controleur;
import src.ihm.PanelPioche;
import src.metier.Metier;
import src.metier.CarteObjectif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AfficheCarteObjectif extends JFrame implements ActionListener {

    private Controleur ctrl;
    private PanelPioche panelPioche;
    private Metier metier;

    private JPanel panelObjectif;

    private JButton btnCarte1;
    private JButton btnCarte2;
    private JButton btnCarte3;

    private List<CarteObjectif> lstCarteObjectifs;

    private ImageIcon icon1;

    private Image img1;

    public AfficheCarteObjectif(Controleur ctrl){
        this.ctrl = ctrl;
        this.metier = new Metier(this.ctrl);
        this.setTitle("Selection Carte Objectif");
        this.setSize(945,250);
        this.setLocation(500,200);
        this.setResizable(false);

        this.panelObjectif = new JPanel(new GridLayout(1,3));

        System.out.println(this.metier.getImage());
        
        this.icon1 = new ImageIcon("./src/data/images/Europe.png");
        this.img1 = this.icon1.getImage().getScaledInstance(315,215,Image.SCALE_SMOOTH);

        this.btnCarte1 = new JButton();
        this.btnCarte2 = new JButton();
        this.btnCarte3 = new JButton();

        ArrayList<CarteObjectif> lstCartePioche = new ArrayList<CarteObjectif>(this.ctrl.getPioche().piocherObjectif());
        this.btnCarte1.setText(lstCartePioche.get(0).getNoeud1().getNom() + " " +  lstCartePioche.get(0).getNoeud2().getNom() + " " + lstCartePioche.get(0).getPoints());
        this.btnCarte2.setText(lstCartePioche.get(1).getNoeud1().getNom() + " " +  lstCartePioche.get(1).getNoeud2().getNom() + " " + lstCartePioche.get(1).getPoints());
        this.btnCarte3.setText(lstCartePioche.get(2).getNoeud1().getNom() + " " +  lstCartePioche.get(2).getNoeud2().getNom() + " " + lstCartePioche.get(2).getPoints());


        this.btnCarte1.setIcon(new ImageIcon(img1));
        this.btnCarte2.setIcon(new ImageIcon(img1));
        this.btnCarte3.setIcon(new ImageIcon(img1));

        this.btnCarte1.setVerticalTextPosition(SwingConstants.CENTER);
        this.btnCarte1.setHorizontalTextPosition(SwingConstants.CENTER);
        this.btnCarte2.setVerticalTextPosition(SwingConstants.CENTER);
        this.btnCarte2.setHorizontalTextPosition(SwingConstants.CENTER);
        this.btnCarte3.setVerticalTextPosition(SwingConstants.CENTER);
        this.btnCarte3.setHorizontalTextPosition(SwingConstants.CENTER);
        //this.btnCarte3.setBackground(new Color(new ImageIcon(img1)));
        
        this.btnCarte1.addActionListener(this);
        this.btnCarte2.addActionListener(this);
        this.btnCarte3.addActionListener(this);

        this.panelObjectif.add(this.btnCarte1);
        this.panelObjectif.add(this.btnCarte2);
        this.panelObjectif.add(this.btnCarte3);
        

        this.add(panelObjectif);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == this.btnCarte1){

        }
        if(e.getSource() == this.btnCarte2){
            
        }
        if(e.getSource() == this.btnCarte3){
            
        }
        
    }
    
}
