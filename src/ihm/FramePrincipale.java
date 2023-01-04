package src.ihm;

import src.Controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.GridBagLayout;
import java.io.File;

public class FramePrincipale extends JFrame {

    private JPanel          panelBase;
    private PanelPioche     panelPioche;
    private PanelJoueurs    panelJoueurs;
    private PanelPlateau    panelPlateau;
    private PanelMainJoueur panelMainJoueur;

    private Controleur ctrl;

    public FramePrincipale(Controleur ctrl){

        this.setTitle("Concepteur de Plateau");
        this.setResizable(true);
        this.setUndecorated(false);

        //Creation des composants
        this.ctrl = ctrl;

        this.panelBase       = new JPanel(new BorderLayout());
        this.panelPioche     = new PanelPioche(this.ctrl);
        this.panelJoueurs    = new PanelJoueurs(this.ctrl);
        this.panelPlateau    = new PanelPlateau(this.ctrl);
        this.panelMainJoueur = new PanelMainJoueur(this.ctrl);

        //Action listener
        //Positionnement des composants
        this.setLayout(new BorderLayout());

        this.panelBase.add(this.panelPlateau, BorderLayout.CENTER);
        this.panelBase.add(this.panelJoueurs, BorderLayout.NORTH);

        this.add(this.panelBase, BorderLayout.CENTER);
        this.add(this.panelPioche, BorderLayout.EAST);
        this.add(this.panelMainJoueur, BorderLayout.SOUTH);

        //Parametre de la Frame

        Toolkit kit = Toolkit.getDefaultToolkit();

        Dimension tailleEcran = kit.getScreenSize();
        Insets scnMax = kit.getScreenInsets(getGraphicsConfiguration());
        int tailleBarTache = scnMax.bottom;

        this.setSize((int) tailleEcran.getWidth(), (int) tailleEcran.getHeight() - tailleBarTache);

		this.setExtendedState(this.MAXIMIZED_BOTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

    }
}
