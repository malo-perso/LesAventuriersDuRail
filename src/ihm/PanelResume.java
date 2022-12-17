package src.ihm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Controleur;
import src.ihm.PanelRegleJeu;
import src.ihm.grilles.GrillesNoeudModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PanelResume extends JPanel implements ActionListener{

    //private static final String[] colNoeud = {"Nom", "x", "y","NomX","NomY"};
    private static final String[] colArrete = {"Noeud1", "Noeud2", "Longueur","Type"};
    private static final String[] colCarte = {"Noeud1", "Noeud2", "Points"};

    //private DefaultTableModel tabNoeud = new DefaultTableModel(colNoeud, 0);
    private DefaultTableModel tabArrete = new DefaultTableModel(colArrete, 0);
    private DefaultTableModel tabCarte = new DefaultTableModel(colCarte, 0);

    private GrillesNoeudModel model;
    private PanelRegleJeu panelRegleJeu;

    private JPanel panelAll;
    private JPanel panelBtn;
    private JPanel panelJoueur;
    private JPanel panelNoeud;
    private JPanel panelArrete;
    private JPanel panelObjectif;

    private JLabel lblJMin;
    private JLabel lblJMax;
    private JLabel lblDoubleMin;
    private JLabel lblVehicule;
    private JLabel lblFin;

    private JTable jTabNoeud;
    private JTable jTabArrete;
    private JTable jTabCarte;

    private JButton btnRetour;
    private JButton btnEnregistrer;

    private int nbJoueurMin;
    private int nbJoueurMax;
    private int DoubleVoie;
    private int nbWagonJoueur;
    private int nbWagonFin;

    private Controleur ctrl;

    //private JScrollBar sbTout;

    public PanelResume(Controleur ctrl){

        this.ctrl = ctrl;
        this.model = new GrillesNoeudModel(this.ctrl);

        this.panelRegleJeu = new PanelRegleJeu(this.ctrl);

        JScrollPane spTabNoeud;
        JScrollPane spTabArrete;
        JScrollPane spTabCarte;

        this.panelAll = new JPanel();
        this.panelJoueur = new JPanel();
        this.panelNoeud = new JPanel();
        this.panelArrete = new JPanel();
        this.panelObjectif = new JPanel();
        this.panelBtn = new JPanel();

        this.nbJoueurMin = this.panelRegleJeu.getNbJoueurMin();
        this.nbJoueurMax = this.panelRegleJeu.getNbJoueurMax();
        this.DoubleVoie = this.panelRegleJeu.getDoubleVoie();
        this.nbWagonJoueur = this.panelRegleJeu.getNbWagonJoueur();
        this.nbWagonFin    = this.panelRegleJeu.getNbWagonFin();

        this.lblJMin = new JLabel("Joueur Min : " + this.nbJoueurMin);
        this.lblJMax = new JLabel("Joueur Max : " + this.nbJoueurMax);
        this.lblDoubleMin = new JLabel("Joueur Min pour double voies : " + this.DoubleVoie );
        this.lblVehicule = new JLabel("Vehicule par joueur : " + this.nbWagonJoueur);
        this.lblFin = new JLabel("Fin de partie si moins de  vehicules " +  this.nbWagonFin); 

        this.jTabNoeud = new JTable(this.model);
        this.jTabArrete = new JTable(this.tabArrete);
        this.jTabCarte = new JTable(this.tabCarte);

        this.btnRetour = new JButton("Retour");
        this.btnEnregistrer = new JButton("Enregistrer");
        
        this.setLayout(new BorderLayout());
        this.panelAll.setLayout(new GridLayout(4,1));
        this.panelJoueur.setLayout(new GridLayout(5,1));
        this.panelNoeud.setLayout(new BorderLayout());
        this.panelArrete.setLayout(new BorderLayout());
        this.panelObjectif.setLayout(new BorderLayout());
        this.panelBtn.setLayout(new GridLayout(1,3));

        spTabNoeud = new JScrollPane(this.jTabNoeud);
        spTabArrete = new JScrollPane(this.jTabArrete);
        spTabCarte = new JScrollPane(this.jTabCarte);

        this.panelJoueur.add(this.lblJMin);
        this.panelJoueur.add(this.lblJMax);
        this.panelJoueur.add(this.lblDoubleMin);
        this.panelJoueur.add(this.lblVehicule);
        this.panelJoueur.add(this.lblFin);


        this.jTabNoeud.setFillsViewportHeight(true);
        this.jTabArrete.setFillsViewportHeight(true);
        this.jTabCarte.setFillsViewportHeight(true);

        this.jTabNoeud.setEnabled(false);
        this.jTabArrete.setEnabled(false);
        this.jTabCarte.setEnabled(false);

        this.panelNoeud.add(spTabNoeud,BorderLayout.CENTER);
        this.panelArrete.add(spTabArrete,BorderLayout.CENTER);
        this.panelObjectif.add(spTabCarte,BorderLayout.CENTER);

        this.panelBtn.add(this.btnRetour);
        this.panelBtn.add(new JLabel(""));
        this.panelBtn.add(this.btnEnregistrer);
        
        this.panelAll.add(this.panelJoueur);
        this.panelAll.add(this.panelNoeud);
        this.panelAll.add(this.panelArrete);
        this.panelAll.add(this.panelObjectif);
        this.add(this.panelAll);
        this.add(this.panelBtn,BorderLayout.SOUTH);
        
        this.btnRetour.addActionListener(this);
        this.btnEnregistrer.addActionListener(this);
    }

    public void actionPerformed( ActionEvent e){
        if(e.getSource() == this.btnRetour){
            this.ctrl.getIHM().changePanel("panelListeObjectif");
        }
        if(e.getSource() == this.btnEnregistrer){
            System.out.println("Enregistrer");
            this.ctrl.enregistrer();
        }
    }
}
