package IHM.PanelObjectif;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class PanelListeObjectif extends JPanel{

    private JPanel panelTable;
    private JTable tabCarte;
    private JButton btnAjoutCarte;

    private JPanel panelValidation;
    private JButton btnRetour;
    private JButton btnEnregistrer;

    public PanelListeObjectif(){

        JScrollPane spTabCarte; 

        this.panelTable = new JPanel();
        this.tabCarte = new JTable();
        this.btnAjoutCarte = new JButton("Ajouter +");
        this.panelValidation = new JPanel();
        this.btnRetour = new JButton("Retour");
        this.btnEnregistrer = new JButton("Enregistrer");

        //this.tabCarte.setFillsViewportHeight(false);
        this.setLayout(new BorderLayout());
        this.panelTable.setLayout(new BorderLayout());
        this.panelValidation.setLayout(new GridLayout(1,2, 10, 10));



        spTabCarte = new JScrollPane(this.tabCarte);

        this.panelTable.add(new JLabel("Carte objectif", SwingConstants.CENTER), BorderLayout.NORTH);
        this.panelTable.add(spTabCarte, BorderLayout.CENTER);
        this.panelTable.add(this.btnAjoutCarte, BorderLayout.SOUTH);

        this.panelValidation.add(this.btnRetour);
        this.panelValidation.add(this.btnEnregistrer);

        this.add(this.panelTable, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.add(new PanelListeObjectif());
        jf.setVisible(true);
    }

}
