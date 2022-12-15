package src.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Controleur;
import src.metier.fonctionAux;
import src.metier.Noeud;

import java.util.ArrayList;


public class PanelRegleJeu extends JPanel implements ActionListener{
    
    private static final String[] NOM_COLONNE = {"Nom", "X", "Y"};
    private DefaultTableModel model; 

    private JTable jTabNoeud;
    
    private JPanel panelRegle,panelValidation,panelNoeud,panelNoeudBtn,panelRemplissage,panelTable;
    
    private JButton btnSuivant,btnAjoutNoeud,btnSupprNoeud,btnClear;
    
    private JTextField txtNbJoueurMin,txtNbJoueurMax,txtDoubleVoie,txtNbWagonFin,txtNbWagonJoueur,txtNomNoeud,txtX,txtY;
    
    private int nbJoueurMin;
    private int nbJoueurMax;
    private int doubleVoie; 
    private int nbWagonJoueur;
    private int nbWagonFin;

    private Controleur ctrl;

    public PanelRegleJeu(Controleur ctrl)
    {
        //Creation des composants
        this.ctrl = ctrl;
        
        this.nbJoueurMax = 5;
        this.nbJoueurMin = 2;
        this.doubleVoie = 3;
        this.nbWagonJoueur = 45;
        this.nbWagonFin = 2;
        
        this.model = new DefaultTableModel(NOM_COLONNE, 0);
        this.jTabNoeud = new JTable(this.model);
        this.jTabNoeud.setFillsViewportHeight(true);
        this.jTabNoeud.setEnabled(false);

        JScrollPane spTabNoeud = new JScrollPane(this.jTabNoeud);

        this.btnClear = new JButton("Effacer");
        this.btnAjoutNoeud = new JButton("Ajouter +");
        this.btnSupprNoeud = new JButton("Supprimer -");
        this.btnSuivant = new JButton("Suivant");
        
        this.txtNbJoueurMin = new JTextField(this.nbJoueurMin+" ");
        this.txtNbJoueurMax = new JTextField(this.nbJoueurMax+" ");
        this.txtNbWagonJoueur = new JTextField(this.nbWagonJoueur+" ");
        this.txtDoubleVoie = new JTextField(this.doubleVoie+" ");
        this.txtNbWagonFin = new JTextField(this.nbWagonFin+" ");
        this.txtNomNoeud = new JTextField("Nom de la ville");
        this.txtX = new JTextField("X");
        this.txtY = new JTextField("Y");
        
        //Creation des layout
        this.setLayout(new BorderLayout());
        this.panelRegle = new JPanel(new GridLayout(6,2, 0, 3));
        this.panelTable = new JPanel(new BorderLayout());
        this.panelNoeud = new JPanel(new BorderLayout());
        this.panelNoeudBtn = new JPanel(new GridLayout(2,2));
        this.panelValidation = new JPanel(new BorderLayout());
        this.panelRemplissage = new JPanel(new GridLayout(4,3));
        
        //Action listener
        this.txtNbJoueurMin.addActionListener(this);
        this.txtNbJoueurMax.addActionListener(this);
        this.txtDoubleVoie.addActionListener(this);
        this.txtNbWagonJoueur.addActionListener(this);
        this.txtNbWagonFin.addActionListener(this);
        this.btnAjoutNoeud.addActionListener(this);
        this.btnSupprNoeud.addActionListener(this);
        this.btnClear.addActionListener(this);
        this.btnSuivant.addActionListener(this);

        //Positionnement des composants
        this.panelRegle.add(new JLabel("Joueur minimum :", SwingConstants.RIGHT));
        this.panelRegle.add(this.txtNbJoueurMin);
        this.panelRegle.add(new JLabel("Joueur maximum :", SwingConstants.RIGHT));
        this.panelRegle.add(this.txtNbJoueurMax);
        this.panelRegle.add(new JLabel("Nombre de wagon par joueur :", SwingConstants.RIGHT));
        this.panelRegle.add(this.txtNbWagonJoueur);
        this.panelRegle.add(new JLabel("Joueur minimum pour les doubles voies :", SwingConstants.RIGHT));
        this.panelRegle.add(this.txtDoubleVoie);        
        this.panelRegle.add(new JLabel("Nombre de wagon pour finir la partie :", SwingConstants.RIGHT));
        this.panelRegle.add(this.txtNbWagonFin);
        this.panelRegle.add(new JLabel());

        this.panelRemplissage.add(new JLabel("Nom :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("X :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Y :", SwingConstants.CENTER));
        this.panelRemplissage.add(this.txtNomNoeud);
        this.panelRemplissage.add(this.txtX);
        this.panelRemplissage.add(this.txtY);
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(this.btnClear);
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());

        this.panelNoeud.add(this.panelRegle, BorderLayout.NORTH);
        this.panelNoeud.add(this.panelRemplissage, BorderLayout.CENTER);

        this.panelNoeudBtn.add(this.btnAjoutNoeud);
        this.panelNoeudBtn.add(this.btnSupprNoeud);
        this.panelNoeudBtn.add(new JLabel());
        
        this.panelTable.add(this.panelNoeud, BorderLayout.NORTH);
        this.panelTable.add(spTabNoeud, BorderLayout.CENTER);
        this.panelTable.add(this.panelNoeudBtn, BorderLayout.SOUTH);
        
        this.panelValidation.add(this.btnSuivant, BorderLayout.EAST);
        
        this.add(new JLabel("Paramètre du plateau", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);
    }

    //Getter
    public int getNbJoueurMin() {return this.nbJoueurMin;}
    public int getNbJoueurMax() {return this.nbJoueurMax;}
    public int getDoubleVoie() {return this.doubleVoie;}
    public int getNbWagonJoueur() {return this.nbWagonJoueur;}
    public int getNbWagonFin() {return this.nbWagonFin;}

    public ArrayList<Noeud> getNoeud() {
        ArrayList<Noeud> noeud = new ArrayList<Noeud>();
        for (int i = 0; i < this.model.getRowCount(); i++) {
            noeud.add(new Noeud((String) this.model.getValueAt(i, 0), (int) this.model.getValueAt(i, 1), (int) this.model.getValueAt(i, 2)));
        }
        return noeud;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.txtNbJoueurMin) {
            if(!fonctionAux.isInteger(this.txtNbJoueurMin.getText()))
                this.txtNbJoueurMin.setText(" ");
            this.nbJoueurMin = Integer.parseInt(this.txtNbJoueurMin.getText());
        }
        if (e.getSource() == this.txtNbJoueurMax) {
            if(!fonctionAux.isInteger(this.txtNbJoueurMax.getText()))
                    this.txtNbJoueurMax.setText(" ");
            this.nbJoueurMax = Integer.parseInt(this.txtNbJoueurMax.getText());
        }
        if (e.getSource() == this.txtDoubleVoie) {
            if(!fonctionAux.isInteger(this.txtDoubleVoie.getText()))
                    this.txtDoubleVoie.setText(" ");
            this.doubleVoie = Integer.parseInt(this.txtDoubleVoie.getText());
        }
        if (e.getSource() == this.txtNbWagonJoueur) {
            if(!fonctionAux.isInteger(this.txtNbWagonJoueur.getText()))
                    this.txtNbWagonJoueur.setText(" ");
            this.nbWagonJoueur =Integer.parseInt(this.txtNbWagonJoueur.getText());
        }
        if (e.getSource() == this.txtNbWagonFin) {
            if(!fonctionAux.isInteger(this.txtNbWagonFin.getText()))
                    this.txtNbWagonFin.setText(" ");
            this.nbWagonFin= Integer.parseInt(this.txtNbWagonFin.getText());
        }

        if (e.getSource() == this.btnAjoutNoeud) {
            if (fonctionAux.isInteger(this.txtX.getText()) && fonctionAux.isInteger(this.txtY.getText()) && !this.txtNomNoeud.getText().equals(""))  
                {
                    this.model.addRow(new Object[] {
                    this.txtNomNoeud.getText(),
                    this.txtX.getText(),
                    this.txtY.getText()});
                }
                this.txtNomNoeud.setText("");
                this.txtX.setText("");
                this.txtY.setText("");
        }
        if (e.getSource() == this.btnSupprNoeud) {
            this.model.removeRow(this.jTabNoeud.getSelectedRow());
        }
        if (e.getSource() == this.btnClear) {
            this.txtNomNoeud.setText(" ");
            this.txtX.setText(" ");
            this.txtY.setText(" ");
        }
        if (e.getSource() == this.btnSuivant) {
            this.setVisible(false);
            this.ctrl.changerPanel("panelArrete");
        }
    }

}