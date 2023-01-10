package src.ihm;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

import java.util.List;

import src.Controleur;
import src.metier.CarteVehicule;
import src.metier.CarteObjectif;

public class PanelPioche extends JPanel implements ActionListener{
    
    private Controleur ctrl;
    private FrameAfficheCarteObjectif afficheCarteObjectif;
    private List<CarteObjectif> piocheObjectif;
    private CarteVehicule[] piocheVehiculeVisible;

    private JButton[] btnPiocheVehiculeVisible;
    private JButton btnPiocheVehicule, btnPiocheObjectif;
    

    public PanelPioche(Controleur ctrl) {
        this.ctrl = ctrl;
        this.afficheCarteObjectif = new FrameAfficheCarteObjectif(this.ctrl);
        this.piocheObjectif = ctrl.getMetier().getPioche().getLstCarteObjectif();
        this.piocheVehiculeVisible = this.ctrl.getMetier().getPioche().majPiocheVehiculeVisible();
        this.btnPiocheVehiculeVisible = new JButton[5];


        this.setLayout(new GridLayout(7, 1));

        // création des composants
        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {
            this.btnPiocheVehiculeVisible[i] = new JButton(i+"");
            //this.btnPiocheVehiculeVisible[i].setBackground(this.piocheVehiculeVisible[i].getType().getColor());
        }
        this.btnPiocheVehicule = new JButton("Pioche Vehicule");
        this.btnPiocheObjectif = new JButton("Pioche Objectif");

        // ajout des listeners
        for (int i = 0; i < this.btnPiocheVehiculeVisible.length; i++) {
            this.btnPiocheVehiculeVisible[i].addActionListener(this);
        }
        this.btnPiocheVehicule.addActionListener(this);
        this.btnPiocheObjectif.addActionListener(this);

        // ajout des composants
        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {
            this.add(this.btnPiocheVehiculeVisible[i]);
        }
        this.add(this.btnPiocheVehicule);
        this.add(this.btnPiocheObjectif);
    }


    public void majPiocheVehiculeVisible() {
        for (int i = 0; i < this.piocheVehiculeVisible.length; i++) {

            this.piocheVehiculeVisible[i] = this.ctrl.getMetier().getPioche().majPiocheVehiculeVisible()[i];

            if (this.piocheVehiculeVisible[i] != null) {
                this.btnPiocheVehiculeVisible[i].setEnabled(true);
                this.btnPiocheVehiculeVisible[i].setBackground(this.piocheVehiculeVisible[i].getType().getColor());
                this.btnPiocheVehiculeVisible[i].setText("");
            } else {
                this.btnPiocheVehiculeVisible[i].setBackground(null);
                this.btnPiocheVehiculeVisible[i].setText("il n'y a plus de cartes");
                this.btnPiocheVehiculeVisible[i].setEnabled(false);
            }
        }
    }

    
    /** 
     * @return List<CarteObjectif>
     */
    public List<CarteObjectif> getPiochCarteObjectifs(){
        return this.piocheObjectif;
    }

    public void setBtnPiocheObjectifUtilisable(){
        this.btnPiocheObjectif.setEnabled(true);
    }

    
    /** 
     * @param btn
     */
    public void setUtilisable(JButton btn){
        btn.setEnabled(true);
    }
    
    
    /** 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < this.btnPiocheVehiculeVisible.length; i++) {
            if (e.getSource() == this.btnPiocheVehiculeVisible[i]) {
                this.btnPiocheObjectif.setEnabled(false);
                this.ctrl.getIHM().griserComposants();

                this.ctrl.getMetier().piocherVehicule(i);
                this.ctrl.getIHM().setVisible(true);
            }
        }

        if (e.getSource() == this.btnPiocheVehicule) {
            this.btnPiocheObjectif.setEnabled(false);
            this.ctrl.getIHM().griserComposants();

            this.ctrl.getMetier().piocherVehicule(6);
            this.ctrl.getIHM().setVisible(true);
        }

        if (e.getSource() == this.btnPiocheObjectif) {
            switch(this.ctrl.getMetier().getLstCarteObjectifs().size()){
                case 0 : JOptionPane.showMessageDialog(null, "Vous avez déjà pioché toutes les cartes objectifs", "Erreur", JOptionPane.ERROR_MESSAGE);break;
                case 1 : JOptionPane.showMessageDialog(null, "Attention ! Il ne reste qu'une carte objectif !");
                        
                        this.ctrl.getIHM().desactiver();

                        this.ctrl.piocherObjectif();
                        this.afficheCarteObjectif.setVisible(true);
                        this.ctrl.getIHM().setVisible(true);
                        break;
                case 2 : JOptionPane.showMessageDialog(null, "Attention ! Il ne reste que deux cartes objectifs !");
                        
                        this.ctrl.getIHM().desactiver();

                        this.ctrl.piocherObjectif();
                        this.afficheCarteObjectif.setVisible(true);
                        this.ctrl.getIHM().setVisible(true);
                        break;
                default:this.ctrl.getIHM().desactiver();

                        this.ctrl.piocherObjectif();
                        this.afficheCarteObjectif.setVisible(true);
                        this.ctrl.getIHM().setVisible(true);
                        break;
            }
        }
    }



}