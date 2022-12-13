package livrable.IHM.PanelNoeud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelNoeud extends JPanel implements ActionListener {
    private PanelJoueur pnlJoueur;
    private PanelWagonJoueur pnlWagonJoueur;
    private PanelListeNoeud pnlListeNoeud;
    private JPanel pnlSuivant;
    public PanelNoeud()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.pnlJoueur = new PanelJoueur();
        this.pnlWagonJoueur = new PanelWagonJoueur();
        this.pnlListeNoeud = new PanelListeNoeud();

        this.pnlSuivant = new JPanel(new BorderLayout());
        this.pnlSuivant.add(new JButton("Suivant"),BorderLayout.EAST);


        this.add(this.pnlJoueur);
        this.add(this.pnlWagonJoueur);
        this.add(this.pnlListeNoeud);
        this.add(this.pnlSuivant);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
