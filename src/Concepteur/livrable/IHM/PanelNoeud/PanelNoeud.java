package livrable.IHM.PanelNoeud;

import livrable.Controleur;
import livrable.IHM.PanelConcepteur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelNoeud extends JPanel implements ActionListener {
    private PanelJoueur pnlJoueur;
    private PanelWagonJoueur pnlWagonJoueur;
    private PanelListeNoeud pnlListeNoeud;
    private JPanel pnlSuivant;
    private JButton btnSuivant;
    private PanelConcepteur pnlConcepteur;
    private Controleur controleur;
    public PanelNoeud(PanelConcepteur pnlConcepteur,Controleur controleur)
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.pnlConcepteur = pnlConcepteur;

        this.controleur = controleur;


        this.pnlJoueur = new PanelJoueur();
        this.pnlWagonJoueur = new PanelWagonJoueur();
        this.pnlListeNoeud = new PanelListeNoeud(this.pnlConcepteur,this.controleur);

        this.pnlSuivant = new JPanel(new BorderLayout());

        this.btnSuivant = new JButton("Suivant");
        this.pnlSuivant.add(this.btnSuivant,BorderLayout.EAST);

        this.add(this.pnlJoueur);
        this.add(this.pnlWagonJoueur);
        this.add(this.pnlListeNoeud);
        this.add(this.pnlSuivant);

        this.btnSuivant.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== this.btnSuivant)
        {
            this.pnlConcepteur.showPanel("ListeNoeud");
        }

    }
}
