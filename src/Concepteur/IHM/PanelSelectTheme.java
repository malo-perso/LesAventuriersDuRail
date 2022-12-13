package IHM;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class PanelSelectTheme extends JPanel implements ActionListener{

    private JTextField txtMappe;
    private JButton btnTheme;
    private JPanel pnlNomTheme;
    private JPanel pnlBoutton;

    public PanelSelectTheme() {
        
        this.txtMappe = new JTextField();
        this.btnTheme = new JButton("Image Europe");

        this.setLayout(new BorderLayout());

        this.pnlNomTheme = new JPanel(new GridLayout(2,1));
        this.pnlNomTheme.add(new JLabel("Nom de la Mappe", JLabel.LEFT));
        this.pnlNomTheme.add(new JLabel("Thème", JLabel.LEFT));
        this.add(this.pnlNomTheme, BorderLayout.WEST);

        this.pnlBoutton = new JPanel(new GridLayout(2,1));
        this.pnlBoutton.add(this.txtMappe);
        this.pnlBoutton.add(this.btnTheme);
        this.add(this.pnlBoutton, BorderLayout.CENTER);

        this.txtMappe.addActionListener(this);

    }    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.txtMappe) {
            System.out.println("Bouton Thème");
        }
    }
}
