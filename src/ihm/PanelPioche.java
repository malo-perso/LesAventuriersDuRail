package src.ihm;

import javax.swing.*;

import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.List;

import src.Controleur;
import src.metier.CarteVehicule;
import src.metier.CarteObjectif;

public class PanelPioche extends JPanel {
    
    Controleur ctrl;
    List<CarteVehicule> piocheVehicule;
    List<CarteObjectif> piocheObjectif;
    CarteVehicule[] piocheVehiculeVisible;
    

    public PanelPioche(Controleur ctrl) {
        this.ctrl = ctrl;
        this.piocheVehicule = ctrl.getPioche().getLstCartesVehicule();
        this.piocheObjectif = ctrl.getPioche().getLstCartesObjectif();
        this.piocheVehiculeVisible = this.getPiocheVehiculeVisible();

        this.setLayout(new GridLayout(1, 7));

        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {
            this.add(new JLabel(this.piocheVehiculeVisible[i].getType().toString()));
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