package src.ihm;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.Controleur;
import src.metier.Noeud;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class FramePrincipale extends JFrame implements ActionListener
{
    private JPanel panelFormulaire;

    private PanelPlateau panelPlateau;

    private PanelAretes panelArrete;

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

        this.panelFormulaire = new JPanel(new CardLayout());
        this.panelPlateau = new PanelPlateau(this.ctrl);
        this.panelArrete = new PanelAretes(this.ctrl);
        this.panelListeObjectif = new PanelListeObjectif(this.ctrl);
        this.panelRegleJeu = new PanelRegleJeu(this.ctrl);
        this.panelResume = new PanelResume(this.ctrl);

        this.setTitle("Concepteur de Plateau");
        this.setResizable(false);
        this.setUndecorated(false);

        this.imgLogo = kit.getImage("/../data/images/logo.png") ;
		this.setIconImage(imgLogo);

        this.menuBarre = new JMenuBar();

        this.menuFichier = new JMenu ("Fichier");
        this.menuNouveau = new JMenuItem("Nouveau");
        this.menuOuvrir = new JMenuItem("Ouvrir");

        this.menuAide = new JMenu("Aide");
        this.menuRegles = new JMenuItem("Regles");

        this.menuRegles.setIcon( new ImageIcon( "../data/images/Regles.png") );
        
        this.panelFormulaire.add(panelRegleJeu,"panelRegleJeu");
        this.panelFormulaire.add(panelArrete,"panelArrete");
        this.panelFormulaire.add(panelListeObjectif,"panelListeObjectif");
        this.panelFormulaire.add(panelResume,"panelResume");


        this.menuFichier.add(menuNouveau);
        this.menuFichier.add(menuOuvrir);

        this.menuAide.add(menuRegles);

        this.menuBarre.add(menuFichier);
        this.menuBarre.add(menuAide);

        this.add(this.panelPlateau,BorderLayout.CENTER);
        this.add(this.panelFormulaire,BorderLayout.EAST);


        this.setJMenuBar(menuBarre);

        this.reglePDF = new File("../data/PDF/Regles.pdf");

        this.menuRegles.addActionListener(this::actionPerformed);
        this.menuNouveau.addActionListener(this::actionPerformed);
        this.menuOuvrir.addActionListener(this::actionPerformed);


        this.pack();
		this.setDefaultLookAndFeelDecorated(true);
		this.setExtendedState(this.MAXIMIZED_BOTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);


    }

    public void changePanel(String nomPanel){
        this.card = (CardLayout) this.panelFormulaire.getLayout();
        this.card.show(this.panelFormulaire,nomPanel);
    }

    public void majIHM() { 
        this.panelPlateau.repaint(); 
        this.panelListeObjectif.setTableNoeud(this.ctrl.lstNoeudXMLtoIHM());

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
				JFileChooser nouveau = new JFileChooser(".");
				
				int res = nouveau.showOpenDialog(this);
			}catch(Exception erreur){erreur.printStackTrace();}
		}
        if(e.getSource() == this.menuOuvrir){
            try{
				System.out.println("Ouvrir");
				JFileChooser chooser = new JFileChooser(".");
				
				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION)
					this.ctrl.setFichierPlateau(chooser.getSelectedFile().getPath());

            }catch(Exception erreur){erreur.printStackTrace();}
        }
    }

    public ArrayList<Noeud> getNoeud() {
        return this.panelRegleJeu.getNoeud();
    }


}

