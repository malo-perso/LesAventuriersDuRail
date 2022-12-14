package src.ihm;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FramePrincipale extends JFrame implements ActionListener
{
    private JPanel panelFormulaire;

    private PanelArretes panelArrete;

    private PanelListeObjectif panelListeObjectif;

    private PanelRegleJeu panelRegleJeu;

    private PanelResume panelresume;

	private JMenuBar menuBarre;

    private JMenu menuFichier;

    private JMenu menuAide;

    private JMenuItem menuNouveau;

    private JMenuItem menuOuvrir;

    private JMenuItem menuRegles;

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;

    CardLayout card;

    private File reglePDF;

    private Image imgLogo;


	public FramePrincipale(){

        Toolkit kit = Toolkit.getDefaultToolkit();

        this.panelArrete = new PanelAretes();
        this.panelListeObjectif = new PanelListeObjectif();
        this.panelRegleJeu = new PanelRegleJeu();
        this.panelResume = new PanelResume();

        this.setTitle("Concepteur de Plateau");
        this.setResizable(false);
        this.setUndecorated(false);

        this.imgLogo = kit.getImage("../data/images/logo.png") ;
		this.setIconImage(imgLogo);

        this.menuBarre = new JMenuBar();

        this.menuFichier = new JMenu ("Fichier");
        this.menuNouveau = new JMenuItem("Nouveau");
        this.menuOuvrir = new JMenuItem("Ouvrir");

        this.menuAide = new JMenu("Aide");
        this.menuRegles = new JMenuItem("Regles");

        this.menuRegles.setIcon( new ImageIcon( "../data/images/Regles.png"   ) );
        
        this.card.add(panelArrete);
        this.card.add(panelListeObjectif);
        this.card.add(panelRegleJeu);
        this.card.add(panelResume);


        this.menuFichier.add(menuNouveau);
        this.menuFichier.add(menuOuvrir);

        this.menuAide.add(menuRegles);

        this.menuBarre.add(menuFichier);
        this.menuBarre.add(menuAide);

        this.add(card,BorderLayout.EAST);


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
				JFileChooser ouvrir = new JFileChooser(".");
				
				int res = ouvrir.showOpenDialog(this);
				/*if (res == JFileChooser.APPROVE_OPTION && chooser.getSelectedFile().getPath() != null)
					this.ctrl.setFichierPlateau(new File(chooser.getSelectedFile().getPath()));*/

            }catch(Exception erreur){erreur.printStackTrace();}
        }
    }

    public static void main(String[] arg){new FramePrincipale();}

}

