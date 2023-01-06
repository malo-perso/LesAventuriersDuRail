package src.ihm;

import src.Controleur;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.AttributeSet.FontAttribute;

import src.Controleur;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private ArrayList<CarteObjectif> lstCarteObjectif;
    private ArrayList<Integer> carteObjectifChoisie;
    private CarteVehicule[] carteVehiculeMainJoueur;
    private JButton[] btnCarteVehicule;
    private JButton[] btnCarteObjectif;

    private ImageIcon imgPara,imgObjectif,imgPlateau;;

    private Image img2;

    public PanelMainJoueur(Controleur ctrl){
        this.ctrl = ctrl;

        this.setPreferredSize(new Dimension(600,130));
        //this.setLayout(new GridLayout(1,3));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);

        this.lstCarteObjectif = new ArrayList<CarteObjectif>(this.ctrl.getPioche().piocherObjectif());
        this.lstCarteVehicule = this.ctrl.getPioche().getLstCartesVehicule();
        this.carteObjectifChoisie = new ArrayList<Integer>(3);
        this.carteObjectifChoisie.add(0);
        this.carteObjectifChoisie.add(2);
        this.carteObjectifChoisie.add(1);

        this.btnCarteVehicule = new JButton[this.lstCarteVehicule.size()];
        this.btnCarteObjectif = new JButton[this.lstCarteObjectif.size()];
        this.panelOrganisation = new JPanel(new BorderLayout());
        this.panelCarteCoul = new JPanel();
        this.panelCarteObjectif = new JPanel();
        this.panelInfo = new JPanel(new GridLayout(2,2));

        this.imgPara = new ImageIcon("./src/data/images/Engrennage.jpg");
        this.imgObjectif = new ImageIcon("./src/data/images/map.jpg");
        this.imgPlateau = new ImageIcon(this.ctrl.getImagePlateau());
        this.img2 = imgPlateau.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);

        Image img1 = imgPara.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        for(int i=0; i<this.lstCarteVehicule.size(); i++){
            this.btnCarteVehicule[i] = new JButton("btn"+i);
            //this.btnCarteVehicule[i].setBackground(this.carteVehiculeJoueur.getType().getColor());
            this.panelCarteCoul.add(this.btnCarteVehicule[i]);
        }
        if(this.carteObjectifChoisie != null){
            this.panelCarteObjectif.setLayout(new GridLayout(this.carteObjectifChoisie.size(),1));
            for(int i = 0; i< this.carteObjectifChoisie.size(); i++){
                this.btnCarteObjectif[i] = new JButton(new ImageIcon(img2));
                this.btnCarteObjectif[i].setFont(new Font("Calibri",Font.BOLD,14));
                switch(this.carteObjectifChoisie.get(i)){
                    case 0 : this.btnCarteObjectif[i].setText("De " + lstCarteObjectif.get(0).getNoeud1().getNom() + " à " +  lstCarteObjectif.get(0).getNoeud2().getNom() + " " + lstCarteObjectif.get(0).getPoints() + " points");
                             break;
                    case 1 : this.btnCarteObjectif[i].setText("De " + lstCarteObjectif.get(1).getNoeud1().getNom() + " à " +  lstCarteObjectif.get(1).getNoeud2().getNom() + " " + lstCarteObjectif.get(1).getPoints() + " points");
                             break;
                    case 2 : this.btnCarteObjectif[i].setText("De " + lstCarteObjectif.get(2).getNoeud1().getNom() + " à " +  lstCarteObjectif.get(2).getNoeud2().getNom() + " " + lstCarteObjectif.get(2).getPoints() + " points");
                             break;
                }
                this.btnCarteObjectif[i].setPreferredSize(new Dimension(300,200));
                this.btnCarteObjectif[i].setVerticalTextPosition(SwingConstants.CENTER);
                this.btnCarteObjectif[i].setHorizontalTextPosition(SwingConstants.CENTER);
                this.panelCarteObjectif.add(this.btnCarteObjectif[i]);
            }
        }
        else
            this.panelCarteObjectif.add(new JLabel("Pas de carte Objectif",SwingConstants.CENTER));

        this.scrollCarteCoul = new JScrollPane(this.panelCarteCoul);
        //scrollCarteObjectif 
        this.scrollCarteObjectif = new JScrollPane(this.panelCarteObjectif);      

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

    
    public void majIHM() {
        this.btnPerso.setText(this.ctrl.getJoueurCourant().getNom());
        this.lblNbVehicule.setText("Nb Vehicule : "+this.ctrl.getJoueurCourant().getNbWagon());
        this.lblNbPoints.setText("Nb Points : "+this.ctrl.getJoueurCourant().getPoint());

        System.out.println(this.carteObjectifChoisie);
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
