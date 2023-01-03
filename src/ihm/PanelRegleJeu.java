package src.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;

import javax.swing.*;

import src.Controleur;
import src.ihm.grilles.GrillesNoeudModel;
import src.metier.FonctionAux;
import src.metier.Noeud;

import java.util.ArrayList;


public class PanelRegleJeu extends JPanel implements ActionListener{

    private GrillesNoeudModel model;
    private JTable jTabNoeud;
    
    private JPanel panelRegle,panelValidation,panelNoeud,panelNoeudBtn,panelRemplissage,panelTable,panelSaisie,panelLabel;
    
    private JButton btnSuivant,btnAjoutNoeud,btnSupprNoeud,btnClear;
    
    private JTextField txtNbJoueurMin,txtNbJoueurMax,txtDoubleVoie,txtNbWagonFin,txtNbWagonJoueur,txtNomNoeud,txtX,txtY,txtNomX,txtNomY,txtNbPointCheminLong;/*txtTailleNoeud*/
    
    private JCheckBox cbCheminLong;

    private int nbJoueurMin;
    private int nbJoueurMax;
    private int doubleVoie; 
    private int nbWagonJoueur;
    private int nbWagonFin;
    private int nbPointCheminLong;

    private Controleur ctrl;

    public PanelRegleJeu(Controleur ctrl)
    {
        //Creation des composants
        this.ctrl = ctrl;
        
        this.nbJoueurMax   = ctrl.getMetier().getNombreJoueurMaximum();
        this.nbJoueurMin   = ctrl.getMetier().getNombreJoueurMinimum();
        this.doubleVoie    = ctrl.getMetier().getNombreJoueurMiniDoubleRoute();
        this.nbWagonJoueur = ctrl.getMetier().getNbWagonJoueur();
        this.nbWagonFin    = ctrl.getMetier().getNbWagonFinPartie();
        this.nbPointCheminLong = ctrl.getMetier().getNbPointCheminLong();

        this.model = new GrillesNoeudModel(this.ctrl);
        this.jTabNoeud = new JTable(this.model);
        this.jTabNoeud.setFillsViewportHeight(true);
        this.jTabNoeud.setEnabled(true);

        JScrollPane spTabNoeud = new JScrollPane(this.jTabNoeud);

        this.cbCheminLong = new JCheckBox("Chemin le plus long :");

        this.btnClear = new JButton("Effacer");
        this.btnAjoutNoeud = new JButton("Ajouter +");
        this.btnSupprNoeud = new JButton("Supprimer -");
        this.btnSuivant    = new JButton("Suivant");
        
        this.txtNbJoueurMin   = new JTextField(this.nbJoueurMin+"");
        this.txtNbJoueurMax   = new JTextField(this.nbJoueurMax+"");
        this.txtNbWagonJoueur = new JTextField(this.nbWagonJoueur+"");
        this.txtDoubleVoie    = new JTextField(this.doubleVoie+"");
        this.txtNbWagonFin    = new JTextField(this.nbWagonFin+"");
        this.txtNbPointCheminLong = new JTextField();

        if(this.nbPointCheminLong < 1 ){
            this.cbCheminLong.setSelected(false);
            this.txtNbPointCheminLong.setEditable(false);
        }else{
            this.cbCheminLong.setSelected(true);
            this.txtNbPointCheminLong.setEditable(true);
            this.txtNbPointCheminLong.setText(this.nbPointCheminLong+"");
        }

        //this.txtTailleNoeud   = new JTextField();


        this.txtNomNoeud = new JTextField();
        this.txtX        = new JTextField();
        this.txtY        = new JTextField();
        this.txtNomX     = new JTextField();
        this.txtNomY     = new JTextField();
        
        //Creation des layout
        this.setLayout(new BorderLayout());
        this.panelRegle = new JPanel(new GridLayout(1,2));
        this.panelLabel = new JPanel(new GridLayout(6,1, 0, 3));
        this.panelSaisie = new JPanel(new GridLayout(6,2,0,3));
        this.panelTable = new JPanel(new BorderLayout());
        this.panelNoeud = new JPanel(new BorderLayout());
        this.panelNoeudBtn = new JPanel(new GridLayout(2,2));
        this.panelValidation = new JPanel(new GridLayout(1,3));
        this.panelRemplissage = new JPanel(new GridLayout(4,5));
        
        //Action listener
        this.txtNbJoueurMin.addActionListener(this);
        this.txtNbJoueurMax.addActionListener(this);
        this.txtDoubleVoie.addActionListener(this);
        this.txtNbWagonJoueur.addActionListener(this);
        this.txtNbWagonFin.addActionListener(this);
        this.txtNbPointCheminLong.addActionListener(this);
        this.btnAjoutNoeud.addActionListener(this);
        this.btnSupprNoeud.addActionListener(this);
        this.btnClear.addActionListener(this);
        this.btnSuivant.addActionListener(this);
        this.cbCheminLong.addActionListener(this);

        //Positionnement des composants
        this.panelLabel.add(new JLabel("Joueur minimum :", SwingConstants.RIGHT));
        this.panelLabel.add(new JLabel("Joueur maximum :", SwingConstants.RIGHT));
        this.panelLabel.add(new JLabel("Nombre de wagon par joueur :", SwingConstants.RIGHT));
        this.panelLabel.add(new JLabel("Joueur minimum pour les doubles voies :", SwingConstants.RIGHT));      
        this.panelLabel.add(new JLabel("Nombre de wagon pour finir la partie :", SwingConstants.RIGHT));
        //this.panelLabel.add(new JLabel("Taille des Noeuds :", SwingConstants.RIGHT));
        this.panelLabel.add(this.cbCheminLong);

        this.panelSaisie.add(this.txtNbJoueurMin);
        this.panelSaisie.add(new JLabel());
        this.panelSaisie.add(this.txtNbJoueurMax);
        this.panelSaisie.add(new JLabel());
        this.panelSaisie.add(this.txtNbWagonJoueur);
        this.panelSaisie.add(new JLabel());
        this.panelSaisie.add(this.txtDoubleVoie);
        this.panelSaisie.add(new JLabel());
        this.panelSaisie.add(this.txtNbWagonFin);
        this.panelSaisie.add(new JLabel());
        this.panelSaisie.add(this.txtNbPointCheminLong);
        this.panelSaisie.add(new JLabel());
        //this.panelSaisie.add(this.txtTailleNoeud);
        //this.panelSaisie.add(new JLabel());

        this.panelRegle.add(this.panelLabel);
        this.panelRegle.add(this.panelSaisie);
        

        this.panelRemplissage.add(new JLabel("Nom :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("X :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Y :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("NomX :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("NomY :", SwingConstants.CENTER));

        this.panelRemplissage.add(this.txtNomNoeud);
        this.panelRemplissage.add(this.txtX);
        this.panelRemplissage.add(this.txtY);
        this.panelRemplissage.add(this.txtNomX);
        this.panelRemplissage.add(this.txtNomY);
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(this.btnClear);
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());
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
        
        this.panelValidation.add(new JLabel());
        this.panelValidation.add(new JLabel());
        this.panelValidation.add(this.btnSuivant);
        
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
    public int getNbPointCheminLong() {return this.nbPointCheminLong;}


    public void maJTable(ArrayList<Noeud> noeuds) {
        this.model.majTable(noeuds);
    }
    // ai supprimé ajouterNoeud car le contenu de la table se cale uniquement sur le métier
    // si changement dans le métier il y a, appeler la méthode maJTable
    
    public void majTxtRegleJeu(int nbJoueurMin, int nbJoueurMax, int doubeVoie, int nbWagon, int nbWagonFin, int nbPointCheminLong){
        this.nbJoueurMin = nbJoueurMin;
        this.nbJoueurMax = nbJoueurMax;
        this.doubleVoie = doubeVoie;
        this.nbWagonJoueur = nbWagon;
        this.nbWagonFin    = nbWagonFin;
        this.nbPointCheminLong = nbPointCheminLong;

        this.txtNbJoueurMin.setText(nbJoueurMin +"");
        this.txtNbJoueurMax.setText(nbJoueurMax+"");
        this.txtDoubleVoie.setText(doubleVoie +"");
        this.txtNbWagonJoueur.setText(nbWagon +"");
        this.txtNbWagonFin.setText(nbWagonFin +"");

        if(this.nbPointCheminLong < 1 ){
            this.cbCheminLong.setSelected(false);
            this.txtNbPointCheminLong.setText("");
            this.txtNbPointCheminLong.setEditable(false);
        }else{
            this.cbCheminLong.setSelected(true);
            this.txtNbPointCheminLong.setEditable(true);
            this.txtNbPointCheminLong.setText(nbPointCheminLong +"");
            
        }
    }

    public void supprimerRegleJeu(){
        this.nbJoueurMin = 2;
        this.nbJoueurMax = 5;
        this.doubleVoie = 3;
        this.nbWagonJoueur = 45;
        this.nbWagonFin    = 2;
        this.nbPointCheminLong = -1;

        this.txtNbJoueurMin.setText(this.nbJoueurMin+"");
        this.txtNbJoueurMax.setText(this.nbJoueurMax+"");
        this.txtNbWagonJoueur.setText(this.nbWagonJoueur+"");
        this.txtDoubleVoie.setText(this.doubleVoie+"");
        this.txtNbWagonFin.setText(this.nbWagonFin+"");

        if(this.nbPointCheminLong < 1 ){
            this.cbCheminLong.setSelected(false);
            this.txtNbPointCheminLong.setText("");
            this.txtNbPointCheminLong.setEditable(false);
        }else{
            this.cbCheminLong.setSelected(true);
            this.txtNbPointCheminLong.setEditable(true);
            this.txtNbPointCheminLong.setText(nbPointCheminLong +"");
            
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.txtNbJoueurMin) {
            if(!FonctionAux.isInteger(this.txtNbJoueurMin.getText()))
                this.txtNbJoueurMin.setText(" ");
            this.nbJoueurMin = Integer.parseInt(this.txtNbJoueurMin.getText());
            this.ctrl.getMetier().setNombreJoueurMinimum(this.nbJoueurMin);
            this.ctrl.majLabelResume(this.nbJoueurMin, this.nbJoueurMax, this.doubleVoie, this.nbWagonJoueur, this.nbWagonFin, this.nbPointCheminLong);
        }
        if (e.getSource() == this.txtNbJoueurMax) {
            if(!FonctionAux.isInteger(this.txtNbJoueurMax.getText()))
                    this.txtNbJoueurMax.setText(" ");
            this.nbJoueurMax = Integer.parseInt(this.txtNbJoueurMax.getText());
            this.ctrl.getMetier().setNombreJoueurMaximum(this.nbJoueurMax);
            this.ctrl.majLabelResume(this.nbJoueurMin, this.nbJoueurMax, this.doubleVoie, this.nbWagonJoueur, this.nbWagonFin, this.nbPointCheminLong);
        }
        if (e.getSource() == this.txtDoubleVoie) {
            if(!FonctionAux.isInteger(this.txtDoubleVoie.getText()))
                    this.txtDoubleVoie.setText(" ");
            this.doubleVoie = Integer.parseInt(this.txtDoubleVoie.getText());
            this.ctrl.getMetier().setNombreJoueurMiniDoubleRoute(this.doubleVoie);
            this.ctrl.majLabelResume(this.nbJoueurMin, this.nbJoueurMax, this.doubleVoie, this.nbWagonJoueur, this.nbWagonFin, this.nbPointCheminLong);
        }
        if (e.getSource() == this.txtNbWagonJoueur) {
            if(!FonctionAux.isInteger(this.txtNbWagonJoueur.getText()))
                    this.txtNbWagonJoueur.setText(" ");
            this.nbWagonJoueur =Integer.parseInt(this.txtNbWagonJoueur.getText());
            this.ctrl.getMetier().setNbWagonJoueur(this.nbWagonJoueur);
            this.ctrl.majLabelResume(this.nbJoueurMin, this.nbJoueurMax, this.doubleVoie, this.nbWagonJoueur, this.nbWagonFin, this.nbPointCheminLong);
        }
        if (e.getSource() == this.txtNbWagonFin) {
            if(!FonctionAux.isInteger(this.txtNbWagonFin.getText()))
                    this.txtNbWagonFin.setText(" ");
            this.nbWagonFin= Integer.parseInt(this.txtNbWagonFin.getText());
            this.ctrl.getMetier().setNbWagonFinPartie(this.nbWagonFin);
            this.ctrl.majLabelResume(this.nbJoueurMin, this.nbJoueurMax, this.doubleVoie, this.nbWagonJoueur, this.nbWagonFin, this.nbPointCheminLong);
        }

        if (e.getSource() == this.btnAjoutNoeud) {
            if (FonctionAux.isInteger(this.txtX.getText()) && FonctionAux.isInteger(this.txtY.getText()) && !this.txtNomNoeud.getText().equals(""))  
                {
                    this.ctrl.ajouterNoeud(this.txtNomNoeud.getText(),
                                           Integer.parseInt(this.txtX.getText()),
                                           Integer.parseInt(this.txtY.getText()),
                                           Integer.parseInt(this.txtNomX.getText()),
                                           Integer.parseInt(this.txtNomY.getText())
                                        );
                }
                this.txtNomNoeud.setText("");
                this.txtX.setText("");
                this.txtY.setText("");
                this.txtNomX.setText("");
                this.txtNomY.setText("");
        }
        if (e.getSource() == this.btnSupprNoeud) {
            if(this.jTabNoeud.getSelectedRow() != -1){
                this.ctrl.supprimerNoeud(this.ctrl.getLstNoeuds().get(this.jTabNoeud.getSelectedRow()));
            }
        }
        
        if (e.getSource() == this.btnClear) {
            this.txtNomNoeud.setText(" ");
            this.txtX.setText(" ");
            this.txtY.setText(" ");
            this.txtNomX.setText("");
            this.txtNomY.setText("");
        }
        if (e.getSource() == this.btnSuivant) {
            this.setVisible(false);
            this.ctrl.getIHM().changePanel("panelArrete");
        }
        if (e.getSource() == this.cbCheminLong){
            if(this.cbCheminLong.isSelected()){
                this.txtNbPointCheminLong.setEditable(true);
            }else{
                this.txtNbPointCheminLong.setText("");
                this.txtNbPointCheminLong.setEditable(false);
                this.nbPointCheminLong = -1;
            }
        }
        if(e.getSource() == this.txtNbPointCheminLong){
            if(!FonctionAux.isInteger(this.txtNbPointCheminLong.getText()))
                    this.txtNbPointCheminLong.setText(" ");
            this.nbPointCheminLong= Integer.parseInt(this.txtNbPointCheminLong.getText());
            this.ctrl.getMetier().setNbPointCheminLong(this.nbPointCheminLong);
            this.ctrl.majLabelResume(this.nbJoueurMin, this.nbJoueurMax, this.doubleVoie, this.nbWagonJoueur, this.nbWagonFin, this.nbPointCheminLong);
        }
    }

}