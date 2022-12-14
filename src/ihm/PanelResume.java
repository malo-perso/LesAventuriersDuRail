package src.ihm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PanelResume extends JPanel implements ActionListener{

    private static final String[] colNoeud = {"Nom", "x", "y"};
    private static final String[] colArrete = {"Noeud1", "Noeud2", "taille","type"};
    private static final String[] colCarte = {"Noeud1", "Noeud2", "Points"};

    private DefaultTableModel tabNoeud = new DefaultTableModel(colNoeud, 0);
    private DefaultTableModel tabArrete = new DefaultTableModel(colArrete, 0);
    private DefaultTableModel tabCarte = new DefaultTableModel(colNoeud, 0);

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

    //private JScrollBar sbTout;

    public PanelResume(){

        JScrollPane spTabNoeud;
        JScrollPane spTabArrete;
        JScrollPane spTabCarte;

        this.panelAll = new JPanel();
        this.panelJoueur = new JPanel();
        this.panelNoeud = new JPanel();
        this.panelArrete = new JPanel();
        this.panelObjectif = new JPanel();
        this.panelBtn = new JPanel();

        this.lblJMin = new JLabel("Joueur Min : 2");
        this.lblJMax = new JLabel("Joueur Max : 5");
        this.lblDoubleMin = new JLabel("Joueur Min pour double voies : 2");
        this.lblVehicule = new JLabel("Vehicule par joueur : 45");
        this.lblFin = new JLabel("Fin de partie si moins de  vehicules 2");

        this.jTabNoeud = new JTable(this.tabNoeud);
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

        this.panelNoeud.add(spTabNoeud,BorderLayout.CENTER);
        this.panelArrete.add(spTabArrete,BorderLayout.CENTER);
        this.panelObjectif.add(spTabCarte,BorderLayout.CENTER);

        this.panelBtn.add(this.btnRetour);
        this.panelBtn.add(new JLabel(""));
        this.panelBtn.add(this.btnEnregistrer);

        this.btnEnregistrer.addActionListener(this::actionPerformed);

        
        this.panelAll.add(this.panelJoueur);
        this.panelAll.add(this.panelNoeud);
        this.panelAll.add(this.panelArrete);
        this.panelAll.add(this.panelObjectif);
        this.add(this.panelAll);
        this.add(this.panelBtn,BorderLayout.SOUTH);

    }
    public void actionPerformed( ActionEvent e){
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.add(new PanelResume());
        frame.setVisible(true);
    }
}
