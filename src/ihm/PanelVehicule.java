package src.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Controleur;
import src.ihm.grilles.GrillesVehiculeModel;
import src.metier.FonctionAux;
import src.metier.CarteVehicule;

import java.awt.*;

import java.util.ArrayList;


public class PanelVehicule extends JPanel implements ActionListener{

    private Controleur ctrl;

    private GrillesVehiculeModel model;
    private JPanel  panelNomVehicule;
    private JPanel  panelVehiculeBtn;
    private JPanel  panelTable;
    private JButton btnAjoutVehicule;
    private JButton btnSupprVehicule;
    private JTextField txtNomVehicule,nbrJoker;
    private JButton btnRetour;
    private JButton btnSuivant;
    private JPanel  panelValidation;

    private Image CarteRecto;
    private Image CarteVerso;
    private Image CarteJoker;

    private JButton btnCarteRecto;
    private JButton btnCarteVerso;
    private JButton btnCarteJoker;

    private JTable jTabVehicule;
    
    public PanelVehicule(Controleur ctrl)
    {
        //Creation des composants
        this.ctrl = ctrl;

        this.txtNomVehicule = new JTextField();

        this.model = new GrillesVehiculeModel(this.ctrl);
        this.jTabVehicule = new JTable(this.model);
        this.jTabVehicule.setFillsViewportHeight(true);
        this.jTabVehicule.setEnabled(true);

        JScrollPane spTabVehicule = new JScrollPane(this.jTabVehicule);

        this.btnAjoutVehicule = new JButton("Ajouter");
        this.btnSupprVehicule = new JButton("Supprimer");

        this.nbrJoker = new JTextField();

        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");

        //Creation des layouts
        this.setLayout(new BorderLayout());
        this.panelNomVehicule = new JPanel(new GridLayout(1, 3));
        this.panelVehiculeBtn = new JPanel(new GridLayout(1, 3));
        this.panelTable = new JPanel(new BorderLayout());

        this.panelValidation = new JPanel(new GridLayout(1, 3));

        // Positionnement des composants
        this.panelNomVehicule.add(new JLabel("Nom du véhicule :", SwingConstants.RIGHT));
        this.panelNomVehicule.add(this.txtNomVehicule);

        this.panelVehiculeBtn.add(this.btnAjoutVehicule);
        this.panelVehiculeBtn.add(this.btnSupprVehicule);
        this.panelVehiculeBtn.add(new JLabel());

        this.panelTable.add(this.panelNomVehicule, BorderLayout.NORTH);
        this.panelTable.add(spTabVehicule, BorderLayout.CENTER);
        this.panelTable.add(this.panelVehiculeBtn, BorderLayout.SOUTH);

        this.panelValidation.add(this.btnRetour);
        this.panelValidation.add(new JLabel());
        this.panelValidation.add(this.btnSuivant);

        //Action listener
        this.btnAjoutVehicule.addActionListener(this);
        this.btnSupprVehicule.addActionListener(this);
        this.btnRetour.addActionListener(this);
        this.btnSuivant.addActionListener(this);

        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);

        this.setBackground(Color.PINK);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if ( e.getSource() == this.btnRetour) {
            this.ctrl.getIHM().changePanel("panelRegleJeu");
        }

        if ( e.getSource() == this.btnSuivant) {
            this.ctrl.getIHM().changePanel("panelArrete");
        }
        if(e.getSource() == this.CarteRecto){
            try{
				System.out.println("Ouvrir");
				JFileChooser chooser = new JFileChooser(".");
				
				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION)
					this.ctrl.lireXML(chooser.getSelectedFile());

            }catch(Exception erreur){erreur.printStackTrace();}
        }
        
    }


}
