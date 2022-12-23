package src.ihm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Controleur;
import src.ihm.PanelRegleJeu;
import src.ihm.grilles.GrillesAreteModel;
import src.ihm.grilles.GrillesCartesObjectifsModel;
import src.ihm.grilles.GrillesNoeudModel;
import src.metier.Arete;
import src.metier.CarteObjectif;
import src.metier.Noeud;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class PanelResume extends JPanel implements ActionListener{

    //private static final String[] colNoeud = {"Nom", "x", "y","NomX","NomY"};
    //private static final String[] colArrete = {"Noeud1", "Noeud2", "Longueur","Type"};
    //private static final String[] colCarte = {"Noeud1", "Noeud2", "Points"};

    //private DefaultTableModel tabNoeud = new DefaultTableModel(colNoeud, 0);
    //private DefaultTableModel tabArrete = new DefaultTableModel(colArrete, 0);
    //private DefaultTableModel tabCarte = new DefaultTableModel(colCarte, 0);

    private GrillesNoeudModel modelN;
    private GrillesAreteModel modelA;
    private GrillesCartesObjectifsModel modelO;
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
        this.modelN = new GrillesNoeudModel(this.ctrl);
        this.modelA = new GrillesAreteModel(this.ctrl);
        this.modelO = new GrillesCartesObjectifsModel(this.ctrl);

        this.panelRegleJeu = new PanelRegleJeu(this.ctrl);

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
        this.lblVehicule = new JLabel("Vehicule par joueur : " + this.nbWagonJoueur);
        this.lblDoubleMin = new JLabel("Joueur Min pour double voies : " + this.DoubleVoie );
        this.lblFin = new JLabel("Nombres de vehicules pour finir la partie : " +  this.nbWagonFin); 

        this.jTabNoeud = new JTable(this.modelN);
        this.jTabArrete = new JTable(this.modelA);
        this.jTabCarte = new JTable(this.modelO);

        this.btnRetour = new JButton("Retour");
        this.btnEnregistrer = new JButton("Enregistrer");
        
        this.setLayout(new BorderLayout());
        this.panelAll.setLayout(new GridLayout(4,1));
        this.panelJoueur.setLayout(new GridLayout(5,1));
        this.panelNoeud.setLayout(new BorderLayout());
        this.panelArrete.setLayout(new BorderLayout());
        this.panelObjectif.setLayout(new BorderLayout());
        this.panelBtn.setLayout(new GridLayout(1,3));
         
        this.jTabNoeud.setFillsViewportHeight(true);
        this.jTabArrete.setFillsViewportHeight(true);
        this.jTabCarte.setFillsViewportHeight(true);

        this.jTabNoeud.setEnabled(false);
        this.jTabArrete.setEnabled(false);
        this.jTabCarte.setEnabled(false);
        
        JScrollPane spTabNoeud = new JScrollPane(this.jTabNoeud);
        JScrollPane spTabArrete = new JScrollPane(this.jTabArrete);
        JScrollPane spTabCarte = new JScrollPane(this.jTabCarte);

        this.panelJoueur.add(this.lblJMin);
        this.panelJoueur.add(this.lblJMax);
        this.panelJoueur.add(this.lblVehicule);
        this.panelJoueur.add(this.lblDoubleMin);
        this.panelJoueur.add(this.lblFin);

        this.panelNoeud.add(spTabNoeud);
        this.panelArrete.add(spTabArrete);
        this.panelObjectif.add(spTabCarte);

        this.panelBtn.add(this.btnRetour);
        this.panelBtn.add(new JLabel(""));
        this.panelBtn.add(this.btnEnregistrer);
        
        this.panelAll.add(this.panelJoueur);
        this.panelAll.add(this.panelNoeud);
        this.panelAll.add(this.panelArrete);
        this.panelAll.add(this.panelObjectif);
        this.add(new JLabel("Resumer de l'ensemble des parametres",SwingConstants.CENTER),BorderLayout.NORTH);
        this.add(this.panelAll,BorderLayout.CENTER);
        this.add(this.panelBtn,BorderLayout.SOUTH);
        
        this.btnRetour.addActionListener(this);
        this.btnEnregistrer.addActionListener(this);
    }
    
    public void majNoeud(ArrayList<Noeud> noeuds) {
        this.modelN.majTable(noeuds);
    }

    public void majArete(ArrayList<Arete> lstAretes) {
        this.modelA.majTable(lstAretes);
    }

    public void majCarteObjectif(ArrayList<CarteObjectif> lstCarteObjectif){
        this.modelO.majTable(lstCarteObjectif);
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
