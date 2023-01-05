package src.ihm;

import src.Controleur;
import src.metier.CarteVehicule;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import src.Controleur;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.List;

public class PanelMainJoueur extends JPanel implements ActionListener {

    private Controleur ctrl;

    private JPanel panelCarteCoul;
    private JPanel panelCarteObjectif;
    private JPanel panelOrganisation;
    private JPanel panelInfo;
    private JButton btnPerso;
    private JButton btnParametre;

    private JLabel lblNbVehicule;
    private JLabel lblNbPoints;

    private JScrollPane scrollCarteCoul;
    private JScrollPane scrollCarteObjectif;


    private List<CarteVehicule> lstCarteVehicule;
    private CarteVehicule[] carteVehiculeMainJoueur;
    private JButton[] btnCarteVehicule;

    private ImageIcon imgV;

    public PanelMainJoueur(Controleur ctrl){

        this.setSize(600,300);
        //this.setLayout(new GridLayout(1,3));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);

        this.lstCarteVehicule = ctrl.getPioche().getLstCartesVehicule();

        this.btnCarteVehicule = new JButton[this.lstCarteVehicule.size()];
        this.panelOrganisation = new JPanel(new BorderLayout());
        this.panelCarteCoul = new JPanel();
        this.panelCarteObjectif = new JPanel();
        this.panelInfo = new JPanel(new GridLayout(2,2));

        for(int i=0; i<this.lstCarteVehicule.size(); i++){
            this.btnCarteVehicule[i] = new JButton("btn"+i);
            //this.btnCarteCoul[i].setBackground(this.carteVehiculeJoueur.getType().getColor());
            this.panelCarteCoul.add(this.btnCarteVehicule[i]);
        }

        this.panelCarteObjectif.add(new JButton("btnObjectif"));
        this.panelCarteObjectif.add(new JButton("btnObjectif"));

        this.scrollCarteCoul = new JScrollPane(this.panelCarteCoul);
        //scrollCarteObjectif 
        this.scrollCarteObjectif = new JScrollPane(this.panelCarteObjectif);


        this.imgV = new ImageIcon("./src/data/images/Engrennage.jpg");

        Image img1 = imgV.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        

        this.btnPerso = new JButton("MisterConfiture");
        this.btnParametre = new JButton(new ImageIcon(img1));

        this.btnParametre.setBorder(null);

        this.lblNbVehicule = new JLabel("Nb Vehicule : 70");
        this.lblNbPoints = new JLabel("Nb Points : 200");

        this.btnParametre.addActionListener(this);

        

        this.panelInfo.add(this.btnPerso);
        this.panelInfo.add(this.lblNbVehicule);
        this.panelInfo.add(this.lblNbPoints);
        this.panelInfo.add(this.btnParametre);

        this.add(this.scrollCarteCoul);
        this.add(this.scrollCarteObjectif);

        this.panelOrganisation.add(this.scrollCarteCoul, BorderLayout.CENTER);
        this.panelOrganisation.add(this.scrollCarteObjectif, BorderLayout.EAST);

        this.add(this.panelOrganisation, BorderLayout.CENTER);
        this.add(this.panelInfo, BorderLayout.EAST);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    /*
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.add(new PanelMainJoueur());
        frame.setSize(new Dimension(1200,200));
        frame.setVisible(true); 
    }
     */
    
}
