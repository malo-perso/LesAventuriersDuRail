package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.Controleur;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.GridBagLayout;
import java.io.File;



public class FramePrincipale extends JFrame {

    private JPanel panelPioche;
    private JPanel panelJoueurs;
    private JPanel panelPlateau;
    private JPanel panelMainJoueur;
    
    private Controleur ctrl;

    public FramePrincipale(Controleur ctrl){


        this.ctrl = ctrl;


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
