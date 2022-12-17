package src.ihm.grilles;

import javax.swing.table.AbstractTableModel;

import src.Controleur;
import src.metier.CarteVehicule;

import java.awt.Color;

import java.util.ArrayList;

public class GrillesVehiculeModel extends AbstractTableModel
{
    private Controleur ctrl;

	private String[]   tabEntetes;
    ArrayList<CarteVehicule> lstVehicules;

    public GrillesVehiculeModel(Controleur ctrl)
    {
        this.ctrl = ctrl;
        
        this.tabEntetes = new String[] { "nombre Carte", "Couleur"};
    }

    
    public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	//public int    getRowCount   ()                 { return this.lstVehicules.size();      }
    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public Object getValueAt    (int row, int col) 
    { 
        switch(col)
        {
           // case 0: return (int) (this.lstVehicules.get(row);
            //case 1 : return color (this.lstVehicules.get(row).getColor());
            //case 1: return Color.decode(this.lstVehicules.get(row).getColor());
            

            default: return null;
        }
    }

   
    /*
	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }

    public void majTable(ArrayList<Noeud> lstVehicules)
    {   
        this.lstVehicules = lstVehicules; 
        this.fireTableRowsInserted(0, this.lstVehicules.size() - 1);
    }

    public void setValueAt(Object value, int row, int col)
    {
        boolean bRet;
    }
    */






    
}