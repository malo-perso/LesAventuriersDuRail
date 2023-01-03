package src.ihm.grilles;

import javax.swing.table.AbstractTableModel;

import src.Controleur;
import src.metier.Arete;
import src.metier.CarteVehicule;
import src.metier.Type;

import java.awt.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 

/*
 *     ArrayList<Arete>   lstArete;

    public GrillesAreteModel(Controleur ctrl) {
        this.ctrl = ctrl;

        Arete a;
        this.lstArete = this.ctrl.getLstAretes();
 */

public class GrillesVehiculeModel extends AbstractTableModel
{
    private Controleur ctrl;

	private String[]   tabEntetes;
    //private HashMap<Integer, Color> hashVehicules;
    private Object[][] tabVehicules;
    private List<Type> lstType;

    public GrillesVehiculeModel(Controleur ctrl)
    {
        super();
        this.ctrl = ctrl;
        this.ctrl.getTypes();
        //hashVehicules = this.ctrl.getHashVehicules();
        
        /*
        this.hashVehicules = new HashMap<Integer, Color>();
        this.hashVehicules.put(12, Color.WHITE);
        this.hashVehicules.put(12, Color.BLUE);
        this.hashVehicules.put(12, Color.YELLOW);
        this.hashVehicules.put(12, Color.ORANGE);
        this.hashVehicules.put(12, Color.BLACK);
        this.hashVehicules.put(12, Color.RED);
        this.hashVehicules.put(12, Color.GREEN);
        this.hashVehicules.put(14, Color.PINK);
        */

        this.tabEntetes = new String[] { "nombre Carte", "Couleur"};
        //this.lstType = this.ctrl.getLstType();


        this.tabVehicules = new Object[][]{
            {"12", Color.WHITE},
            {"12", Color.BLUE},
            {"12", Color.YELLOW},
            {"12", Color.ORANGE},
            {"12", Color.BLACK},
            {"12", Color.RED},
            {"12", Color.GREEN},
            {"12", Color.PINK}
        };
        
    }

    public int getRowCount() 
    {
        return tabVehicules.length;
        //return hashVehicules.size();
        //return this.lstType.size();
    }

    public int getColumnCount() 
    {
        return tabEntetes.length;
    }

    public String getColumnName(int columnIndex) 
    {
        return tabEntetes[columnIndex];
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return tabVehicules[rowIndex][columnIndex];
        /*
        if (columnIndex == 0) {
            return hashVehicules.keySet().toArray()[rowIndex];
        } else {
            return hashVehicules.get(rowIndex);
        }
        */
    }
    
    @Override
    public Class getColumnClass(int columnIndex)
    {
        switch(columnIndex){
            case 4:
                return Color.class;
            default:
                return Color.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; //Toutes les cellules éditables
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //la valeur de chaque cellule doit etre un nombre sauf 0
        if(aValue instanceof Integer && (Integer)aValue > 0){
            tabVehicules[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        //la valeur de chaque cellule ne peut pas être null, egal a zero ou vide
        if(aValue != null && !aValue.equals("") && !aValue.equals(0)){
            tabVehicules[rowIndex][columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

    }
    
}