package src.ihm;

import src.Controleur;
import src.metier.*;
import src.ihm.*;

import java.awt.Color;
import javax.swing.*;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class FramePrincipale extends JFrame implements ActionListener {

    private JPanel          panelBase;
    private PanelPioche     panelPioche;
    private PanelJoueurs    panelJoueurs;
    private PanelPlateau    panelPlateau;
    private PanelMainJoueur panelMainJoueur;

    private JButton         btnJouerLocal;

    private JMenuBar menuBarre;

    private JMenu menuAide;

    private JMenuItem menuRegles;

    private JScrollPane scrollPane;

    private File reglePDF;

    private Controleur ctrl;

    public FramePrincipale(Controleur ctrl){

        this.setTitle("Les Cocaïnomanes");
        this.setResizable(true);
        this.setUndecorated(false);

        //Creation des composants
        this.ctrl = ctrl;

        this.panelBase       = new JPanel(new BorderLayout());
        this.panelPioche     = new PanelPioche(this.ctrl);
        this.panelJoueurs    = new PanelJoueurs(this.ctrl);
        this.panelPlateau    = new PanelPlateau(this.ctrl);
        this.panelMainJoueur = new PanelMainJoueur(this.ctrl);

        this.scrollPane = new JScrollPane(this.panelPlateau);

        this.menuBarre = new JMenuBar();

        this.menuAide = new JMenu("Aide");
        this.menuRegles = new JMenuItem("Regles");

        this.menuRegles.setIcon(new ImageIcon("./src/data/images/Regles.png"));
        this.reglePDF = new File("./src/data/PDF/Regles.pdf");

        //Action listener

        this.btnJouerLocal = new JButton("Jouer en local");
        this.btnJouerLocal.addActionListener(this);
        this.menuRegles.addActionListener(this);

        //Positionnement des composants
        this.setLayout(new BorderLayout());

        this.panelBase.add(this.scrollPane, BorderLayout.CENTER);
        this.panelBase.add(this.panelJoueurs, BorderLayout.NORTH);

        this.menuAide.add(this.menuRegles);

        this.menuBarre.add(this.menuAide);

        this.setJMenuBar(this.menuBarre);

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

    public void majPioche() {
        this.panelPioche.majPiocheVehiculeVisible();
    }
    
    public void majIHM()
    {
        this.panelPlateau.majIHM();
        this.panelMainJoueur.majIHM();
    }

    public void griserComposants(){
        this.setEnabled(false);
    }

    public void activerComposants(){
        this.setEnabled(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.menuRegles){
            try{
                Desktop.getDesktop().open(reglePDF);
            }catch(Exception erreur){erreur.printStackTrace();}
        }
    }
    
}
