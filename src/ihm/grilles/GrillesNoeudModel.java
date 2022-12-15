package src.ihm.grilles;

import javax.swing.table.AbstractTableModel;

import src.Controleur;
import src.metier.Noeud;

import java.util.ArrayList;

public class GrillesNoeudModel extends AbstractTableModel
{
    private Controleur ctrl;

	private String[]   tabEntetes;
	private Object[][] tabDonnees;

    public GrillesNoeudModel(Controleur ctrl)
    {
        this.ctrl = ctrl;

        Noeud n;
		ArrayList<Noeud> lstNoeuds = this.ctrl.lstNoeudIHMtoXML();

		tabDonnees = new Object[lstNoeuds.size()][4];
        
        if(this.ctrl.lstNoeudXMLtoIHM() != null)
        {
            for (int i=0; i<lstNoeuds.size(); i++)
            {
                n = lstNoeuds.get(i);
                
                tabDonnees[i][0] = n.getNom();
                tabDonnees[i][1] = n.getX();
                tabDonnees[i][2] = n.getY();
                tabDonnees[i][3] = n.getNomX();
                tabDonnees[i][4] = n.getNomY();
            }

        }
        
        this.tabEntetes = new String[] { "Nom", "X", "Y", "Nom X", "Nom Y"};
    }

    
    public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }
	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }

    public void setValueAt(Object value, int row, int col)
    {
        this.tabDonnees[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}