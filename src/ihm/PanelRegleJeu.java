package src.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

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
    
    private JTextField txtNbJoueurMin,txtNbJoueurMax,txtDoubleVoie,txtNbWagonFin,txtNbWagonJoueur,txtNomNoeud,txtX,txtY,txtNomX,txtNomY,txtTailleNoeud;
    
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
        
        this.nbJoueurMax   = ctrl.getMetier().getNombreJoueurMaximum();
        this.nbJoueurMin   = ctrl.getMetier().getNombreJoueurMinimum();
        this.doubleVoie    = ctrl.getMetier().getNombreJoueurMiniDoubleRoute();
        this.nbWagonJoueur = ctrl.getMetier().getNbWagonJoueur();
        this.nbWagonFin    = ctrl.getMetier().getNbWagonFinPartie();

        this.model = new GrillesNoeudModel(this.ctrl);
        this.jTabNoeud = new JTable(this.model);
        this.jTabNoeud.setFillsViewportHeight(true);
        this.jTabNoeud.setEnabled(true);

        JScrollPane spTabNoeud = new JScrollPane(this.jTabNoeud);

        this.btnClear = new JButton("Effacer");
        this.btnAjoutNoeud = new JButton("Ajouter +");
        this.btnSupprNoeud = new JButton("Supprimer -");
        this.btnSuivant    = new JButton("Suivant");
        
        this.txtNbJoueurMin   = new JTextField(this.nbJoueurMin+"");
        this.txtNbJoueurMax   = new JTextField(this.nbJoueurMax+"");
        this.txtNbWagonJoueur = new JTextField(this.nbWagonJoueur+"");
        this.txtDoubleVoie    = new JTextField(this.doubleVoie+"");
        this.txtNbWagonFin    = new JTextField(this.nbWagonFin+"");
        this.txtNomNoeud      = new JTextField();
        this.txtTailleNoeud   = new JTextField();
        this.txtX    = new JTextField();
        this.txtY    = new JTextField();
        this.txtNomX = new JTextField();
        this.txtNomY = new JTextField();
        
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
        this.btnAjoutNoeud.addActionListener(this);
        this.btnSupprNoeud.addActionListener(this);
        this.btnClear.addActionListener(this);
        this.btnSuivant.addActionListener(this);

        //Positionnement des composants
        this.panelLabel.add(new JLabel("Joueur minimum :", SwingConstants.RIGHT));
        this.panelLabel.add(new JLabel("Joueur maximum :", SwingConstants.RIGHT));
        this.panelLabel.add(new JLabel("Nombre de wagon par joueur :", SwingConstants.RIGHT));
        this.panelLabel.add(new JLabel("Joueur minimum pour les doubles voies :", SwingConstants.RIGHT));      
        this.panelLabel.add(new JLabel("Nombre de wagon pour finir la partie :", SwingConstants.RIGHT));
        this.panelLabel.add(new JLabel("Taille des Noeuds :", SwingConstants.RIGHT));

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


    public void maJTable(ArrayList<Noeud> noeuds) {
        this.model.majTable(noeuds);
    }
    // ai supprimé ajouterNoeud car le contenu de la table se cale uniquement sur le métier
    // si changement dans le métier il y a, appeler la méthode maJTable
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.txtNbJoueurMin) {
            if(!FonctionAux.isInteger(this.txtNbJoueurMin.getText()))
                this.txtNbJoueurMin.setText(" ");
            this.nbJoueurMin = Integer.parseInt(this.txtNbJoueurMin.getText());
        }
        if (e.getSource() == this.txtNbJoueurMax) {
            if(!FonctionAux.isInteger(this.txtNbJoueurMax.getText()))
                    this.txtNbJoueurMax.setText(" ");
            this.nbJoueurMax = Integer.parseInt(this.txtNbJoueurMax.getText());
        }
        if (e.getSource() == this.txtDoubleVoie) {
            if(!FonctionAux.isInteger(this.txtDoubleVoie.getText()))
                    this.txtDoubleVoie.setText(" ");
            this.doubleVoie = Integer.parseInt(this.txtDoubleVoie.getText());
        }
        if (e.getSource() == this.txtNbWagonJoueur) {
            if(!FonctionAux.isInteger(this.txtNbWagonJoueur.getText()))
                    this.txtNbWagonJoueur.setText(" ");
            this.nbWagonJoueur =Integer.parseInt(this.txtNbWagonJoueur.getText());
        }
        if (e.getSource() == this.txtNbWagonFin) {
            if(!FonctionAux.isInteger(this.txtNbWagonFin.getText()))
                    this.txtNbWagonFin.setText(" ");
            this.nbWagonFin= Integer.parseInt(this.txtNbWagonFin.getText());
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
            this.ctrl.getIHM().changePanel("panelVehicule");
        }
    }

}