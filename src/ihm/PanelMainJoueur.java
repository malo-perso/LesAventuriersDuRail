package src.ihm;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;


public class PanelMainJoueur extends JPanel implements ActionListener {

    private JPanel panelCarteCoul;
    private JPanel panelCarteObjectif;
    private JPanel panelInfo;

    private JButton btnCarteCoul;
    private JButton btnPerso;
    private JButton btnParametre;

    private JLabel lblNbVehicule;
    private JLabel lblNbPoints;

    private JScrollPane scrollCarteCoul;
    private JScrollPane scrollCarteObjectif;

    private ImageIcon imgV;

    public PanelMainJoueur(){

        this.setSize(600,300);
        this.setLayout(new GridLayout(1,3));

        this.panelCarteCoul = new JPanel();
        this.panelCarteObjectif = new JPanel();
        this.panelInfo = new JPanel(new GridLayout(2,2));

        this.scrollCarteCoul = new JScrollPane(this.panelCarteCoul);
        this.scrollCarteObjectif = new JScrollPane(this.panelCarteObjectif);

        this.imgV = new ImageIcon("./src/data/images/wagonblanc.jpg");

        Image img1 = imgV.getImage().getScaledInstance(215, 115, Image.SCALE_SMOOTH);
        

        this.btnCarteCoul = new JButton(new ImageIcon(img1));

        this.btnPerso = new JButton("Perso");
        this.btnParametre = new JButton("Para");

        this.lblNbVehicule = new JLabel("Nb Vehicule : ");
        this.lblNbPoints = new JLabel("Nb Points : ");

        this.btnCarteCoul.setBorder(null);

        this.panelCarteCoul.add(this.btnCarteCoul);

        this.panelInfo.add(this.btnPerso);
        this.panelInfo.add(this.lblNbVehicule);
        this.panelInfo.add(this.lblNbPoints);
        this.panelInfo.add(this.btnParametre);

        this.add(this.scrollCarteCoul);
        this.add(this.scrollCarteObjectif);
        this.add(this.panelInfo);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.add(new PanelMainJoueur());
        frame.setSize(new Dimension(1200,200));
        frame.setVisible(true); 
    }
    
}
