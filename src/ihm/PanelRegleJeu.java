import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.metier.Noeud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PanelRegleJeu extends JPanel implements ActionListener{
    
    private static final String[] NOM_COLONNE = {"Nom", "X", "Y"};

    private JButton btnSuivant,btnAjoutVille,btnSupprVille,btnClear;

    private JTextField txtNbJoueurMin,txtNbJoueurMax,txtDoubleVoie,txtNbWagonFin,txtNbWagonJoueur,txtNomVille,txtX,txtY;

    private JTable tableVille;
    private DefaultTableModel model; 

    private JPanel panelRegle,panelValidation,panelVille,panelVilleBtn,panelTexteVille;

    private int nbJoueurMin;
    private int nbJoueurMax;
    private int doubleVoie; 
    private int nbWagonJoueur;
    private int nbWagonFin;


    
    public PanelRegleJeu()
    {
        this.setLayout(new BorderLayout());

        this.nbJoueurMax = 5;
        this.nbJoueurMin = 2;
        this.doubleVoie = 3;
        this.nbWagonJoueur = 45;
        this.nbWagonFin = 2;

        this.btnSuivant = new JButton("Suivant");
        this.btnAjoutVille = new JButton("Ajouter");
        this.btnSupprVille = new JButton("Supprimer");
        this.btnClear = new JButton("Clear");
        
        this.txtNbJoueurMin = new JTextField(this.nbJoueurMin+" ");
        this.txtNbJoueurMax = new JTextField(this.nbJoueurMax+" ");
        this.txtNbWagonJoueur = new JTextField(this.nbWagonJoueur+" ");
        this.txtDoubleVoie = new JTextField(this.doubleVoie+" ");
        this.txtNbWagonFin = new JTextField(this.nbWagonFin+" ");
        this.txtNomVille = new JTextField("Nom de la ville");
        this.txtX = new JTextField("X");
        this.txtY = new JTextField("Y");


        this.panelRegle = new JPanel(new GridLayout(10,2, 5, 5));
        this.panelVille = new JPanel(new BorderLayout());
        this.panelVilleBtn = new JPanel();
        this.panelValidation = new JPanel(new BorderLayout());

        this.panelRegle.add(new JLabel("Joueur minimum"));
        this.panelRegle.add(this.txtNbJoueurMin);
        this.panelRegle.add(new JLabel("Joueur maximum"));
        this.panelRegle.add(this.txtNbJoueurMax);
        this.panelRegle.add(new JLabel("Nombre de wagon par joueur"));
        this.panelRegle.add(this.txtNbWagonJoueur);
        this.panelRegle.add(new JLabel("Joueur minimum pour utiliser les doubles voies"));
        this.panelRegle.add(this.txtDoubleVoie);
        this.panelRegle.add(new JLabel("Nombre de wagon pour finir la partie"));
        this.panelRegle.add(this.txtNbWagonFin);

        this.model = new DefaultTableModel(NOM_COLONNE, 0);
        this.tableVille = new JTable(this.model);
        
        
        this.panelVilleBtn.add(this.btnAjoutVille);
        this.panelVilleBtn.add(this.btnSupprVille);
        this.panelVilleBtn.add(this.btnClear);

        this.panelTexteVille = new JPanel(new BorderLayout());
        this.panelTexteVille.add(this.txtNomVille, BorderLayout.NORTH);
        this.panelTexteVille.add(this.txtX, BorderLayout.CENTER);
        this.panelTexteVille.add(this.txtY, BorderLayout.SOUTH);

        this.panelVille.add(this.panelTexteVille, BorderLayout.NORTH);
        this.panelVille.add(new JScrollPane(this.tableVille), BorderLayout.CENTER);
        this.panelVille.add(this.panelVilleBtn, BorderLayout.SOUTH);

        this.panelValidation.add(this.btnSuivant, BorderLayout.EAST);

        this.add(this.panelRegle, BorderLayout.NORTH);
        this.add(this.panelVille, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);

        this.txtNbJoueurMin.addActionListener(this);
        this.txtNbJoueurMax.addActionListener(this);
        this.txtDoubleVoie.addActionListener(this);
        this.txtNbWagonJoueur.addActionListener(this);
        this.txtNbWagonFin.addActionListener(this);
        this.btnAjoutVille.addActionListener(this);
        this.btnSupprVille.addActionListener(this);
        this.btnClear.addActionListener(this);
        this.btnSuivant.addActionListener(this);
        
    }

    public int getNbJoueurMin() {
        return this.nbJoueurMin;
    }

    public int getNbJoueurMax() {
        return this.nbJoueurMax;
    }

    public int getDoubleVoie() {
        return this.doubleVoie;
    }

    public int getNbWagonJoueur() {
        return this.nbWagonJoueur;
    }

    public int getNbWagonFin() {
        return this.nbWagonFin;
    }

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

            this.txtNbJoueurMax.setText(this.txtNbJoueurMin.getText());
        }
        if (e.getSource() == this.txtNbJoueurMax) {
            this.txtNbJoueurMin.setText(this.txtNbJoueurMax.getText());
        }
        if (e.getSource() == this.txtDoubleVoie) {
            this.txtDoubleVoie.setText(this.txtDoubleVoie.getText());
        }
        if (e.getSource() == this.txtNbWagonJoueur) {
            this.txtNbWagonJoueur.setText(this.txtNbWagonJoueur.getText());
        }
        if (e.getSource() == this.txtNbWagonFin) {
            this.txtNbWagonFin.setText(this.txtNbWagonFin.getText());
        }

        if (e.getSource() == this.btnAjoutVille) {
            this.model.addRow(new Object[] {
            this.txtNomVille.getText(),
            this.txtX.getText(),
            this.txtY.getText()});
        }
        if (e.getSource() == this.btnSupprVille) {
            this.model.removeRow(this.tableVille.getSelectedRow());
        }
        if (e.getSource() == this.btnClear) {
            this.txtNomVille.setText(" ");
            this.txtX.setText(" ");
            this.txtY.setText(" ");
        }
    }
}
