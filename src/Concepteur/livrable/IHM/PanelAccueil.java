package livrable.IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAccueil extends JPanel implements ActionListener
{
    private JLabel  lblhaut,lblbas;
    private JButton btnCreerMappe;
    private JButton btnJeu;
    private JButton btnOptions;
    private Image   imgBackground;
    private FramePrincipale FP;

    public PanelAccueil(FramePrincipale FP)
    {
        // Options du panel
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(3, 3, 0, 20));

this.FP = FP;

        // Création des composants
        this.btnCreerMappe = new JButton("Créer une mappe");
        this.btnJeu        = new JButton("Jouer");
        this.btnOptions    = new JButton("Options");

        this.btnCreerMappe.setSize(100, 50);
        this.btnJeu.setSize(100, 50);
        this.btnOptions.setSize(100, 50);

		this.imgBackground = Toolkit.getDefaultToolkit().createImage("./bg.png");

        // Positionnement des composants
        this.add(new JLabel());
        this.add(this.btnCreerMappe);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(this.btnJeu);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(this.btnOptions);
        this.add(new JLabel());
        //ecoute des composants
        this.btnCreerMappe.addActionListener(this);
        this.btnJeu.addActionListener(this);
        this.btnOptions.addActionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(this.imgBackground,0,0,null);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.btnCreerMappe)
            this.FP.changerConcepteur();
    }
}