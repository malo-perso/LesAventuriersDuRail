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

public class FrameAfficheCarteObjectif extends JFrame implements ActionListener {

    private Controleur ctrl;
    private PanelPioche panelPioche;
    private Metier metier;

    private JPanel panelBase;
    private JPanel panelObjectif;
    private JPanel panelCB;
    private JPanel panelValidation;

    private JButton btnCarte1;
    private JButton btnCarte2;
    private JButton btnCarte3;
    private JButton btnValider;

    private JCheckBox cbCarte1;
    private JCheckBox cbCarte2;
    private JCheckBox cbCarte3;

    private List<CarteObjectif> lstCarteObjectifs;

    private ImageIcon icon1;

    private Image img1;

    public FrameAfficheCarteObjectif(Controleur ctrl){
        this.ctrl = ctrl;
        this.metier = new Metier(this.ctrl);
        this.setTitle("Selection Carte Objectif");
        this.setSize(945,300);
        this.setLocation(500,200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.panelBase = new JPanel(new BorderLayout());
        this.panelObjectif = new JPanel(new GridLayout(1,3, 5, 0));
        this.panelCB = new JPanel(new GridLayout(1,3));
        this.panelValidation = new JPanel(new GridLayout(1,3));
        
        this.icon1 = new ImageIcon("./src/data/images/Europe.png");
        this.img1 = this.icon1.getImage().getScaledInstance(315,215,Image.SCALE_SMOOTH);

        this.btnCarte1 = new JButton();
        this.btnCarte2 = new JButton();
        this.btnCarte3 = new JButton();
        this.btnValider = new JButton("Valider");
        
        this.cbCarte1 = new JCheckBox();
        this.cbCarte2 = new JCheckBox();
        this.cbCarte3 = new JCheckBox();

        this.cbCarte1.setHorizontalAlignment(SwingConstants.CENTER);
        this.cbCarte2.setHorizontalAlignment(SwingConstants.CENTER);
        this.cbCarte3.setHorizontalAlignment(SwingConstants.CENTER);

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

        this.btnCarte1.setBorder(null);
        this.btnCarte2.setBorder(null);
        this.btnCarte3.setBorder(null);
        
        this.btnValider.addActionListener(this);
        this.cbCarte1.addActionListener(this);
        this.cbCarte2.addActionListener(this);
        this.cbCarte3.addActionListener(this);

        this.panelObjectif.add(this.btnCarte1);
        this.panelObjectif.add(this.btnCarte2);
        this.panelObjectif.add(this.btnCarte3);

        this.panelCB.add(this.cbCarte1,SwingConstants.CENTER);
        this.panelCB.add(this.cbCarte2, SwingConstants.CENTER);
        this.panelCB.add(this.cbCarte3,SwingConstants.CENTER);
       
        this.panelValidation.add(new JLabel());
        this.panelValidation.add(this.btnValider);
        this.panelValidation.add(new JLabel());
        
        this.panelBase.add(this.panelObjectif,BorderLayout.NORTH);
        this.panelBase.add(this.panelCB,BorderLayout.CENTER);
        this.panelBase.add(this.panelValidation,BorderLayout.SOUTH);
        this.add(panelBase);
    }
    
    public void clearCheckBox(){
        this.cbCarte1.setSelected(false);
        this.cbCarte2.setSelected(false);
        this.cbCarte3.setSelected(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<Integer> carteChoisie = new ArrayList<Integer>();
        if(e.getSource() == this.btnValider){

            if ( this.cbCarte1.isSelected()){
                carteChoisie.add(0);
            }

            if( this.cbCarte2.isSelected()){
                carteChoisie.add(1);
            }

            if( this.cbCarte3.isSelected()){
                carteChoisie.add(2);
            }

            if(carteChoisie.size() == 0){
                JOptionPane.showMessageDialog(null, "Vous devez choisir une carte");
            }else{
                this.ctrl.piocherObjectif(carteChoisie);
                this.dispose();
                clearCheckBox();
                this.ctrl.getIHM().activer();
                this.ctrl.getIHM().setVisible(true);
            }
        }
        
    }
    
}
