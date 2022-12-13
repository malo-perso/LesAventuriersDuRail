package IHM.asuppr;

import IHM.PanelSelectTheme;

import javax.swing.*;
import java.awt.GridLayout;

public class PanelCreationMappe extends JPanel //implements ActionListener
{
    private JLabel lblMappe;
    private JLabel lblTheme;
    private PanelSelectTheme pnlNomTheme;
    private JLabel lblInformationJoueur;
    private PanelInformationPoint pnlFormulaireJoueur;
    private JLabel lblInformationWagon;
    private JPanel pnlFormulaireWagon;
    private JLabel lblVille;
    private JPanel pnlVille;
    private JLabel lblRoute;
    private JPanel pnlRoute;
    private JLabel lblInformationObjectif;
    private JPanel pnlFormulaireObjectif;

    public PanelCreationMappe ()
    {
        this.setLayout(new GridLayout(14,1));
        this.lblMappe = new JLabel("Créer Mappe",JLabel.CENTER);
        this.lblMappe.setSize(50,50);
        this.lblTheme = new JLabel("Sélection Thème",JLabel.CENTER);
        this.pnlNomTheme = new PanelSelectTheme();
        this.lblInformationJoueur = new JLabel("Information Metier.Joueur",JLabel.CENTER);
        this.pnlFormulaireJoueur = new PanelInformationPoint();
        this.lblInformationWagon = new JLabel("Information Wagon",JLabel.CENTER);
        this.pnlFormulaireWagon = new JPanel();
        this.lblVille = new JLabel("Ajouter Metier.Ville",JLabel.CENTER);
        this.pnlVille = new JPanel();
        this.lblRoute = new JLabel("Ajouter Metier.Route",JLabel.CENTER);
        this.pnlRoute = new JPanel();
        this.lblInformationObjectif = new JLabel("Information Carte Objectif",JLabel.CENTER);
        this.pnlFormulaireObjectif = new JPanel();

        this.add(lblMappe);
        this.add(lblTheme);
        this.add(pnlNomTheme);
        this.add(lblInformationJoueur);
        this.add(pnlFormulaireJoueur);
        this.add(lblInformationWagon);
        this.add(pnlFormulaireWagon);
        this.add(lblVille);
        this.add(pnlVille);
        this.add(lblRoute);
        this.add(pnlRoute);
        this.add(lblInformationObjectif);
        this.add(pnlFormulaireObjectif);
    }
}
