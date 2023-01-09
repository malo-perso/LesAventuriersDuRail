package src.ihm;

import src.Controleur;


import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

public class PanelJeu extends JPanel implements ActionListener {
    
    private Controleur ctrl;

    private JButton btnLocal;
    private JButton btnHebergerOnline;
    private JButton btnRejoindreOnline;
    private JButton btnRetour;
    
    public PanelJeu (Controleur ctrl) {
        this.ctrl = ctrl;
        this.setLayout(new GridLayout(6, 1, 25, 25));

        this.btnLocal = new JButton("Local");
        this.btnHebergerOnline = new JButton("Heberger en ligne");
        this.btnRejoindreOnline = new JButton("Rejoindre en ligne");
        this.btnRetour = new JButton("Retour");

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnLocal);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnHebergerOnline);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnRejoindreOnline);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(this.btnRetour);

        this.btnLocal.addActionListener(this);
        this.btnHebergerOnline.addActionListener(this);
        this.btnRejoindreOnline.addActionListener(this);
        this.btnRetour.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnLocal ) {
            try{
				JFileChooser chooser = new JFileChooser("./src/data/mappe/");

                FileFilter filtre = new FileNameExtensionFilter("XML (*.xml)", "xml");
                chooser.setFileFilter(filtre);
                chooser.setAcceptAllFileFilterUsed(false);
				
				int res = chooser.showOpenDialog(this);
				if (res == JFileChooser.APPROVE_OPTION)
                {
					this.ctrl.getMetier().lireXML(chooser.getSelectedFile());
                    //this.changePanel();
                }

            }catch(Exception erreur){erreur.printStackTrace();}
            this.ctrl.getIHMAcceuil().changePanel("panelCreerJoueur");

        }
        else if ( e.getSource() == this.btnHebergerOnline ) {
            System.out.println("Heberger en ligne");
        }
        else if ( e.getSource() == this.btnRejoindreOnline ) {
            
            String codeServ = JOptionPane.showInputDialog(this,
                    "Code de connexion", null);
                    
            System.out.println("Rejoindre le serv :" + codeServ);
        }
        else if ( e.getSource() == this.btnRetour ) {
            System.out.println("Retour");
            this.ctrl.getIHMAcceuil().changePanel("panelAcceuil");
        }
    }
}
