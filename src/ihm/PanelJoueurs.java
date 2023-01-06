package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

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
            this.panelGrilleJoueur.setLayout(new GridLayout(1,9, 4, 0 ));

            //Remplissage du panelGrilleJoueur
            for(FicheJoueur ficheJoueur : this.alPanelJoueur){
                this.panelGrilleJoueur.add(ficheJoueur);
            }
            for(int i=this.alPanelJoueur.size()-1; i<8; i++){
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

        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(sp, BorderLayout.CENTER);

    }

    private class FicheJoueur extends JPanel{

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
            this.lblPoint = new JLabel( String.format("%3d",point) +  " Point" + (point>1?"s":" "));
            this.lblNbWagon = new JLabel( String.format("%3d",nbWagon) + (nbWagon>1?" Wagons restants ":" Wagon restant   "));
            this.lblNbCarteObjectif = new JLabel( String.format("%3d",nbCarteObjectif) + (nbCarteObjectif>1?" Cartes objectifs":" Carte objectif  "));
            this.lblNbCarteVehicule = new JLabel( String.format("%3d",nbCarteVehicule) + (nbCarteVehicule>1?" Cartes vehicules":" Carte vehicule  "));

            this.panelInfo = new JPanel();
            this.panelPoint = new JPanel();

            this.setOpaque(false);
            this.panelInfo.setOpaque(false);
            this.panelPoint.setOpaque(false);

            this.setBorder(BorderFactory.createMatteBorder(2, 2,2,2,couleur));
            
            //Création du layout
            this.setLayout(new BorderLayout());
            this.panelInfo.setLayout(new GridLayout(3,1, 0 , 8));
            this.panelPoint.setLayout(new BorderLayout());
            //positionnement des composant
            
            try {
                File file = new File("src/data/images/Joueur.png");
                
                BufferedImage img = PanelJoueurs.replace(ImageIO.read(file), Color.WHITE.getRGB(), couleur.getRGB());
                this.panelPoint.add(new JLabel(new ImageIcon(img.getScaledInstance(45, 45, Image.SCALE_SMOOTH))), BorderLayout.CENTER);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.panelPoint.add(this.lblPoint, BorderLayout.SOUTH);

            this.panelInfo.add(this.lblNbWagon);
            this.panelInfo.add(this.lblNbCarteObjectif);
            this.panelInfo.add(this.lblNbCarteVehicule);

            this.add(this.lblNom, BorderLayout.NORTH);
            this.add(this.panelPoint, BorderLayout.WEST);
            this.add(this.panelInfo, BorderLayout.CENTER);
        }
            
        public void setPoint(int point) {this.lblPoint.setText(String.format("%3d",point) +  " Point" + (point>1?"s":" "));}
        public void setNbWagon(int nbWagon) {this.lblNbWagon.setText(String.format("%3d",nbWagon) + (nbWagon>1?" Wagons restants":" Wagon restant  "));}
        public void setlNbCarteObjectif(int nbCarteObjectif) {this.lblNbCarteObjectif.setText(String.format("%3d",nbCarteObjectif) + (nbCarteObjectif>1?" Cartes objectifs":" Carte objectif  "));}
        public void setNbCarteVehicule(int nbCarteVehicule) {this.lblNbCarteVehicule.setText(String.format("%3d",nbCarteVehicule) + (nbCarteVehicule>1?" Cartes vehicules":" Carte vehicule  "));}
    }

    public void majPanelJoueur()
    {
        for(int cpt = 0; cpt < this.alPanelJoueur.size(); cpt++){
            this.alPanelJoueur.get(cpt).setPoint(this.ctrl.getLstJoueurs().get(cpt).getPoint());
            this.alPanelJoueur.get(cpt).setNbWagon(this.ctrl.getLstJoueurs().get(cpt).getNbWagon());
            this.alPanelJoueur.get(cpt).setlNbCarteObjectif(this.ctrl.getLstJoueurs().get(cpt).getCartesObjectif().size());
            this.alPanelJoueur.get(cpt).setNbCarteVehicule(this.ctrl.getLstJoueurs().get(cpt).getCartesVehicule().size());
        }
    }

    public static BufferedImage replace(BufferedImage image, int target, int preferred) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        int color;
    
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                color = image.getRGB(i, j);
                if (color == target) {
                    newImage.setRGB(i, j, preferred);
                }
                else {
                    newImage.setRGB(i, j, color);
                }
            }
        }
    
        return newImage;
    }


    public void setInutilisable(){
        this.panelGrilleJoueur.setEnabled(false);
        this.panelSp.setEnabled(false);
    }

    public void setUtilisable(){
        this.panelGrilleJoueur.setEnabled(true);
        this.panelSp.setEnabled(true);
    }
}

