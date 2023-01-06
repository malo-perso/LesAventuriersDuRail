package src.ihm;

import javax.swing.*;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

import src.Controleur;
import src.metier.CarteVehicule;
import src.metier.CarteObjectif;
import src.ihm.FrameAfficheCarteObjectif;

public class PanelPioche extends JPanel implements ActionListener{
    
    private Controleur ctrl;
    private FrameAfficheCarteObjectif afficheCarteObjectif;
    private List<CarteVehicule> piocheVehicule;
    private List<CarteObjectif> piocheObjectif;
    private CarteVehicule[] piocheVehiculeVisible;

    private JButton[] btnPiocheVehiculeVisible;
    private JButton btnPiocheVehicule, btnPiocheObjectif;
    

    public PanelPioche(Controleur ctrl) {
        this.ctrl = ctrl;
        this.afficheCarteObjectif = new FrameAfficheCarteObjectif(this.ctrl);
        this.piocheVehicule = ctrl.getPioche().getLstCartesVehicule();
        this.piocheObjectif = ctrl.getPioche().getLstCartesObjectif();
        this.piocheVehiculeVisible = this.ctrl.getPioche().majPiocheVehiculeVisible();
        this.btnPiocheVehiculeVisible = new JButton[5];


        this.setLayout(new GridLayout(7, 1));

        // création des composants
        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {
            this.btnPiocheVehiculeVisible[i] = new JButton(i+"");
            //this.btnPiocheVehiculeVisible[i].setBackground(this.piocheVehiculeVisible[i].getType().getColor());
        }
        this.btnPiocheVehicule = new JButton("Pioche Vehicule");
        this.btnPiocheObjectif = new JButton("Pioche Objectif");

        // ajout des listeners
        for (int i = 0; i < this.btnPiocheVehiculeVisible.length; i++) {
            this.btnPiocheVehiculeVisible[i].addActionListener(this);
        }
        this.btnPiocheVehicule.addActionListener(this);
        this.btnPiocheObjectif.addActionListener(this);

        // ajout des composants
        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {
            this.add(this.btnPiocheVehiculeVisible[i]);
        }
        this.add(this.btnPiocheVehicule);
        this.add(this.btnPiocheObjectif);
    }


    public void majPiocheVehiculeVisible() {
        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {

            this.piocheVehiculeVisible[i] = this.ctrl.getPioche().majPiocheVehiculeVisible()[i];

            if (this.piocheVehiculeVisible[i] != null) {
                this.btnPiocheVehiculeVisible[i].setBackground(this.piocheVehiculeVisible[i].getType().getColor());
            } else {
                this.btnPiocheVehiculeVisible[i].setBackground(null);
                this.btnPiocheVehiculeVisible[i].setText("Y'a plus de cartes mon reuf");
            }
        }
    }

    public List<CarteObjectif> getPiochCarteObjectifs(){
        return this.piocheObjectif;
    }

    public void setBtnPiocheObjectifUtilisable(){
        this.btnPiocheObjectif.setEnabled(true);
    }

    public void setInutilisable(JButton btn) {
        btn.setEnabled(false);
    }

    public void setUtilisable(JButton btn){
        btn.setEnabled(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < this.btnPiocheVehiculeVisible.length; i++) {
            if (e.getSource() == this.btnPiocheVehiculeVisible[i]) {
                this.setInutilisable(btnPiocheObjectif);
                this.ctrl.getIHM().griserComposants();

                this.ctrl.piocherVehicule(i);
            }
        }

        if (e.getSource() == this.btnPiocheVehicule) {
            this.setInutilisable(btnPiocheObjectif);
            this.ctrl.getIHM().griserComposants();

            this.ctrl.piocherVehicule(6);
        }

        if (e.getSource() == this.btnPiocheObjectif) {
            this.ctrl.getIHM().desactiver();

            this.ctrl.getPioche().piocherObjectif();
            this.afficheCarteObjectif.setVisible(true);

            this.ctrl.getIHM().activer();
        }
    }



}