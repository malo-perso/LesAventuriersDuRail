package livrable.IHM.PanelNoeud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelJoueur extends JPanel implements ActionListener {

    private JTextField txtNbJoueurMin;
    private JTextField txtNbJoueurMax;
    private JPanel pnlNbJoueur;
    private JTextField txtDoubleVoie;

    private int nbJoueurMin;
    private int nbJoueurMax;
    private int doubleVoie;

    public PanelJoueur() {
        this.setLayout(new GridLayout(3, 3));

        this.txtNbJoueurMin = new JTextField("2");
        this.txtNbJoueurMax = new JTextField("2");
        this.pnlNbJoueur = new JPanel(new GridLayout(2, 2));

        this.pnlNbJoueur.add(new JLabel("Joueur minimum"));
        this.pnlNbJoueur.add(this.txtNbJoueurMin);
        this.pnlNbJoueur.add(new JLabel("Joueur maximum"));
        this.pnlNbJoueur.add(this.txtNbJoueurMax);

        this.add(this.pnlNbJoueur);

        this.add(new JLabel("Joueur minimum pour utiliser les doubles voies", JLabel.CENTER));

        this.txtDoubleVoie = new JTextField("2");
        this.add(this.txtDoubleVoie);

        this.txtNbJoueurMin.addActionListener(this);
        this.txtNbJoueurMax.addActionListener(this);
        this.txtDoubleVoie.addActionListener(this);




        //this.txtNbJoueurMax.addKeyListener(new KeyAdapter

    }

    public int getNbJoueurMin() {
        return this.nbJoueurMin;}
    public int getNbJoueurMax() {
        return this.nbJoueurMax;}
    public int getDoubleVoie() {
        return this.doubleVoie;}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.txtNbJoueurMin) {
            this.txtNbJoueurMax.setText(this.txtNbJoueurMin.getText());
        }
        if (e.getSource() == this.txtNbJoueurMax) {
            this.txtNbJoueurMin.setText(this.txtNbJoueurMax.getText());
        }
        if (e.getSource() == this.txtDoubleVoie) {
            this.txtDoubleVoie.setText(this.txtDoubleVoie.getText());
        }
    }


}






