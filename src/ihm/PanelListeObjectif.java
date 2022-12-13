
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelListeObjectif extends JPanel implements ActionListener{

    private static final String[] COLUMNS = {"Noeud1", "Noeud2", "Points"};
    private DefaultTableModel tabCarte = new DefaultTableModel(COLUMNS, 0);

    private JPanel panelRemplissage;
    private JTextField txtNoeud1;
    private JTextField txtNoeud2;
    private JTextField txtNbPoints;

    private JPanel panelTable;
    private JTable jTabCarte;
    private JButton btnAjoutCarte;

    private JPanel panelValidation;
    private JButton btnRetour;
    private JButton btnSuivant;

    public PanelListeObjectif(){

        JScrollPane spTabCarte; 

        this.panelRemplissage = new JPanel();
        this.txtNoeud1 = new JTextField();
        this.txtNoeud2 = new JTextField();
        this.txtNbPoints = new JTextField();
    
        this.panelTable = new JPanel();
        this.jTabCarte = new JTable(this.tabCarte);
        this.btnAjoutCarte = new JButton("Ajouter +");
        this.panelValidation = new JPanel();
        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Enregistrer");

        this.jTabCarte.setFillsViewportHeight(true);
        this.setLayout(new BorderLayout());
        this.panelRemplissage.setLayout(new GridLayout(2,3));
        this.panelTable.setLayout(new BorderLayout());
        this.panelValidation.setLayout(new GridLayout(1,2, 10, 10));

        //Action listener
        this.btnAjoutCarte.addActionListener(this);
        this.btnRetour.addActionListener(this);
        this.btnSuivant.addActionListener(this);

        spTabCarte = new JScrollPane(this.jTabCarte);
        
        this.panelRemplissage.add(new JLabel("Noeud1 :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Noeud2 :", SwingConstants.CENTER));
        this.panelRemplissage.add(new JLabel("Points :", SwingConstants.CENTER));
        this.panelRemplissage.add(this.txtNoeud1);
        this.panelRemplissage.add(this.txtNoeud2);
        this.panelRemplissage.add(this.txtNbPoints);

        this.panelTable.add(this.panelRemplissage, BorderLayout.NORTH);
        this.panelTable.add(spTabCarte, BorderLayout.CENTER);
        this.panelTable.add(this.btnAjoutCarte, BorderLayout.SOUTH);

        this.panelValidation.add(this.btnRetour);
        this.panelValidation.add(this.btnSuivant);

        this.add(new JLabel("Carte objectif", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);
        

    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.add(new PanelListeObjectif());
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnAjoutCarte){
            if (!this.txtNoeud1.getText().equals("") && !this.txtNoeud2.getText().equals("") && !this.txtNbPoints.getText().equals("") ){

                this.tabCarte.addRow(new Object[]{
                    this.txtNoeud1.getText(),
                    this.txtNoeud2.getText(),
                    this.txtNbPoints.getText()
                });

                this.txtNoeud1.setText("");
                this.txtNoeud2.setText("");
                this.txtNbPoints.setText("");
            }
        }
        if(e.getSource() == this.btnRetour){
            
        }
        if(e.getSource() == this.btnSuivant){
            
        }
        
        
    }

}
