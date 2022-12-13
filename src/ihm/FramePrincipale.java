package ihm;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FramePrincipale extends JFrame implements ActionListener
{
	private JMenuBar menuBarre;

    private JMenu menuFichier;

    private JMenu menuAide;

    private JMenuItem menuNouveau;

    private JMenuItem menuOuvrir;

    private JMenuItem menuRegles;

    private File reglePDF;

    private Image imgLogo;


	public FramePrincipale(){

        Toolkit kit = Toolkit.getDefaultToolkit();

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
        
        this.menuFichier.add(menuNouveau);
        this.menuFichier.add(menuOuvrir);

        this.menuAide.add(menuRegles);

        this.menuBarre.add(menuFichier);
        this.menuBarre.add(menuAide);

        this.setJMenuBar(menuBarre);

        this.reglePDF = new File("../data/PDF/Regles.pdf");

        this.menuRegles.addActionListener(this::actionPerformed);
        this.menuFichier.addActionListener(this::actionPerformed);
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
        /*if(e.getSource() == this.menuNouveau){
            try{

            }catch(){}
        }*/
    }

    public static void main(String[] arg){new FramePrincipale();}


}

