package src.ihm.grilles;

import javax.swing.table.AbstractTableModel;

import src.Controleur;
import src.metier.Noeud;

import java.util.ArrayList;

public class GrillesNoeudModel extends AbstractTableModel
{
    private Controleur ctrl;

	private String[]   tabEntetes;
    ArrayList<Noeud> lstNoeuds;

    public GrillesNoeudModel(Controleur ctrl)
    {
        this.ctrl = ctrl;

        Noeud n;
		lstNoeuds = this.ctrl.getLstNoeuds();
        
        this.tabEntetes = new String[] { "Nom", "X", "Y", "Nom X", "Nom Y"};
    }

    
    public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.lstNoeuds.size();      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	
    public Object getValueAt    (int row, int col) 
    { 
        switch(col)
        {
            case 0: return this.lstNoeuds.get(row).getNom();
            case 1: return String.valueOf(this.lstNoeuds.get(row).getX());
            case 2: return String.valueOf(this.lstNoeuds.get(row).getY());
            case 3: return this.lstNoeuds.get(row).getNomX();
            case 4: return this.lstNoeuds.get(row).getNomY();
            default: return null;
        }

    }

	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }

    public void majTable(ArrayList<Noeud> lstNoeuds)
    {   
        this.lstNoeuds = lstNoeuds; 
        this.fireTableRowsInserted(0, this.lstNoeuds.size() - 1);
    }

    public void setValueAt(Object value, int row, int col)
    {
        boolean bRet;
        
    }

    
}