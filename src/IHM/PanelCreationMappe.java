import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class PanelCreationMappe extends JPanel //implements ActionListener
{
    private JLabel lblMappe;
    private JLabel lblTheme;
    private JPanel pnlNomTheme;
    private JLabel lblInformationJoueur;
    private JPanel pnlFormulaireJoueur;
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
        this.setLayout(new GridLayout(10,1));
        this.lblMappe = new JLabel("Créer Mappe");
        this.lblTheme = new JLabel("Sélection Thème");
        this.pnlNomTheme = new JPanel();
        this.lblInformationJoueur = new JLabel("Information Joueur");
        this.pnlFormulaireJoueur = new JPanel();
        this.lblInformationWagon = new JLabel("Information Wagon");
        this.pnlFormulaireWagon = new JPanel();
        this.lblVille = new JLabel("Ajouter Ville");
        this.pnlVille = new JPanel();
        this.lblRoute = new JLabel("Ajouter Route");
        this.pnlRoute = new JPanel();
        this.lblInformationObjectif = new JLabel("Information Carte Objectif");
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
