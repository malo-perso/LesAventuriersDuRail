package src.ihm;

import src.Controleur;
import src.metier.Noeud;
import src.metier.Arete;
import src.metier.FonctionAux;
import src.ihm.grilles.GrillesAreteModel;

import java.awt.event.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.Vector;

public class PanelAretes extends JPanel implements ActionListener {

    private GrillesAreteModel model;

    private JTable tabAretes;

    private JPanel panelValidation, panelArete, panelAreteBtn, panelRemplissage, panelTable, panelClear;

    private JButton btnRetour, btnSuivant, btnAjoutArete, btnSupprArete, btnClear;
    private JComboBox<String> listNoeud1, listNoeud2, listTypeArete;

    private JTextField txtLongueurArete;

    private Controleur ctrl;


    public PanelAretes(Controleur ctrl) {

        // création des composants
        this.ctrl = ctrl;
        
        this.panelTable = new JPanel();
        this.panelValidation = new JPanel();
        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");

        this.model = new GrillesAreteModel(this.ctrl);
        this.tabAretes = new JTable(this.model);
        this.tabAretes.setFillsViewportHeight(true);
        this.tabAretes.setEnabled(true);

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
        this.panelValidation  = new JPanel(new GridLayout(1,3));
        this.panelRemplissage = new JPanel(new GridLayout(2,4));
        this.panelClear       = new JPanel(new GridLayout(2,3));

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

        this.panelClear.add(new JLabel());
        this.panelClear.add(this.btnClear);
        this.panelClear.add(new JLabel());
        this.panelClear.add(new JLabel());
        this.panelClear.add(new JLabel());
        this.panelClear.add(new JLabel());
        

        this.panelArete.add(this.panelClear, BorderLayout.SOUTH);
        this.panelArete.add(this.panelRemplissage, BorderLayout.CENTER);

        this.panelAreteBtn.add(this.btnAjoutArete);
        this.panelAreteBtn.add(this.btnSupprArete);
        this.panelAreteBtn.add(new JLabel());
        
        this.panelTable.add(this.panelArete, BorderLayout.NORTH);
        this.panelTable.add(spTabAretes, BorderLayout.CENTER);
        this.panelTable.add(this.panelAreteBtn, BorderLayout.SOUTH);
        
        this.panelValidation.add(this.btnRetour);
        this.panelValidation.add(new JLabel());
        this.panelValidation.add(this.btnSuivant);

        this.add(new JLabel("Création des arêtes", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);

        this.btnRetour.addActionListener(this);
        this.btnSuivant.addActionListener(this);

        this.listNoeud1.addActionListener(this);
        this.listNoeud2.addActionListener(this);
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

    //renvoie une liste de noms de noeud sauf celui passé en parametre
    public ArrayList<String> getNomNoeuds(String nomNoeud){
        ArrayList<String> lstNomNoeuds = this.ctrl.getNomNoeuds();
        lstNomNoeuds.remove(nomNoeud);
        return lstNomNoeuds;
    }

    public void majNoeud(ArrayList<Noeud> lstNoeuds) {
        this.listNoeud1.removeActionListener(this);
        this.listNoeud2.removeActionListener(this);

        this.listNoeud1.removeAllItems();
        this.listNoeud2.removeAllItems();

        for (Noeud noeud : lstNoeuds) {
            this.listNoeud1.addItem(noeud.getNom());
        }

        this.listNoeud1.addActionListener(this);
        this.listNoeud2.addActionListener(this);
    }

    public void majArete(ArrayList<Arete> lstAretes) {
        this.model.majTable(lstAretes);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnClear){
            this.removePanelRemplissage();
        }

        if(e.getSource() == this.btnAjoutArete){
            if(this.listNoeud1.getSelectedItem() != null    &&
               this.listNoeud2.getSelectedItem() != null    &&
               FonctionAux.isInteger(this.txtLongueurArete.getText())  &&
               this.listTypeArete.getSelectedItem() != null
              )
            {
                this.ctrl.ajouterArete(
                    this.ctrl.getLstNoeuds().get(this.listNoeud1.getSelectedIndex()),
                    this.ctrl.getLstNoeuds().get(this.listNoeud2.getSelectedIndex()),
                    Integer.parseInt(this.txtLongueurArete.getText()),
                    (String)this.listTypeArete.getSelectedItem()
                );
                this.removePanelRemplissage();
            }

            if(!FonctionAux.isInteger(this.txtLongueurArete.getText())){
                this.txtLongueurArete.setText("");
            }

            //this.panelPlateau.tracerArete();
        }

        if(e.getSource() == this.btnSupprArete){
            if(this.tabAretes.getSelectedRow() != -1){
                this.ctrl.supprimerArete(this.ctrl.getLstAretes().get(this.tabAretes.getSelectedRow()));
            }
        }

        if ( e.getSource() == this.btnRetour) {
            this.ctrl.getIHM().changePanel("panelVehicule");
        }

        if ( e.getSource() == this.btnSuivant) {
            this.ctrl.getIHM().changePanel("panelListeObjectif");
        }

        if(e.getSource() == this.listNoeud1){
            
            if (this.listNoeud1.getSelectedItem() == this.listNoeud2.getSelectedItem()) {
                this.listNoeud2.setSelectedItem(null);
            }
        }

        if(e.getSource() == this.listNoeud2){
            
            if (this.listNoeud2.getSelectedItem() == this.listNoeud1.getSelectedItem()) {
                this.listNoeud1.setSelectedItem(null);
            }
        }

    }
    
}