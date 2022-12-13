package livrable.IHM;

import livrable.Controleur;
import livrable.IHM.PanelNoeud.PanelListeNoeud;
import livrable.IHM.PanelObjectif.PanelListeObjectif;
import livrable.IHM.PanelNoeud.PanelNoeud;
import javax.swing.*;
import java.awt.*;

public class PanelConcepteur extends JPanel {

    private PanelPlateau pnlPlateau;
    private PanelNoeud pnlNoeud;
    private PanelListeNoeud pnlListeNoeud;
    private PanelListeObjectif pnlListeObjectif;
    private JPanel pnlFormulaire;
    private Controleur controleur;
    public PanelConcepteur(Controleur ctrl) {
        this.setLayout(new BorderLayout());
        this.controleur = ctrl;
        this.pnlPlateau = new PanelPlateau();



        this.pnlListeObjectif = new PanelListeObjectif();


        this.pnlNoeud = new PanelNoeud(this,this.controleur);
        this.pnlListeNoeud = new PanelListeNoeud(this,this.controleur);
        this.pnlListeObjectif = new PanelListeObjectif();

        this.pnlFormulaire = new JPanel(new CardLayout());

        this.add(this.pnlPlateau,BorderLayout.CENTER);
        this.add(this.pnlFormulaire,BorderLayout.EAST);

        this.pnlFormulaire.add(this.pnlNoeud,"Noeud");
        this.pnlFormulaire.add(this.pnlListeNoeud,"ListeNoeud");
        this.pnlFormulaire.add(this.pnlListeObjectif,"ListeObjectif");

    }

    public void showPanel(String nom)
    {
        CardLayout cl = (CardLayout)(this.pnlFormulaire.getLayout());
        cl.show(this.pnlFormulaire, nom);
    }

}
