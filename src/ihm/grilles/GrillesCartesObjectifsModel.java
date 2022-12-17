package src.ihm.grilles;

import javax.swing.table.AbstractTableModel;

import src.Controleur;
import src.metier.Noeud;
import src.metier.CarteObjectif;

import java.util.ArrayList;

public class GrillesCartesObjectifsModel extends AbstractTableModel {

    private Controleur ctrl;
    private String[]   tabEntetes;
    ArrayList<CarteObjectif> lstCarteObjectifs;

    public GrillesCartesObjectifsModel(Controleur ctrl){
        this.ctrl = ctrl;

        CarteObjectif co;
        lstCarteObjectifs = this.ctrl.getLstObjectifs();

        this.tabEntetes = new String[] {"Noeud1","Noeud2","Points"};
    }

    public int    getColumnCount()                 { return this.tabEntetes.length;       }
	public int    getRowCount   ()                 { return this.lstCarteObjectifs.size();}
	public String getColumnName (int col)          { return this.tabEntetes[col];         }

    @Override
    public Object getValueAt(int row, int col) {
        // TODO Auto-generated method stub

        switch(col){
            case 0 : return this.lstCarteObjectifs.get(row).getNoeud1();
            case 1 : return this.lstCarteObjectifs.get(row).getNoeud2();
            case 2 : return this.lstCarteObjectifs.get(row).getPoints();
            default : return null;
        }
        
    }

    public void majTable(ArrayList<CarteObjectif> lstCarteObjectifs)
    {   
        this.lstCarteObjectifs = lstCarteObjectifs;
        this.fireTableDataChanged();
    }

    public boolean isCellEditable(int row, int col)
    {
        return true;
    }


    
    
}