package IHM;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class PanelJeu extends JPanel 
{
    private Button btnLocal;
    private Button btnHeberger;
    private Button btnRejoindre;

    public PanelJeu() 
    {
        // Options du panel
        this.setLayout(new GridLayout(3, 3));
        // Création des panels
        this.btnLocal     = new Button("Créer une mappe");
        this.btnHeberger  = new Button("Héberger");
        this.btnRejoindre = new Button("Rejoindre");
        
        // Positionnement des composants
        this.btnLocal.setSize(200, 80);
        this.btnHeberger.setSize(200, 80);
        this.btnRejoindre.setSize(200, 80);

		this.add(btnLocal);
        this.add(btnHeberger);
        this.add(btnRejoindre);

    }
}