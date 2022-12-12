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

        this.pnlNomTheme = new JPanel();
        this.pnlNomTheme.add(new JLabel("Nom de la Mappe", JLabel.LEFT));
        this.pnlNomTheme.add(new JLabel("Thème", JLabel.LEFT));
        this.add(this.pnlNomTheme, BorderLayout.WEST);

        this.pnlBoutton = new JPanel();
        this.pnlBoutton.add(this.txtMappe);
        this.pnlBoutton.add(this.btnTheme);
        this.add(this.pnlBoutton, BorderLayout.CENTER);

    }    

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnTheme) {
            System.out.println("Bouton Thème");
        }
    }
}
