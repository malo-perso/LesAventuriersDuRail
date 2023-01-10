package src.ihm;

import src.Controleur;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.io.File;

public class FramePrincipale extends JFrame implements ActionListener {

    private JPanel          panelBase;
    private PanelPioche     panelPioche;
    private PanelJoueurs    panelJoueurs;
    private PanelPlateau    panelPlateau;
    private PanelMainJoueur panelMainJoueur;

    private JButton         btnJouerLocal;

    private JMenuBar menuBarre;

    private JMenu menuAide;
    private JMenu menuPresentation;

    private JMenuItem menuRegles;
    private JMenuItem menuFinDePartie;

    private JScrollPane scrollPane;

    private File reglePDF;

    private Controleur ctrl;

    public FramePrincipale(Controleur ctrl){

        this.setTitle("Les Aventuriers du Rail");
        this.setResizable(true);
        this.setUndecorated(false);

        //Creation des composants
        this.ctrl = ctrl;

        this.panelBase       = new JPanel(new BorderLayout());
        this.panelPioche     = new PanelPioche(this.ctrl);
        this.panelJoueurs    = new PanelJoueurs(this.ctrl);
        this.panelPlateau    = new PanelPlateau(this.ctrl);
        this.panelMainJoueur = new PanelMainJoueur(this.ctrl);

        this.scrollPane = new JScrollPane(this.panelPlateau);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        this.menuBarre = new JMenuBar();

        this.menuAide = new JMenu("Aide");
        
        this.menuRegles = new JMenuItem("Regles");

        this.menuPresentation = new JMenu("Presentation");
        this.menuFinDePartie = new JMenuItem("Fin de Partie");


        this.menuRegles.setIcon(new ImageIcon("./src/data/images/Regles.png"));
        this.reglePDF = new File("./src/data/PDF/Regles.pdf");

        //Action listener

        this.btnJouerLocal = new JButton("Jouer en local");
        this.btnJouerLocal.addActionListener(this);
        this.menuRegles.addActionListener(this);
        this.menuFinDePartie.addActionListener(this);

        //Positionnement des composants
        this.setLayout(new BorderLayout());

        this.panelBase.add(this.scrollPane, BorderLayout.CENTER);
        this.panelBase.add(this.panelJoueurs, BorderLayout.NORTH);

        this.menuAide.add(this.menuRegles);

        this.menuBarre.add(this.menuAide);

        this.menuPresentation.add(this.menuFinDePartie);

        this.menuBarre.add(this.menuPresentation);

        this.setJMenuBar(this.menuBarre);

        this.add(this.panelBase, BorderLayout.CENTER);
        this.add(this.panelPioche, BorderLayout.EAST);
        this.add(this.panelMainJoueur, BorderLayout.SOUTH);

        //Parametre de la Frame
        Toolkit kit = Toolkit.getDefaultToolkit();

        Dimension tailleEcran = kit.getScreenSize();
        Insets scnMax = kit.getScreenInsets(getGraphicsConfiguration());
        int tailleBarTache = scnMax.bottom;

        this.setSize((int) tailleEcran.getWidth(), (int) tailleEcran.getHeight() - tailleBarTache);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    }

    public void majPioche() {
        this.panelPioche.majPiocheVehiculeVisible();
    }

    
    /** 
     * @param nom
     * @param tour
     */
    public void afficherJoueur(String nom, int tour)
    {
        if( tour < 1 ){
            JOptionPane.showMessageDialog(null, nom, "Tour de préparation" , JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, nom, "Tour" + tour, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    public void majIHM()
    {
        this.panelPlateau.majIHM();
        this.panelMainJoueur.majIHM();
        this.panelJoueurs.majPanelJoueur();
    }

    
    /** 
     * @return PanelPioche
     */
    public PanelPioche getPanelPioche() {
        return this.panelPioche;
    }

    public void desactiver(){
        this.setEnabled(false);
    }

    public void activer(){
        this.setEnabled(true);
    }

    public void griserComposants(){
        this.panelBase.setEnabled(false);
        this.panelJoueurs.setInutilisable();
        this.panelPlateau.setInutilisable();
        this.panelMainJoueur.setInutilisable();
    }

    public void activerComposants(){
        this.panelBase.setEnabled(true);
        this.panelJoueurs.setUtilisable();
        this.panelPlateau.setUtilisable();
        this.panelMainJoueur.setUtilisable();
    }

    public void resetNoeudSelect(){
        this.panelPlateau.resetNoeudSelect();
    }

    
    /** 
     * @param msg
     */
    public void afficherMsgErreur(String msg){
        JOptionPane.showMessageDialog(null, msg, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    
    /** 
     * @param msg
     */
    public void afficherMsgInfo(String msg){
        JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    
    /** 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.menuRegles){
            try{
                Desktop.getDesktop().open(reglePDF);
            }catch(Exception erreur){erreur.printStackTrace();}
        }
        if(e.getSource() == this.menuFinDePartie)
            this.ctrl.getMetier().finDePartie();
    }
    
}
