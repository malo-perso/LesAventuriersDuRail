package src.ihm;

import javax.swing.*;

import java.awt.GridLayout;

import java.util.ArrayList;

import src.Controleur;
import src.metier.CarteVehicule;
import src.metier.CarteObjectif;

public class PanelPioche extends JPanel {
    
    Controleur ctrl;
    ArrayList<CarteVehicule> piocheVehicule;
    ArrayList<CarteObjectif> piocheObjectif;
    CarteVehicule[] piocheVehiculeVisible;
    JButton[] btnPiocheVehiculeVisible;
    

    public PanelPioche(Controleur ctrl) {
        this.ctrl = ctrl;
        this.piocheVehicule = ctrl.getLstCartesVehicule();
        this.piocheObjectif = ctrl.getLstCartesObjectif();
        this.piocheVehiculeVisible = this.getPiocheVehiculeVisible();
        this.btnPiocheVehiculeVisible = new JButton[5];

        this.setLayout(new GridLayout(7, 1));

        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {
            this.btnPiocheVehiculeVisible[i] = new JButton(i+"");
            this.btnPiocheVehiculeVisible[i].setBackground(this.piocheVehiculeVisible[i].getType().getColor());
            this.add(this.btnPiocheVehiculeVisible[i]);

        }
        this.add(new JButton("Vehicules"));
        this.add(new JButton("Objectif"));
    }

    public CarteVehicule[] getPiocheVehiculeVisible() {
        CarteVehicule[] piocheVehiculeVisible = new CarteVehicule[5];
        for (int i = 0; i < 5; i++) {
            piocheVehiculeVisible[i] = this.piocheVehicule.get(i);
        }
        return piocheVehiculeVisible;
    }

    public void majPiocheVehiculeVisible() {
        this.piocheVehiculeVisible = getPiocheVehiculeVisible();
    }



}