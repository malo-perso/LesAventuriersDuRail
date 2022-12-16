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

		this.tabDonnees = new Object[lstNoeuds.size()][5];
        
        if(this.ctrl.lstNoeudXMLtoIHM() != null)
        {
            for (int lig=0; lig<lstNoeuds.size(); lig++)
            {
                n = lstNoeuds.get(lig);
                
                this.tabDonnees[lig][0] = n.getNom();
                this.tabDonnees[lig][1] = n.getX();
                this.tabDonnees[lig][2] = n.getY();
                this.tabDonnees[lig][3] = n.getNomX();
                this.tabDonnees[lig][4] = n.getNomY();
            }

        }
        
        this.tabEntetes = new String[] { "Nom", "X", "Y", "Nom X", "Nom Y"};
    }

    
    public int    getColumnCount()                 { return this.tabEntetes.length;      }
	public int    getRowCount   ()                 { return this.tabDonnees.length;      }
	public String getColumnName (int col)          { return this.tabEntetes[col];        }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col];   }
	public Class  getColumnClass(int c)            { return getValueAt(0, c).getClass(); }

    public void majTable(ArrayList<Noeud> lstNoeuds)
    {   
        Noeud n;

        tabDonnees = new Object[lstNoeuds.size()][5];
        
        if(this.ctrl.lstNoeudXMLtoIHM() != null)
        {
            for (int lig=0; lig<lstNoeuds.size(); lig++)
            {
                n = lstNoeuds.get(lig);
                
                this.tabDonnees[lig][0] = n.getNom();
                this.tabDonnees[lig][1] = n.getX();
                this.tabDonnees[lig][2] = n.getY();
                this.tabDonnees[lig][3] = n.getNomX();
                this.tabDonnees[lig][4] = n.getNomY();
            }

        }
        
        this.tabEntetes = new String[] { "Nom", "X", "Y", "Nom X", "Nom Y"};
    }

    public void setValueAt(Object value, int row, int col)
    {
        boolean bRet;

        if ( col > 0 )
		{
			bRet = this.ctrl.majPosNoeud ( row, col, (Integer) value );
			if ( bRet )
			{
				this.tabDonnees[row][col] = value;
				this.fireTableCellUpdated(row, col);
			}
		}
        else {
            this.tabDonnees[row][col] = value;
			this.fireTableCellUpdated(row, col);
        }
    }
}