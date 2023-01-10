package src.metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src.Controleur;

public class Pioche {
    
    private List<CarteVehicule> lstCartesVehicule;
    private List<CarteObjectif> lstCartesObjectif;
    private Controleur ctrl;
    
    public Pioche(Controleur ctrl, List<CarteVehicule> lstCartesVehicule, List<CarteObjectif> lstCartesObjectif) {

        this.ctrl = ctrl;

        this.lstCartesVehicule = lstCartesVehicule;
        this.lstCartesObjectif = lstCartesObjectif;
    }

    
    /** 
     * @return List<CarteVehicule>
     */
    public List<CarteVehicule> getLstCarteVehicule() {
        return this.lstCartesVehicule;
    }

    
    /** 
     * @return ArrayList<CarteObjectif>
     */
    public ArrayList<CarteObjectif> getLstCarteObjectif() {
        ArrayList<CarteObjectif> lstObj = new ArrayList<CarteObjectif>();
        //lstObj.add(new CarteObjectif(new Noeud("Plic", 0, 0, 0, 0), new Noeud("Ploc", 1, 1, 1, 1), 10));
        return lstObj;
        //return this.lstCartesObjectif;
    }

    
    /** 
     * @return CarteVehicule[]
     */
    public CarteVehicule[] majPiocheVehiculeVisible() {
        CarteVehicule[] piocheVehiculeVisible = new CarteVehicule[5];
        for (int i = 0; i < 5; i++) {
            if (i < this.lstCartesVehicule.size())
                piocheVehiculeVisible[i] = this.lstCartesVehicule.get(i);
            else
                piocheVehiculeVisible[i] = null;
        }

        int nbJoker = 0;
        for (int i = 0; i < 5; i++) {
            if (i < this.lstCartesVehicule.size()){
                if (piocheVehiculeVisible[i].getType().getColor().equals(this.ctrl.getMetier().getCouleurJoker()))
                    nbJoker++;
            }
        }

        if (nbJoker >= 3 && this.lstCartesVehicule.size() > 5) {
            int nbJokerCarte = 0;
            for (CarteVehicule carteVehicule : this.lstCartesVehicule) {
                if (carteVehicule.getType().getColor().equals(this.ctrl.getMetier().getCouleurJoker()))
                    nbJokerCarte++;
            }
            // if (this.ctrl.getIHM() != null) {
            //     this.ctrl.getIHM().afficherMsgInfo("Il y a plus de 3 jokers dans la pioche visible, on mélange la pioche");
            // }

            if (nbJokerCarte>this.lstCartesVehicule.size()-3){
                return piocheVehiculeVisible;
            }
            Collections.shuffle(this.lstCartesVehicule);
            return this.majPiocheVehiculeVisible();
        }

        return piocheVehiculeVisible;
    }

    
    /** 
     * @param i
     * @return CarteVehicule
     */
    public CarteVehicule retirerCarteVehicule(int i) {
        if (this.lstCartesVehicule.size() <= 5)
            return this.lstCartesVehicule.remove(i);
        else {
            CarteVehicule carte = this.lstCartesVehicule.get(i);
            this.lstCartesVehicule.set(i, this.lstCartesVehicule.get(5));
            this.lstCartesVehicule.remove(5);
            return carte;
        }
    }

    
    /** 
     * @param i
     * @return CarteObjectif
     */
    public CarteObjectif retirerCarteObjectif(int i) {
        return this.lstCartesObjectif.remove(i);
    }

    
    /** 
     * @param carte
     */
    public void ajouterCarteVehicule(CarteVehicule carte) {
        this.lstCartesVehicule.add(carte);
    }

    
    /** 
     * @param carte
     */
    public void ajouterCarteObjectif(CarteObjectif carte) {
        this.lstCartesObjectif.add(carte);
    }

    public void melangerCarteVehicule() {
        Collections.shuffle(this.lstCartesVehicule);
    }

    public void melangerCarteObjectif() {
        Collections.shuffle(this.lstCartesObjectif);
    }

    
    /** 
     * @param i
     * @return CarteVehicule
     */
    public CarteVehicule piocherVehicule(int i) {
        if (i >= 0 && i < 7 && i <= this.lstCartesVehicule.size()) {
            if (i == 6){
                CarteVehicule carteVehi = this.retirerCarteVehicule(i-1);
                this.ctrl.majPioche();
                return carteVehi;
            }else{
                CarteVehicule carteVehi = this.retirerCarteVehicule(i);
                this.ctrl.majPioche();
                return carteVehi;
            }
            
        }
        this.ctrl.majPioche();
        return null;
    }

    
    /** 
     * @return List<CarteObjectif>
     */
    public List<CarteObjectif> piocherObjectif() {
        int tmp = 3;
        List<CarteObjectif> lstPiocheObjectif = new ArrayList<CarteObjectif>();
        if(this.lstCartesObjectif.size()<=3)
            tmp = this.lstCartesObjectif.size();
        for(int i=0; i<tmp; i++)
        {
            // if(this.lstCartesObjectif.size()<=3)
            // {
            //     for(int j=0; j<this.lstCartesObjectif.size(); j++)
            //         lstPiocheObjectif.add(this.lstCartesObjectif.get(j));
            // }
            // else
            lstPiocheObjectif.add(this.lstCartesObjectif.get(i));
            
        }
        System.out.println(lstPiocheObjectif);
        return lstPiocheObjectif;
    }

    
    /** 
     * @param joueur
     * @param intCarte
     */
    public void deffausserCarteObjectif(Joueur joueur, ArrayList<Integer> intCarte) {
        for (int i = intCarte.size() - 1; i >=0; i--) {
            joueur.ajouterCarteObjectif(this.lstCartesObjectif.remove(i));
        }     
    }

    
    /** 
     * @param cateDefausse
     */
    public void ajouterCartePioche(List<CarteVehicule> cateDefausse) {
        this.lstCartesVehicule.addAll(cateDefausse);
        this.ctrl.majPioche();
    }
    
}
