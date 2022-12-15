package src.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Controleur;
import src.metier.CarteObjectif;
import src.metier.Noeud;


public class PanelListeObjectif extends JPanel implements ActionListener{

    private static final String[] COLUMNS = {"Noeud1", "Noeud2", "Points"};
    private DefaultTableModel tabCarte = new DefaultTableModel(COLUMNS, 0);

    private String[] tabNoeud;
    private JTable jTabCarte;

    private JPanel panelRemplissage;
    private JPanel panelTable;
    private JPanel panelActionTab;
    private JPanel panelValidation;

    private JComboBox<String> kbNoeud1;
    private JComboBox<String> kbNoeud2;

    private JButton btnClear;
    private JButton btnAjoutCarte;
    private JButton btnSuppCarte;
    private JButton btnRetour;
    private JButton btnSuivant;

    private JTextField txtNbPoints;

    private int indN1;
    private int indN2;

    private Controleur ctrl;

    public PanelListeObjectif(Controleur ctrl){

        //Creation des composants
        this.ctrl = ctrl;
        
        this.tabNoeud = new String[0];
        this.jTabCarte = new JTable(this.tabCarte);
        this.jTabCarte.setFillsViewportHeight(true);
        this.jTabCarte.setEnabled(false);

        JScrollPane spTabCarte = new JScrollPane(this.jTabCarte);
        
        this.panelRemplissage = new JPanel();
        this.panelTable = new JPanel();
        this.panelActionTab = new JPanel();
        this.panelValidation = new JPanel();

        this.kbNoeud1 = new JComboBox<>(tabNoeud);
        this.kbNoeud2 = new JComboBox<>(tabNoeud);
        this.kbNoeud1.setSelectedItem(null);
        this.kbNoeud2.setSelectedItem(null);
        
        this.btnClear = new JButton("Effacer");
        this.btnAjoutCarte = new JButton("Ajouter +");
        this.btnSuppCarte = new JButton("Supprimer -");
        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");
        
        this.txtNbPoints = new JTextField();
        
        //Creation des layout
        this.setLayout(new BorderLayout());
        this.panelRemplissage.setLayout(new GridLayout(4,3));
        this.panelTable.setLayout(new BorderLayout());
        this.panelActionTab.setLayout(new GridLayout(2,2));
        this.panelValidation.setLayout(new BorderLayout());


        //Action listener
        this.kbNoeud1.addActionListener(this);
        this.kbNoeud2.addActionListener(this);

        this.btnClear.addActionListener(this);
        this.btnAjoutCarte.addActionListener(this);
        this.btnSuppCarte.addActionListener(this);
        this.btnRetour.addActionListener(this);
        this.btnSuivant.addActionListener(this);

        // Positionnement des composants
        this.panelRemplissage.add(new JLabel("Noeud1 :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Noeud2 :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Points :", SwingConstants.CENTER));
        this.panelRemplissage.add(this.kbNoeud1);
        this.panelRemplissage.add(this.kbNoeud2);
        this.panelRemplissage.add(this.txtNbPoints);
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(this.btnClear);
        this.panelRemplissage.add(new JLabel());
        this.panelRemplissage.add(new JLabel());
        
        this.panelActionTab.add(this.btnAjoutCarte);
        this.panelActionTab.add(this.btnSuppCarte);
        this.panelActionTab.add(new JLabel());

        this.panelTable.add(this.panelRemplissage, BorderLayout.NORTH);
        this.panelTable.add(spTabCarte, BorderLayout.CENTER);
        this.panelTable.add(this.panelActionTab, BorderLayout.SOUTH);

        this.panelValidation.add(this.btnRetour, BorderLayout.WEST);
        this.panelValidation.add(this.btnSuivant, BorderLayout.EAST);

        this.add(new JLabel("Carte objectif", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);
    }

    public void setTableNoeud(ArrayList<Noeud> arNoeud){
    
        this.tabNoeud = new String[arNoeud.size()];
        for(int i=0; i<arNoeud.size(); i++){
            this.tabNoeud[i] = arNoeud.get(i).getNom();
        }
    }

    public ArrayList<CarteObjectif> getListCarteObjectif(){
        ArrayList<CarteObjectif> arCarteObj = new ArrayList<CarteObjectif>();

        for(int i=0; i<this.tabCarte.getRowCount()-1; i++){
            arCarteObj.add(new CarteObjectif(null, null, i));
        }
        
        return arCarteObj;
    }

    private void removePanelRemplissage(){
        this.kbNoeud1.removeActionListener(this);
        this.kbNoeud2.removeActionListener(this);

        this.kbNoeud1.setSelectedItem(null);
        this.kbNoeud2.setSelectedItem(null);
        this.txtNbPoints.setText("");

        this.kbNoeud1.addActionListener(this);
        this.kbNoeud2.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnClear){
            this.removePanelRemplissage();
        }
        
        if(e.getSource() == this.btnAjoutCarte){
            if (this.kbNoeud1.getSelectedItem() != null && this.kbNoeud2.getSelectedItem() != null && !this.txtNbPoints.getText().equals("") ){
                
                this.tabCarte.addRow(new Object[]{
                    this.kbNoeud1.getSelectedItem(),
                    this.kbNoeud2.getSelectedItem(),
                    this.txtNbPoints.getText()
                });

                this.removePanelRemplissage();
            }
        }

        if(e.getSource() == this.btnSuppCarte){
            if(this.tabCarte.getRowCount() > 0){
                this.tabCarte.removeRow(this.tabCarte.getRowCount()-1);
            }
        }
        
        if(e.getSource() == this.btnRetour){
            this.ctrl.changerPanel("panelArrete");
        }

        if(e.getSource() == this.btnSuivant){
            this.ctrl.changerPanel("panelResume");

        }

        if(e.getSource() == this.kbNoeud1){
            if(this.kbNoeud1.getSelectedIndex() == this.kbNoeud2.getSelectedIndex()){
                
                this.kbNoeud1.removeActionListener(this);
                this.kbNoeud2.removeActionListener(this);
    
                this.kbNoeud1.setSelectedIndex(this.kbNoeud2.getSelectedIndex());
                this.kbNoeud2.setSelectedIndex(this.indN1);
    
                this.kbNoeud1.addActionListener(this);
                this.kbNoeud2.addActionListener(this);
            }

            this.indN1 = this.kbNoeud1.getSelectedIndex();
            this.indN2 = this.kbNoeud2.getSelectedIndex();
        }

        if(e.getSource() == this.kbNoeud2){
            if(this.kbNoeud1.getSelectedIndex() == this.kbNoeud2.getSelectedIndex()){
                
                this.kbNoeud1.removeActionListener(this);
                this.kbNoeud2.removeActionListener(this);
    
                this.kbNoeud2.setSelectedIndex(this.kbNoeud1.getSelectedIndex());
                this.kbNoeud1.setSelectedIndex(this.indN2);
    
                this.kbNoeud1.addActionListener(this);
                this.kbNoeud2.addActionListener(this);
            }

            this.indN2 = this.kbNoeud2.getSelectedIndex();
            this.indN1 = this.kbNoeud1.getSelectedIndex();
        }
    }
}