package src.ihm;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class PanelAccueil extends JPanel implements ActionListener
{
    private JLabel  lblhaut,lblbas;
    private JButton btnCreerMappe;
    private JButton btnJeu;
    private JButton btnOptions;
    private Image   imgBackground;

    public PanelAccueil() 
    {
        MediaTracker mt = new MediaTracker(this);
        this.imgBackground = Toolkit.getDefaultToolkit().getImage("bgF.png");
        mt.addImage(this.imgBackground, 0);
        try {
            mt.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Options du panel
        this.setLayout(new GridLayout(7, 3, 0, 20));
        // Création des composants
        this.btnCreerMappe = new JButton("Créer une mappe");
        this.btnJeu        = new JButton("Jouer");
        this.btnOptions    = new JButton("Options");

        this.btnCreerMappe.setSize(150, 75);
        this.btnJeu.setSize(150, 75);
        this.btnOptions.setSize(150, 75);

		/*ImageIcon background = new ImageIcon(this.getClass().getResource("/bgF.png"));
		JLabel lblBackground = new JLabel(background);
		lblBackground.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());*/

        // Positionnement des composants
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(this.btnCreerMappe);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(this.btnJeu);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(this.btnOptions);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        
        //ecoute des composants
        
        this.btnCreerMappe.addActionListener(this);
        this.btnJeu.addActionListener(this);
        this.btnOptions.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if ( e.getSource() == this.btnCreerMappe ) {
            System.out.println("Créer une mappe");
        }
        else if ( e.getSource() == this.btnJeu ) {
            System.out.println("Jouer");
        }
        else if ( e.getSource() == this.btnOptions ) {
            System.out.println("Options");
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.imgBackground, 0, 0,this.getWidth(),this.getHeight(),  null);
    }

}