package src.ihm.grilles;

import javax.swing.table.AbstractTableModel;

import java.awt.Color;
import java.util.List;

import src.Controleur;
import src.metier.Joueur;

public class GrillesJoueurModel extends AbstractTableModel {
    private Controleur  ctrl;
    private String[]    tabEntetes;
    private List<Joueur>   lstJoueur;
	
    public GrillesJoueurModel(Controleur ctrl){
        super();
        this.ctrl=ctrl;
        this.lstJoueur = this.ctrl.getMetier().getLstJoueurs();
        this.tabEntetes = new String[] { "Pseudo", "Couleur du Joueur"};

        majTable(this.lstJoueur);
    }

    public int    getColumnCount()                 { return this.tabEntetes.length;}
	public int    getRowCount   ()                 { return this.lstJoueur.size(); }
	public String getColumnName (int col)          { return this.tabEntetes[col];  }
    
    
    /** 
     * @param row
     * @param col
     * @return Object
     */
    @Override
    public Object getValueAt(int row, int col) {

        switch(col){
            case 0 : return this.lstJoueur.get(row).getNom();
            case 1 : return this.lstJoueur.get(row).getCouleur();
            default : return null;
        }
        
    }

    
    /** 
     * @param rowIndex
     * @param columnIndex
     * @return Color
     */
    public Color couleurBackground(int rowIndex, int columnIndex) 
    {
        return this.lstJoueur.get(rowIndex).getCouleur();
    }

    
    /** 
     * @param columnIndex
     * @return Class<?>
     */
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return getValueAt(0, columnIndex).getClass();
    }

    
    /** 
     * @param lstJoueur
     */
    public void majTable(List<Joueur> lstJoueur) {
        this.lstJoueur = lstJoueur;
        this.fireTableDataChanged();
    }

    
    /** 
     * @param value
     * @param row
     * @param col
     */
    public void setValueAt(Object value, int row, int col) {
        switch(col){
            case 0 : this.lstJoueur.get(row).setNomJoueur((String)value);break;
            default : break;
        }
        this.fireTableCellUpdated(row, col);
    }

}