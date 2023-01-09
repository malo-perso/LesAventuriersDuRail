package src.ihm.grilles;

import javax.swing.table.AbstractTableModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Controleur;
import src.metier.Joueur;

public class GrillesResultatsModel extends AbstractTableModel{
	private Controleur ctrl;
	private String[] tabEntetes;
	private List<Joueur> lstJoueur;
	private int      rang;
	private String   nomJoueur;
	private int      score;
	private int      nbWagonsRestants;
	private int      nbObjectifsReussis;

	public GrillesResultatsModel(Controleur ctrl){
		super();
		this.ctrl=ctrl;
		this.lstJoueur = this.ctrl.getLstJoueurs();
		this.tabEntetes = new String[] { "Rang", "Nom du Joueur", "Score", "Wagons Restants", "Objectifs Réussis"};

		majTable();
	}

	public int   getColumnCount()                 { return this.tabEntetes.length;}
	public int   getRowCount   ()                 { return this.ctrl.getLstJoueurs().size(); }
	public String getColumnName (int col)          { return this.tabEntetes[col];  }

	@Override
	public Object getValueAt(int row, int col){
		switch(col){
			case 0 : return this.rang;
			case 1 : return this.nomJoueur;
			case 2 : return this.score;
			case 3 : return this.nbWagonsRestants;
			case 4 : return this.nbObjectifsReussis;
			default : return null;
		}
	}

	public Color couleurBackground(int rowIndex, int columnIndex)
	{
		return this.lstJoueur.get(rowIndex).getCouleur();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return getValueAt(0, columnIndex).getClass();
	}

	public void majTable(){
		this.fireTableDataChanged();
	}

	public void setValueAt(Object value, int row, int col){
		switch(col){
			case 0 : this.rang = (int)value;break;
			case 1 : this.nomJoueur = (String)value;break;
			case 2 : this.score = (int)value;break;
			case 3 : this.nbWagonsRestants = (int)value;break;
			case 4 : this.nbObjectifsReussis = (int)value;break;
			default : break;
		}
		this.fireTableCellUpdated(row, col);
	}
}
