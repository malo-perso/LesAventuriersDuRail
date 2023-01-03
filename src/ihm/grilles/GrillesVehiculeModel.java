package src.ihm.grilles;

import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;

import src.Controleur;
import src.metier.Arete;
import src.metier.CarteVehicule;
import src.metier.Type;

import java.awt.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.Font;

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
            {"12", Color.white},
            {"12", Color.blue},
            {"12", Color.yellow},
            {"12", Color.orange},
            {"12", Color.black},
            {"12", Color.red},
            {"12", Color.green},
            {"14", Color.pink}

        };
        Font font = new Font("Arial", Font.PLAIN, 15);
        UIManager.put("Table.font", font);
        
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
    
    public Color couleurBAckground(int rowIndex, int columnIndex) 
    {
        return (Color)  tabVehicules[rowIndex][columnIndex];
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
    public Class<?> getColumnClass(int columnIndex)
    {
        return getValueAt(0, columnIndex).getClass();
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