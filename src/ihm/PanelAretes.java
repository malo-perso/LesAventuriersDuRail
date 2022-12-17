package src.ihm;

import src.Controleur;
import src.metier.Noeud;
import src.metier.Arete;
import src.metier.Type;

import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.Vector;

public class PanelAretes extends JPanel implements ActionListener {

    private PanelPlateau panelPlateau;

    private static final String[] NOM_COLONNE = {"Noeud 1", "Noeud 2", "Longueur", "Type"};
    private DefaultTableModel model;

    private Image  imgFond;

    private JTable tabAretes;

    private JPanel panelValidation, panelArete, panelAreteBtn, panelRemplissage, panelTable;

    private JButton btnRetour, btnSuivant, btnAjoutArete, btnSupprArete, btnClear;
    private JComboBox listNoeud1, listNoeud2, listTypeArete;

    private JTextField txtLongueurArete;

    private Controleur ctrl;

    private int indN1;
    private int indN2;

    public PanelAretes(Controleur ctrl) {

        // création des composants
        this.ctrl = ctrl;
        
        this.panelTable = new JPanel();
        this.panelValidation = new JPanel();
        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");

        this.imgFond = getToolkit().getImage ( "../data/images/logo.png" );

        this.model = new DefaultTableModel(NOM_COLONNE, 0);
        this.tabAretes = new JTable(this.model);
        this.tabAretes.setFillsViewportHeight(true);
        this.tabAretes.setEnabled(false);

        JScrollPane spTabAretes = new JScrollPane(this.tabAretes);

        this.btnClear = new JButton("Effacer");
        this.btnAjoutArete = new JButton("Ajouter +");
        this.btnSupprArete = new JButton("Supprimer -");
        this.btnSuivant = new JButton("Suivant");

        Vector<String> vNoeud = new Vector<String>(this.ctrl.getNomNoeuds());
        Vector<String> vType = new Vector<String>(this.ctrl.getTypes()); 

        this.listNoeud1 = new JComboBox<>(vNoeud);
        this.listNoeud2 = new JComboBox<>(vNoeud);
        this.listTypeArete = new JComboBox<>(vType);
        this.txtLongueurArete = new JTextField();

        this.listNoeud1.setSelectedItem(null);
        this.listNoeud2.setSelectedItem(null);

        // création des layouts
        this.setLayout(new BorderLayout());
        this.panelTable       = new JPanel(new BorderLayout());
        this.panelArete       = new JPanel(new BorderLayout());
        this.panelAreteBtn    = new JPanel(new GridLayout(2,2));
        this.panelValidation  = new JPanel(new BorderLayout());
        this.panelRemplissage = new JPanel(new GridLayout(3,4));

        // activation des composants
        this.btnAjoutArete.addActionListener(this);
        this.btnSupprArete.addActionListener(this);
        this.btnClear.addActionListener(this);
        this.btnSuivant.addActionListener(this);
        this.btnRetour.addActionListener(this);

        // ajout des composants
        this.panelRemplissage.add(new JLabel("Noeud1 :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Noeud2 :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Longueur :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Type :", SwingConstants.CENTER));
        this.panelRemplissage.add(this.listNoeud1);
        this.panelRemplissage.add(this.listNoeud2);
        this.panelRemplissage.add(this.txtLongueurArete);
        this.panelRemplissage.add(this.listTypeArete);
        this.panelRemplissage.add(this.btnClear);
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());

        this.panelArete.add(this.panelRemplissage, BorderLayout.CENTER);

        this.panelAreteBtn.add(this.btnAjoutArete);
        this.panelAreteBtn.add(this.btnSupprArete);
        this.panelAreteBtn.add(new JLabel());
        
        this.panelTable.add(this.panelArete, BorderLayout.NORTH);
        this.panelTable.add(spTabAretes, BorderLayout.CENTER);
        this.panelTable.add(this.panelAreteBtn, BorderLayout.SOUTH);
        
        this.panelValidation.add(this.btnRetour, BorderLayout.WEST);
        this.panelValidation.add(this.btnSuivant, BorderLayout.EAST);

        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);

        this.btnRetour.addActionListener(this);
        this.btnSuivant.addActionListener(this);
    }

    //renvoie la liste des aretes de la JTable
    public ArrayList<Arete> getAretes() {
        ArrayList<Arete> aretes = new ArrayList<Arete>();
        for (int i = 0; i < this.model.getRowCount(); i++) {
            aretes.add(new Arete((Noeud) this.model.getValueAt(i, 0), (Noeud) this.model.getValueAt(i, 1), (int) this.model.getValueAt(i, 2), (Type) this.model.getValueAt(i, 3) ));
        }
        return aretes;
    }

    // fonction qui permet d effacer le contenu des composant du panelRemplissage
    private void removePanelRemplissage(){
        this.listNoeud1.removeActionListener(this);
        this.listNoeud2.removeActionListener(this);

        this.listNoeud1.setSelectedItem(null);
        this.listNoeud2.setSelectedItem(null);
        this.txtLongueurArete.setText("");
        this.listTypeArete.setSelectedItem(null);

        this.listNoeud1.addActionListener(this);
        this.listNoeud2.addActionListener(this);
    }

    // ajoute une arete dans le JTable
    public void ajouterArete(Noeud noeud1, Noeud noeud2, int longueur, Type type) {
        
        DefaultTableModel model = (DefaultTableModel) this.tabAretes.getModel();
        model.addRow( new Object[] {noeud1, noeud2, longueur, type} );
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnClear){
            this.removePanelRemplissage();
        }

        if(e.getSource() == this.btnAjoutArete){
            if(this.listNoeud1.getSelectedItem() != null && this.listNoeud2.getSelectedItem() != null && !this.txtLongueurArete.equals("") ){

                this.model.addRow(new Object[]{
                    this.listNoeud1.getSelectedItem(),
                    this.listNoeud2.getSelectedItem(),
                    this.txtLongueurArete.getText(),
                    this.listTypeArete.getSelectedItem()
                });

                this.removePanelRemplissage();

                //this.panelPlateau.tracerArete();
            }
        }

        if(e.getSource() == this.btnSupprArete){
            if(this.tabAretes.getSelectedRow() != -1){this.model.removeRow(this.tabAretes.getSelectedRow());}
        }

        if ( e.getSource() == this.btnRetour) {
            this.ctrl.getIHM().changePanel("panelVehicule");
        }

        if ( e.getSource() == this.btnSuivant) {
            this.ctrl.getIHM().changePanel("panelListeObjectif");
        }

        if(e.getSource() == this.listNoeud1){
            if(this.listNoeud1.getSelectedIndex() == this.listNoeud2.getSelectedIndex()){

                this.listNoeud1.removeActionListener(this);
                this.listNoeud2.removeActionListener(this);

                this.listNoeud1.setSelectedIndex(this.listNoeud2.getSelectedIndex());
                this.listNoeud2.setSelectedIndex(this.indN1);

                this.listNoeud1.addActionListener(this);
                this.listNoeud2.addActionListener(this);
            }

            this.indN1 = this.listNoeud1.getSelectedIndex();
            this.indN2 = this.listNoeud2.getSelectedIndex();
        }

        if(e.getSource() == this.listNoeud2){
            if(this.listNoeud1.getSelectedIndex() == this.listNoeud2.getSelectedIndex()){

                this.listNoeud1.removeActionListener(this);
                this.listNoeud2.removeActionListener(this);

                this.listNoeud2.setSelectedIndex(this.listNoeud1.getSelectedIndex());
                this.listNoeud1.setSelectedIndex(this.indN2);

                this.listNoeud1.addActionListener(this);
                this.listNoeud2.addActionListener(this);
            }

            this.indN2 = this.listNoeud2.getSelectedIndex();
            this.indN1 = this.listNoeud1.getSelectedIndex();
        }

    }
}