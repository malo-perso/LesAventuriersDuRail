package src.ihm;

import src.Controleur;
import src.metier.CarteObjectif;
import src.metier.CarteVehicule;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.AttributeSet.FontAttribute;

import src.Controleur;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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

        this.lstCarteObjectif = new ArrayList<CarteObjectif>(this.ctrl.getMetier().getJoueurCourant().getCartesObjectif());
        this.lstCarteVehicule = new ArrayList<CarteVehicule>(this.ctrl.getMetier().getJoueurCourant().getCartesVehicule());

        this.btnCarteVehicule = new JButton[this.lstCarteVehicule.size()];
        this.btnCarteObjectif = new JButton[this.lstCarteObjectif.size()];
        this.panelOrganisation = new JPanel(new BorderLayout());
        this.panelCarteObjectif = new JPanel();
        this.panelInfo = new JPanel(new GridLayout(2,2));
        this.panelCarteCoul = new JPanel();


        this.imgPara = new ImageIcon("./src/data/images/Engrennage.jpg");
        this.imgObjectif = new ImageIcon("./src/data/images/map.jpg");
        this.imgPlateau = new ImageIcon(this.ctrl.getImagePlateau());
        this.img2 = imgPlateau.getImage().getScaledInstance(280, 180, Image.SCALE_SMOOTH);

        Image img1 = imgPara.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        Collections.sort(this.lstCarteVehicule);

        
        if(this.lstCarteVehicule.size()<10){
            this.panelCarteCoul.setLayout(new GridLayout(1,10,5,0));

            for(int i=0; i<this.lstCarteVehicule.size(); i++){
                this.btnCarteVehicule[i] = new JButton();
                this.btnCarteVehicule[i].setBackground(this.lstCarteVehicule.get(i).getType().getColor());
                this.btnCarteVehicule[i].setPreferredSize(new Dimension(100, 50));
                this.panelCarteCoul.add(this.btnCarteVehicule[i]);
            }
            for(int i=this.lstCarteVehicule.size(); i<10;i++){
                this.panelCarteCoul.add(new JLabel());
            }
        }else{
            this.panelCarteCoul.setLayout(new GridLayout(1,this.lstCarteVehicule.size()-1,5,0));

            for(int i=0; i<this.lstCarteVehicule.size(); i++){
                this.btnCarteVehicule[i] = new JButton();
                this.btnCarteVehicule[i].setBackground(this.lstCarteVehicule.get(i).getType().getColor());
                this.btnCarteVehicule[i].setPreferredSize(new Dimension(100, 50));
                this.panelCarteCoul.add(this.btnCarteVehicule[i]);
            }
        }
        if(this.lstCarteObjectif.size()!=0){
            this.panelCarteObjectif.setLayout(new GridLayout(this.lstCarteObjectif.size(),1));
            for(int i = 0; i< this.lstCarteObjectif.size(); i++){
                this.btnCarteObjectif[i] = new JButton(new ImageIcon(img2));
                this.btnCarteObjectif[i].setFont(new Font("Calibri",Font.BOLD,14));
                this.btnCarteObjectif[i].setText("De " + lstCarteObjectif.get(i).getNoeud1().getNom() + " à " +  lstCarteObjectif.get(i).getNoeud2().getNom() + " " + lstCarteObjectif.get(i).getPoints() + " points");
                this.btnCarteObjectif[i].setPreferredSize(new Dimension(280,180));
                this.btnCarteObjectif[i].setVerticalTextPosition(SwingConstants.CENTER);
                this.btnCarteObjectif[i].setHorizontalTextPosition(SwingConstants.CENTER);
                this.panelCarteObjectif.add(this.btnCarteObjectif[i]);
            }
        }
        else
            this.panelCarteObjectif.add(new JLabel("Pas de carte Objectif",SwingConstants.CENTER));

        this.scrollCarteCoul = new JScrollPane(this.panelCarteCoul);
        this.scrollCarteCoul.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollCarteCoul.getHorizontalScrollBar().setUnitIncrement(16);
        //scrollCarteObjectif 
        this.scrollCarteObjectif = new JScrollPane(this.panelCarteObjectif);
        this.scrollCarteObjectif.setPreferredSize(new Dimension(300,200));
        this.scrollCarteObjectif.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollCarteObjectif.getVerticalScrollBar().setUnitIncrement(16);

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

        //this.add(this.scrollCarteCoul);
        //this.add(this.scrollCarteObjectif);

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
        
        this.lstCarteVehicule = new ArrayList<CarteVehicule>(this.ctrl.getMetier().getJoueurCourant().getCartesVehicule());
        //System.out.println(this.ctrl.getJoueurCourant().getCartesVehicule().size());
        this.lstCarteObjectif = new ArrayList<CarteObjectif>(this.ctrl.getMetier().getJoueurCourant().getCartesObjectif());
        this.btnCarteVehicule = new JButton[this.lstCarteVehicule.size()];
        this.btnCarteObjectif = new JButton[this.lstCarteObjectif.size()];

        Collections.sort(this.lstCarteVehicule);

        this.btnPerso.setText(this.ctrl.getMetier().getJoueurCourant().getNom());
        this.lblNbVehicule.setText("Nb Vehicule : "+this.ctrl.getMetier().getJoueurCourant().getNbWagon());
        this.lblNbPoints.setText("Nb Points : "+this.ctrl.getMetier().getJoueurCourant().getPoint());

        this.panelCarteCoul.removeAll();

        if(this.lstCarteVehicule.size()<10){
            this.panelCarteCoul.setLayout(new GridLayout(1,10,5,0));

            for(int i=0; i<this.lstCarteVehicule.size(); i++){
                this.btnCarteVehicule[i] = new JButton();
                this.btnCarteVehicule[i].setBackground(this.lstCarteVehicule.get(i).getType().getColor());
                this.btnCarteVehicule[i].setPreferredSize(new Dimension(100, 50));
                this.panelCarteCoul.add(this.btnCarteVehicule[i]);
            }
            for(int i=this.lstCarteVehicule.size(); i<10;i++){
                this.panelCarteCoul.add(new JLabel());
            }
        }else{
            this.panelCarteCoul.setLayout(new GridLayout(1,this.lstCarteVehicule.size()-1,5,0));

            for(int i=0; i<this.lstCarteVehicule.size(); i++){
                this.btnCarteVehicule[i] = new JButton();
                this.btnCarteVehicule[i].setBackground(this.lstCarteVehicule.get(i).getType().getColor());
                this.btnCarteVehicule[i].setPreferredSize(new Dimension(100, 50));
                this.panelCarteCoul.add(this.btnCarteVehicule[i]);
            }
        }

        this.panelCarteObjectif.removeAll();

        if(this.lstCarteObjectif.size() != 0 ){
            this.panelCarteObjectif.setLayout(new GridLayout(this.lstCarteObjectif.size(),1));
            for(int i = 0; i< this.lstCarteObjectif.size(); i++){
                this.btnCarteObjectif[i] = new JButton(new ImageIcon(img2));
                this.btnCarteObjectif[i].setFont(new Font("Calibri",Font.BOLD,14));
                this.btnCarteObjectif[i].setText("De " + lstCarteObjectif.get(i).getNoeud1().getNom() + " à " +  lstCarteObjectif.get(i).getNoeud2().getNom() + " " + lstCarteObjectif.get(i).getPoints() + " points");
                this.btnCarteObjectif[i].setPreferredSize(new Dimension(280,180));
                this.btnCarteObjectif[i].setVerticalTextPosition(SwingConstants.CENTER);
                this.btnCarteObjectif[i].setHorizontalTextPosition(SwingConstants.CENTER);
                this.panelCarteObjectif.add(this.btnCarteObjectif[i]);
            }
        }
        else
            this.panelCarteObjectif.add(new JLabel("Pas de carte Objectif",SwingConstants.CENTER));
    }

    public void setInutilisable(){
        this.panelInfo.setEnabled(false);
        this.panelCarteObjectif.setEnabled(false);
        this.btnPerso.setEnabled(false);
        this.btnParametre.setEnabled(false);
        this.lblNbVehicule.setEnabled(false);
        this.lblNbPoints.setEnabled(false);
        // for(int i=0; i<this.btnCarteVehicule.length; i++){
        //     this.btnCarteVehicule[i].setEnabled(false);
        //     this.btnCarteVehicule[i].setBackground(this.lstCarteVehicule.get(i).getType().getColor());
        // }
    }

    public void setUtilisable(){
        this.panelInfo.setEnabled(true);
        this.panelCarteObjectif.setEnabled(true);
        this.btnPerso.setEnabled(true);
        this.btnParametre.setEnabled(true);
        this.lblNbVehicule.setEnabled(true);
        this.lblNbPoints.setEnabled(true);
        // for(int i=0; i<this.btnCarteVehicule.length; i++){
        //     this.btnCarteVehicule[i].setEnabled(true);
        // }
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