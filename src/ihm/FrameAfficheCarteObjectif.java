package src.ihm;

import src.Controleur;
import src.ihm.PanelPioche;
import src.metier.Metier;
import src.metier.CarteObjectif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class FrameAfficheCarteObjectif extends JFrame implements ActionListener {

    private Controleur ctrl;
    private PanelPioche panelPioche;
    private Metier metier;

    private JPanel panelBase;
    private JPanel panelObjectif;
    private JPanel panelCB;
    private JPanel panelValidation;

    private JButton btnValider;
    private JButton[] btnCarteObjectif;

    private JCheckBox cbCarte1;
    private JCheckBox cbCarte2;
    private JCheckBox cbCarte3;

    private List<CarteObjectif> lstCarteObjectifs;
    private ArrayList<CarteObjectif> lstCartePioche;
    private ArrayList<Integer> carteChoisie;
    private ImageIcon icon1;

    private Image img1;

    public FrameAfficheCarteObjectif(Controleur ctrl){
        this.ctrl = ctrl;
        this.metier = new Metier(this.ctrl);
        this.setTitle("Selection Carte Objectif");
        this.setSize(945,300);
        this.setLocationRelativeTo(null);;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.panelBase = new JPanel(new BorderLayout());
        this.panelObjectif = new JPanel(new GridLayout(1,3, 5, 0));
        this.panelCB = new JPanel(new GridLayout(1,3));
        this.panelValidation = new JPanel(new GridLayout(1,3));

        this.lstCartePioche = new ArrayList<CarteObjectif>(this.ctrl.getPioche().piocherObjectif());
        
        this.icon1 = new ImageIcon(this.ctrl.getImagePlateau());
        this.img1 = this.icon1.getImage().getScaledInstance(315,215,Image.SCALE_SMOOTH);

        this.btnCarteObjectif = new JButton[this.lstCartePioche.size()];

        for(int i= 0; i < this.lstCartePioche.size(); i++){
            this.btnCarteObjectif[i] = new JButton(new ImageIcon(img1));
            this.btnCarteObjectif[i].setText("De " + this.lstCartePioche.get(i).getNoeud1().getNom() + " à " +  this.lstCartePioche.get(i).getNoeud2().getNom() + " " + this.lstCartePioche.get(i).getPoints() + " points");
            this.btnCarteObjectif[i].setFont(new Font("Calibri",Font.BOLD,14));
            this.btnCarteObjectif[i].setVerticalTextPosition(SwingConstants.CENTER);
            this.btnCarteObjectif[i].setHorizontalTextPosition(SwingConstants.CENTER);
            this.btnCarteObjectif[i].setBorder(null);
            this.panelObjectif.add(this.btnCarteObjectif[i]);
        }

        this.btnValider = new JButton("Valider");
        
        this.cbCarte1 = new JCheckBox();
        this.cbCarte2 = new JCheckBox();
        this.cbCarte3 = new JCheckBox();

        this.cbCarte1.setHorizontalAlignment(SwingConstants.CENTER);
        this.cbCarte2.setHorizontalAlignment(SwingConstants.CENTER);
        this.cbCarte3.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.btnValider.addActionListener(this);
        this.cbCarte1.addActionListener(this);
        this.cbCarte2.addActionListener(this);
        this.cbCarte3.addActionListener(this);

        this.panelCB.add(this.cbCarte3,SwingConstants.CENTER);
        this.panelCB.add(this.cbCarte2, SwingConstants.CENTER);
        this.panelCB.add(this.cbCarte1,SwingConstants.CENTER);
       
        this.panelValidation.add(new JLabel());
        this.panelValidation.add(this.btnValider);
        this.panelValidation.add(new JLabel());
        
        this.panelBase.add(this.panelObjectif,BorderLayout.NORTH);
        this.panelBase.add(this.panelCB,BorderLayout.CENTER);
        this.panelBase.add(this.panelValidation,BorderLayout.SOUTH);
        this.add(panelBase);
    }
    
    public void clearCheckBox(){
        this.cbCarte1.setSelected(false);
        this.cbCarte2.setSelected(false);
        this.cbCarte3.setSelected(false);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g1 = (Graphics2D) g;

        double rationW = this.ctrl.getImagePlateau().getWidth(null) /315 ;
        double rationH = this.ctrl.getImagePlateau().getHeight(null)/215;

        List<CarteObjectif> lstCartePioche = this.ctrl.getPioche().piocherObjectif();

        System.out.println(rationW + " " + rationH);

        System.out.println(lstCartePioche.get(0).getNoeud1().getCenterX());
        System.out.println(lstCartePioche.get(0).getNoeud1().getCenterY());
        
        System.out.println((int)(lstCartePioche.get(0).getNoeud1().getCenterX()/rationW));
        System.out.println((int) (lstCartePioche.get(0).getNoeud1().getCenterY()/rationH));
        
        g.setColor(Color.BLACK);

        g.fillOval((int)(lstCartePioche.get(0).getNoeud1().getCenterX()/rationW),(int) (lstCartePioche.get(0).getNoeud1().getCenterY()/rationH), 10, 10);
        g.fillOval((int)(lstCartePioche.get(0).getNoeud2().getCenterX()/rationW), (int)(lstCartePioche.get(0).getNoeud2().getCenterY()/rationH), 10, 10);

        g.fillOval((int)(lstCartePioche.get(1).getNoeud1().getCenterX()/rationW+rationW),(int) (lstCartePioche.get(1).getNoeud1().getCenterY()/rationH), 10, 10);
        g.fillOval((int)(lstCartePioche.get(1).getNoeud2().getCenterX()/rationW+rationW), (int)(lstCartePioche.get(1).getNoeud2().getCenterY()/rationH), 10, 10);

        g.fillOval((int)(lstCartePioche.get(2).getNoeud1().getCenterX()/rationW+rationW*2),(int) (lstCartePioche.get(2).getNoeud1().getCenterY()/rationH), 10, 10);
        g.fillOval((int)(lstCartePioche.get(2).getNoeud2().getCenterX()/rationW+rationW*2), (int)(lstCartePioche.get(2).getNoeud2().getCenterY()/rationH), 10, 10);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.carteChoisie = new ArrayList<Integer>();
        if(e.getSource() == this.btnValider){

            if ( this.cbCarte1.isSelected()){
                carteChoisie.add(0);
            }

            if( this.cbCarte2.isSelected()){
                carteChoisie.add(1);
            }

            if( this.cbCarte3.isSelected()){
                carteChoisie.add(2);
            }

            if(this.ctrl.getNbActionJoueur()<1){
                if(this.carteChoisie.size() < 2){
                    JOptionPane.showMessageDialog(null, "Vous devez choisir au moins 2 cartes");
                }else{
                    this.dispose();
                    clearCheckBox();
                    this.ctrl.getIHM().activer();
                    this.ctrl.getIHM().setVisible(true);
                    this.ctrl.piocherObjectif(this.carteChoisie);
                }
            }else{
                if(carteChoisie.size() == 0){
                    JOptionPane.showMessageDialog(null, "Vous devez choisir une carte");
                }else{
                    this.dispose();
                    clearCheckBox();
                    this.ctrl.getIHM().activer();
                    this.ctrl.getIHM().setVisible(true);
                    this.ctrl.piocherObjectif(this.carteChoisie);    
                }
            }
        }
        
    }
    public void majIHM(){
        this.lstCartePioche = new ArrayList<CarteObjectif>(this.ctrl.getPioche().piocherObjectif());
        this.btnCarteObjectif = new JButton[this.lstCartePioche.size()];

        this.panelObjectif.removeAll();

        for(int i= 0; i < this.lstCartePioche.size(); i++){
            this.btnCarteObjectif[i] = new JButton(new ImageIcon(img1));
            this.btnCarteObjectif[i].setText("De " + this.lstCartePioche.get(i).getNoeud1().getNom() + " à " +  this.lstCartePioche.get(i).getNoeud2().getNom() + " " + this.lstCartePioche.get(i).getPoints() + " points");
            this.btnCarteObjectif[i].setFont(new Font("Calibri",Font.BOLD,14));
            this.btnCarteObjectif[i].setVerticalTextPosition(SwingConstants.CENTER);
            this.btnCarteObjectif[i].setHorizontalTextPosition(SwingConstants.CENTER);
            this.btnCarteObjectif[i].setBorder(null);
            this.panelObjectif.add(this.btnCarteObjectif[i]);
        }
        System.out.println(this.lstCartePioche);
    }

    public ArrayList<Integer> getCarteObjectifChoisie(){
        return this.carteChoisie;
    }
    
}
