package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.metier.Joueur;
import src.Controleur;

public class PanelJoueurs extends JPanel{

    private ArrayList<FicheJoueur> alPanelJoueur;
    private JPanel panelGrilleJoueur;
    private JPanel panelSp;

    private Controleur ctrl;
    
    public PanelJoueurs(Controleur ctrl){

        //initialisation des composant
        this.ctrl = ctrl;
        this.panelGrilleJoueur = new JPanel();
        this.panelSp = new JPanel();
        
        this.alPanelJoueur = new ArrayList<FicheJoueur>();
        
        for(Joueur joueur : this.ctrl.getLstJoueurs()){
            this.alPanelJoueur.add(new FicheJoueur(joueur)); 
        }

        //Création des layout et placement des éléments
        this.setLayout(new BorderLayout());
        this.panelSp.setLayout(new BorderLayout());
        this.panelGrilleJoueur.setBackground(Color.WHITE);

        if ( this.alPanelJoueur.size()-1 < 10){
            this.panelGrilleJoueur.setLayout(new GridLayout(1,10, 4, 0 ));

            //Remplissage du panelGrilleJoueur
            for(FicheJoueur ficheJoueur : this.alPanelJoueur){
                this.panelGrilleJoueur.add(ficheJoueur);
            }
            for(int i=this.alPanelJoueur.size()-1; i<9; i++){
                this.panelGrilleJoueur.add(new JLabel());
            }

        }else{
            this.panelGrilleJoueur.setLayout(new GridLayout(1,this.alPanelJoueur.size()-1, 4, 0));
            
            //Remplissage du panelGrilleJoueur
            for(FicheJoueur ficheJoueur : this.alPanelJoueur){
                this.panelGrilleJoueur.add(ficheJoueur);
            }
        }
        
        this.panelSp.add(this.panelGrilleJoueur);

        JScrollPane sp = new JScrollPane(this.panelSp);

        //sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(sp, BorderLayout.CENTER);

    }

    public class FicheJoueur extends JPanel{

        private JLabel lblNom;
        private JLabel lblPoint;
        private JLabel lblNbWagon;
        private JLabel lblNbCarteObjectif;
        private JLabel lblNbCarteVehicule;

        private JPanel panelInfo;
        private JPanel panelPoint;

        public FicheJoueur(Joueur joueur){
            String nom = joueur.getNom();
            int point = joueur.getPoint();
            int nbWagon = joueur.getNbWagon();
            int nbCarteObjectif = joueur.getCartesObjectif().size();
            int nbCarteVehicule = joueur.getCartesVehicule().size();
            Color couleur = joueur.getCouleur();

            this.lblNom = new JLabel(nom, SwingConstants.CENTER);
            this.lblNom.setFont(this.lblNom.getFont().deriveFont(20f));
            this.lblPoint = new JLabel( point +  " Point" + (point>1?"s":""));
            this.lblNbWagon = new JLabel( nbWagon + (nbWagon>1?" Wagons restants":" Wagon restant"));
            this.lblNbCarteObjectif = new JLabel( nbCarteObjectif + (nbCarteObjectif>1?" Cartes objectifs":" Carte objectif"));
            this.lblNbCarteVehicule = new JLabel( nbCarteVehicule + (nbCarteVehicule>1?" Cartes vehicules":" Carte vehicule"));

            this.panelInfo = new JPanel();
            this.panelPoint = new JPanel();

            this.setOpaque(false);
            this.panelInfo.setOpaque(false);
            this.panelPoint.setOpaque(false);

            this.setBorder(BorderFactory.createMatteBorder(2, 2,2,2,couleur));
            
            //Création du layout
            this.setLayout(new BorderLayout());
            this.panelInfo.setLayout(new GridLayout(3,1));
            this.panelPoint.setLayout(new GridLayout(2,1));
            //positionnement des composant
            
            
            //this.panelPoint.add();
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

    public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		
		g.setColor(Color.BLACK);
        g.drawOval(150, 150, 15, 15);

	}
}
