package src.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import src.Controleur;
import src.ihm.grilles.GrillesVehiculeModel;
import src.ihm.renderer.ColorCellRenderer;
import src.metier.FonctionAux;
import src.metier.CarteVehicule;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.HashMap;

import java.util.ArrayList;


public class PanelVehicule extends JPanel implements ActionListener{

    private Controleur ctrl;

    private GrillesVehiculeModel model;
    private JPanel  panelNomVehicule;
    private JPanel  panelTable;
    private JPanel  panelCarte;
    private JPanel  panelVisuCarte;
    private JTextField txtNomVehicule,nbrJoker;
    private JButton btnRetour;
    private JButton btnSuivant;
    private JPanel  panelValidation;

    private Image CarteRecto;
    private Image CarteVerso;
    private Image CarteJoker;

    private Icon iconRecto;
    private Icon iconVerso;
    private Icon iconJoker;

    private JButton btnCarteRecto;
    private JButton btnCarteVerso;
    private JButton btnCarteVerso2;
    private JButton btnCarteJoker;

    private JTable jTabVehicule;

    private String[] tabEntetes;

    
    public PanelVehicule(Controleur ctrl)
    {
        //Creation des composants
        this.ctrl = ctrl;

        this.txtNomVehicule = new JTextField();

        this.model = new GrillesVehiculeModel(this.ctrl);
        //this.jTabVehicule = new JTable();
        //tabVehicules = this.ctrl.getHashVehicules();
        //(12 de chaque type : violet, blanc, bleu, jaune, orange, noir, rouge et vert et 14 locomotives)

        jTabVehicule = new JTable(this.model);
        jTabVehicule.setDefaultRenderer(Color.class, new ColorCellRenderer());

        this.jTabVehicule.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer());


        JScrollPane spTabVehicule = new JScrollPane(this.jTabVehicule);

        this.nbrJoker = new JTextField("14");

        this.iconVerso     = new ImageIcon("./src/data/images/Cartes_Vehicules/CarteVersoDefault.png");
        this.iconRecto     = new ImageIcon("./src/data/images/Cartes_Vehicules/wagonblanc.jpg");
        this.iconJoker     = new ImageIcon("./src/data/images/Cartes_Vehicules/loco.jpg");

        this.btnCarteJoker  = new JButton("Carte Joker",iconJoker);
        this.btnCarteRecto  = new JButton("Carte Recto",iconRecto);
        this.btnCarteVerso  = new JButton("Carte Verso",iconVerso);
        this.btnCarteVerso2 = new JButton("Carte Verso2",iconVerso);
        
        this.btnCarteJoker.setPreferredSize(new Dimension(100, 100));
        this.btnCarteRecto.setPreferredSize(new Dimension(100, 100));
        this.btnCarteVerso.setPreferredSize(new Dimension(100, 100));
        this.btnCarteVerso2.setPreferredSize(new Dimension(100, 100));
        
        btnCarteVerso.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCarteVerso.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCarteRecto.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCarteRecto.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCarteJoker.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCarteJoker.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCarteVerso2.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCarteVerso2.setHorizontalTextPosition(SwingConstants.CENTER);
        
        

        this.btnRetour = new JButton("Retour");
        this.btnSuivant = new JButton("Suivant");

        //Creation des layouts
        this.setLayout(new BorderLayout());
        this.panelNomVehicule = new JPanel(new GridLayout(1, 3));
        this.panelTable = new JPanel();
        this.panelCarte = new JPanel(new GridLayout(2,1));
        this.panelVisuCarte = new JPanel(new GridLayout(4, 2));

        this.panelValidation = new JPanel(new GridLayout(1, 3));

        // Positionnement des composants
        this.panelNomVehicule.add(new JLabel("Nom du véhicule :", SwingConstants.RIGHT));
        this.panelNomVehicule.add(this.txtNomVehicule);
        
        this.panelVisuCarte.add(this.btnCarteRecto);
        this.panelVisuCarte.add(this.btnCarteVerso);
        this.panelVisuCarte.add(new JLabel("Nombre Carte Joker :", SwingConstants.RIGHT));
        this.panelVisuCarte.add(this.nbrJoker);
        this.panelVisuCarte.add(this.btnCarteJoker);
        this.panelVisuCarte.add(this.btnCarteVerso2);
        this.panelVisuCarte.add(new JLabel());

        this.panelTable.add(jTabVehicule, BorderLayout.CENTER);

        this.panelCarte.add(this.panelTable , BorderLayout.NORTH);
        this.panelCarte.add(this.panelVisuCarte, BorderLayout.CENTER );

        this.panelValidation.add(this.btnRetour);
        this.panelValidation.add(new JLabel());
        this.panelValidation.add(this.btnSuivant);

        //Action listener
        this.btnRetour.addActionListener(this);
        this.btnSuivant.addActionListener(this);
        this.btnCarteJoker.addActionListener(this);
        this.btnCarteRecto.addActionListener(this);
        this.btnCarteVerso.addActionListener(this);
        this.btnCarteVerso2.addActionListener(this);


        this.add(this.panelCarte, BorderLayout.CENTER);
        this.add(this.panelValidation, BorderLayout.SOUTH);


        this.nbrJoker.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                System.out.println(e);
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // ignorer l'événement
                }
            }
        });

        this.setBackground(Color.PINK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ( e.getSource() == this.btnRetour) {
            this.ctrl.getIHM().changePanel("panelArrete");
        }

        if ( e.getSource() == this.btnSuivant) {
            this.ctrl.getIHM().changePanel("panelListeObjectif");
        }
        if(e.getSource() == this.btnCarteRecto){
            try{
				System.out.println("Catre recto");
				JFileChooser file = new JFileChooser("./src/data/images");
                //récupérer l'image choisi et l'afficher dans le bouton
                	if(file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
					CarteRecto = ImageIO.read(file.getSelectedFile());
					iconRecto = new ImageIcon(CarteRecto);
					btnCarteRecto.setIcon(iconRecto);
                    //si ce n'est pas l'image par défaut on enleeve le texte
                    if(!file.getSelectedFile().getName().equals("carteRectoDefault.png")){
                        btnCarteRecto.setText("");
                        btnCarteRecto.setText("Carte Recto");
                        Image img = ((ImageIcon) iconRecto).getImage();
                        Image newimg = img.getScaledInstance(this.btnCarteRecto.getWidth(),this.btnCarteRecto.getHeight(),  java.awt.Image.SCALE_SMOOTH);
                        iconRecto = new ImageIcon(newimg);
                        
                    }
                    else{
                        btnCarteRecto.setText("Carte Recto");
                        Image img = ((ImageIcon) iconRecto).getImage();
                        Image newimg = img.getScaledInstance(this.btnCarteRecto.getWidth(),this.btnCarteRecto.getHeight()-30,  java.awt.Image.SCALE_SMOOTH);
                        iconRecto = new ImageIcon(newimg);
                        
                    }
                    btnCarteRecto.setIcon(iconRecto);    
				}

            }catch(Exception erreur){erreur.printStackTrace();}
        }

        if(e.getSource() == this.btnCarteVerso || e.getSource() == this.btnCarteVerso2){
            try{
                System.out.println("Carte verso");
                JFileChooser file = new JFileChooser("./src/data/images");
                FileFilter filtre = new FileNameExtensionFilter("Image files", new String[]{"jpg","jpeg", "gif", "png"});
                file.setFileFilter(filtre);
                file.setAcceptAllFileFilterUsed(false);
                //récupérer l'image choisi et l'afficher dans le bouton
                    if(file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                    CarteVerso = ImageIO.read(file.getSelectedFile());
                    iconVerso = new ImageIcon(CarteVerso);
                    btnCarteVerso.setIcon(iconVerso);
                    //si ce n'est pas l'image par défaut on enleeve le texte
                    if(!file.getSelectedFile().getName().equals("carteVersoDefault.png")){
                        btnCarteVerso.setText("");
                        Image img = ((ImageIcon) iconVerso).getImage();
                        Image newimg = img.getScaledInstance(this.btnCarteRecto.getWidth(),this.btnCarteRecto.getHeight(),  java.awt.Image.SCALE_SMOOTH);
                        iconVerso = new ImageIcon(newimg);
                    }
                    else{
                        Image img = ((ImageIcon) iconVerso).getImage();
                        Image newimg = img.getScaledInstance(this.btnCarteRecto.getWidth(),this.btnCarteRecto.getHeight()-30,  java.awt.Image.SCALE_SMOOTH);
                        iconVerso = new ImageIcon(newimg);
                    }   
                    btnCarteVerso.setIcon(iconVerso);
                    btnCarteVerso2.setIcon(iconVerso);           
                }
            }
            catch(Exception erreur){erreur.printStackTrace();}
        }
        if(e.getSource() == this.btnCarteJoker){
            try{
				System.out.println("Carte Joker");
				JFileChooser file = new JFileChooser("./src/data/images");
                //récupérer l'image choisi et l'afficher dans le bouton
                	if(file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
					CarteJoker = ImageIO.read(file.getSelectedFile());
					iconJoker = new ImageIcon(CarteJoker);
					btnCarteJoker.setIcon(iconJoker);
                    //si ce n'est pas l'image par défaut on enleeve le texte
                    if(!file.getSelectedFile().getName().equals("loco.jpg")){
                        btnCarteJoker.setText("");
                        Image img = ((ImageIcon) iconJoker).getImage();
                        Image newimg = img.getScaledInstance(this.btnCarteJoker.getWidth(),this.btnCarteJoker.getHeight(),  java.awt.Image.SCALE_SMOOTH);
                        iconJoker = new ImageIcon(newimg);
                    }
                    else{
                        btnCarteJoker.setText("Carte Joker");
                        Image img = ((ImageIcon) iconJoker).getImage();
                        Image newimg = img.getScaledInstance(this.btnCarteJoker.getWidth(),this.btnCarteJoker.getHeight()-30,  java.awt.Image.SCALE_SMOOTH);
                        iconJoker = new ImageIcon(newimg);
                    }   
                    btnCarteJoker.setIcon(iconJoker);           
				}

            }catch(Exception erreur){erreur.printStackTrace();}
        }

    }
    
}
