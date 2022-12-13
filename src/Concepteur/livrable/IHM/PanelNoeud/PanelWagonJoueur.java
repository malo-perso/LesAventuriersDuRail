package livrable.IHM.PanelNoeud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelWagonJoueur extends JPanel implements ActionListener {

    private JPanel pnlWagonJoueur;
    private JPanel pnlWagonFin;
    private JTextField txtNbWagonFin;
    private JTextField txtNbWagonJoueur;
    private int nbWagonJoueur;
    private int nbWagonFin;

    public PanelWagonJoueur()
    {
        this.setLayout(new GridLayout(3,1));

        this.pnlWagonFin = new JPanel(new GridLayout(1,2));
        this.pnlWagonJoueur = new JPanel(new GridLayout(1,2));

        this.txtNbWagonJoueur = new JTextField("45");
        this.txtNbWagonFin = new JTextField("0");

        this.pnlWagonJoueur.add(new JLabel("Wagon par Joueur"));
        this.pnlWagonJoueur.add(this.txtNbWagonJoueur);

        this.pnlWagonFin.add(this.txtNbWagonFin);
        this.pnlWagonFin.add(new JLabel("wagons"));

        this.add(this.pnlWagonJoueur);
        this.add(new JLabel("Fin de partie si moins de ",JLabel.CENTER));
        this.add(this.pnlWagonFin);
    }

    public int getNbWagonJoueur()
    {
        return this.nbWagonJoueur;
    }

    public int getNbWagonFin()
    {
        return this.nbWagonFin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.txtNbWagonJoueur) {
            this.nbWagonJoueur = Integer.parseInt(this.txtNbWagonJoueur.getText());
        }

        if (e.getSource() == this.txtNbWagonFin) {
            this.nbWagonFin = Integer.parseInt(this.txtNbWagonFin.getText());
        }
    }
}
