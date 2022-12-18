package src.ihm;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.Controleur;
import src.metier.Noeud;
import src.metier.Arete;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FramePrincipale extends JFrame implements ActionListener
{
    private JPanel panelFormulaire;

    private PanelPlateau panelPlateau;

    private PanelVehicule panelVehicule;

    private PanelAretes panelArete;

    private PanelListeObjectif panelListeObjectif;

    private PanelRegleJeu panelRegleJeu;

    private PanelResume panelResume;

	private JMenuBar menuBarre;

    private JMenu menuFichier;

    private JMenu menuAide;

    private JMenuItem menuNouveau;

    private JMenuItem menuOuvrir;

    private JMenuItem menuRegles;

    CardLayout card;

    private File reglePDF;

    private Image imgLogo;
    
    private Controleur ctrl;


	public FramePrincipale(Controleur ctrl){

        this.ctrl = ctrl;
        Toolkit kit = Toolkit.getDefaultToolkit();

        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int tailleBarTache = scnMax.bottom;

        this.panelFormulaire = new JPanel(new CardLayout());
        this.panelPlateau = new PanelPlateau(this.ctrl);
        this.panelVehicule = new PanelVehicule(this.ctrl);
        this.panelArete = new PanelAretes(this.ctrl);
        this.panelListeObjectif = new PanelListeObjectif(this.ctrl);
        this.panelRegleJeu = new PanelRegleJeu(this.ctrl);
        this.panelResume = new PanelResume(this.ctrl);

        this.setTitle("Concepteur de Plateau");
        this.setResizable(false);
        this.setUndecorated(false);

        this.imgLogo = kit.getImage(this.getClass().getResource("../data/images/logo.png")) ;
		this.setIconImage(imgLogo);

        this.menuBarre = new JMenuBar();

        this.menuFichier = new JMenu ("Fichier");
        this.menuNouveau = new JMenuItem("Nouveau");
        this.menuOuvrir = new JMenuItem("Ouvrir");

        this.menuAide = new JMenu("Aide");
        this.menuRegles = new JMenuItem("Regles");

        this.menuRegles.setIcon(new ImageIcon(this.getClass().getResource("../data/images/Regles.png")));
        
        this.panelFormulaire.add(this.panelRegleJeu,"panelRegleJeu");
        this.panelFormulaire.add(this.panelVehicule,"panelVehicule");
        this.panelFormulaire.add(this.panelArete,"panelArrete");
        this.panelFormulaire.add(this.panelListeObjectif,"panelListeObjectif");
        this.panelFormulaire.add(this.panelResume,"panelResume");


        this.menuFichier.add(this.menuNouveau);
        this.menuFichier.add(this.menuOuvrir);

        this.menuAide.add(this.menuRegles);

        this.menuBarre.add(this.menuFichier);
        this.menuBarre.add(this.menuAide);

        
        this.add(this.panelPlateau,BorderLayout.CENTER);
        this.add(this.panelFormulaire,BorderLayout.EAST);


        this.setJMenuBar(this.menuBarre);

        this.reglePDF = new File(this.getClass().getResource("../data/PDF/Regles.pdf").getFile());

        //Ajout des listener
        this.menuRegles.addActionListener(this::actionPerformed);
        this.menuNouveau.addActionListener(this::actionPerformed);
        this.menuOuvrir.addActionListener(this::actionPerformed);


        this.pack();
		//this.setDefaultLookAndFeelDecorated(true);

        this.setSize(tailleEcran.width,tailleEcran.height - tailleBarTache);

		//this.setExtendedState(this.MAXIMIZED_BOTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);


    }

    /*****************/
    /*     NOEUD     */
    /*****************/

    public void majNoeud()
    {
        this.panelRegleJeu.maJTable(this.ctrl.getLstNoeuds());
        this.panelArete.majNoeud(this.ctrl.getLstNoeuds());
    }


    public void ajouterNoeud(String nom, int x, int y, int nomX, int nomY) {
        this.panelRegleJeu.maJTable(this.ctrl.getLstNoeuds());
        this.panelArete.majNoeud(this.ctrl.getLstNoeuds());
    }

    public void setPositionNoeud(Noeud noeud, int x, int y, int nomX, int nomY) {
        this.panelPlateau.majIHM();
        this.panelArete.majNoeud(this.ctrl.getLstNoeuds());
    }
    
    /*****************/
    /*     Arete     */
    /*****************/
    
    public void majArete()
    {   
        this.panelArete.majArete(this.ctrl.getLstAretes());
    }

    /*****************/
    /*    CarteObj   */
    /*****************/

    public void majCarteObjectif()
    {   
        //TO DO
    }

    //Permet de changer de panel 
    //nomPanel : nom du panel a afficher
    public void changePanel(String nomPanel){
        this.card = (CardLayout) this.panelFormulaire.getLayout();
        this.card.show(this.panelFormulaire,nomPanel);
    }


    /*****************/
    /*     utiles    */
    /*****************/

    public void majIHM() {
        this.panelPlateau.majIHM();
        this.panelPlateau.repaint(); 
        this.panelRegleJeu.maJTable(this.ctrl.getLstNoeuds());
        this.panelListeObjectif.majTableNoeud(this.ctrl.getLstNoeuds());
    }

    
    public void actionPerformed( ActionEvent e){

        if(e.getSource() == this.menuRegles){

            try{
                System.out.println("Aide");
                Desktop.getDesktop().open(reglePDF);
            }catch(Exception erreur){erreur.printStackTrace();}
        }
		if(e.getSource() == this.menuNouveau){
			try{
				System.out.println("Nouveau");
				JFileChooser chooser = new JFileChooser(".");
				
				int res = chooser.showOpenDialog(this);
                if(res == JFileChooser.APPROVE_OPTION)
                    this.ctrl.setFichierPlateau(chooser.getSelectedFile().getPath());
			}catch(Exception erreur){erreur.printStackTrace();}
		}
        if(e.getSource() == this.menuOuvrir){
            try{
				System.out.println("Ouvrir");
				JFileChooser chooser = new JFileChooser(".");
				
				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION)
					this.ctrl.lireXML(chooser.getSelectedFile());

            }catch(Exception erreur){erreur.printStackTrace();}
        }
    }

}
