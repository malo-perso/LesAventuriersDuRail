package src.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import src.metier.Joueur;
import src.Controleur;

public class PanelJoueurs extends JPanel{

    private ArrayList<FicheJoueur> alPanelJoueur;
    private JPanel panelGrilleJoueur;
    private JScrollPane sp;

    private Controleur ctrl;
    
    public PanelJoueurs(Controleur ctrl){

        //initialisation des composant
        this.ctrl = ctrl;
        this.panelGrilleJoueur = new JPanel();

        this.sp = new JScrollPane(this.panelGrilleJoueur);

        this.alPanelJoueur = new ArrayList<FicheJoueur>();
        
        for(Joueur joueur : this.ctrl.getLstJoueurs()){
            this.alPanelJoueur.add(new FicheJoueur(joueur.getNom(), joueur.getPoint(), joueur.getNbWagon(), joueur.getCartesObjectif().size(), joueur.getCartesVehicule().size())); 
        }

        //Création des layout
        this.panelGrilleJoueur.setLayout(new GridLayout(1,this.alPanelJoueur.size()-1));

        
        //Placement des éléments
        for(FicheJoueur ficheJoueur : this.alPanelJoueur){
            this.panelGrilleJoueur.add(ficheJoueur);
        }
        this.add(this.sp);

    }

    public class FicheJoueur extends JPanel{

        private JLabel lblNom;
        private JLabel lblPoint;
        private JLabel lblNbWagon;
        private JLabel lblNbCarteObjectif;
        private JLabel lblNbCarteVehicule;

        private JPanel panelInfo;
        private JPanel panelPoint;

        public FicheJoueur(String nom, int point, int nbWagon, int nbCarteObjectif, int nbCarteVehicule){
            
            this.lblNom = new JLabel(nom);
            this.lblNom.setFont(this.lblNom.getFont().deriveFont(24f));
            this.lblPoint = new JLabel( point +  " Point" + (point>1?"s":""));
            this.lblNbWagon = new JLabel( nbWagon + (nbWagon>1?" Wagons restants":" Wagon restant"));
            this.lblNbCarteObjectif = new JLabel( nbCarteObjectif + (nbCarteObjectif>1?" Cartes objectifs":" Carte objectif"));
            this.lblNbCarteVehicule = new JLabel( nbCarteVehicule + (nbCarteVehicule>1?" Cartes vehicules":" Carte vehicule"));

            this.panelInfo = new JPanel();
            this.panelPoint = new JPanel();
            //Création du layout
            this.setLayout(new BorderLayout());
            this.panelInfo.setLayout(new GridLayout(3,1));
            this.panelPoint.setLayout(new GridLayout(2,1));
            //positionnement des composant
            this.panelPoint.add(new JLabel("tete du joueur\navec sa couleur"));
            this.panelPoint.add(this.lblPoint);

            this.panelInfo.add(this.lblNbWagon);
            this.panelInfo.add(this.lblNbCarteObjectif);
            this.panelInfo.add(this.lblNbCarteVehicule);

            this.add(this.lblNom, BorderLayout.NORTH);
            this.add(this.panelPoint, BorderLayout.WEST);
            this.add(this.panelInfo, BorderLayout.CENTER);
        }
        
        public void setPoint(int point) {this.lblPoint.setText(point +  " Point" + (point>1?"s":""));}
        public void setNbWagon(int nbWagon) {this.lblNbWagon.setText(nbWagon + (nbWagon>1?" Wagons restants":" Wagon restant"));}
        public void setlNbCarteObjectif(int nbCarteObjectif) {this.lblNbCarteObjectif.setText(nbCarteObjectif + (nbCarteObjectif>1?" Cartes objectifs":" Carte objectif"));}
        public void setNbCarteVehicule(int nbCarteVehicule) {this.lblNbCarteVehicule.setText(nbCarteVehicule + (nbCarteVehicule>1?" Cartes vehicules":" Carte vehicule"));}
    }

    public void majInfoJoueur(){
        //TODO
    }
}
