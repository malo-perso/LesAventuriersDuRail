package livrable.IHM.PanelNoeud;

import livrable.Controleur;
import livrable.IHM.PanelConcepteur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelListeNoeud extends JPanel implements ActionListener {

    private Controleur controleur;
    private JPanel pnlListeNoeud;
    private JPanel pnlBouton;
    private JButton btnSuivant;
    private JButton btnPrecedent;
    private PanelConcepteur pnlConcepteur;
    public PanelListeNoeud(PanelConcepteur pnlConcepteur,Controleur ctrl) {
        this.controleur = ctrl;
        this.pnlConcepteur = pnlConcepteur;

        this.setLayout(new BorderLayout());
        this.pnlListeNoeud = new JPanel();


        this.pnlListeNoeud = new JPanel();



        this.add(new JLabel("Arrets"),BorderLayout.NORTH);
        this.add(this.pnlListeNoeud,BorderLayout.CENTER);
        this.add(this.pnlListeNoeud,BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
