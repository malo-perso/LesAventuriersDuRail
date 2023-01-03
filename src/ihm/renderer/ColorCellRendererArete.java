package src.ihm.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import src.ihm.grilles.GrillesAreteModel;

public class ColorCellRendererArete extends DefaultTableCellRenderer {

    public ColorCellRendererArete()
    {
        this.setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        GrillesAreteModel model = (GrillesAreteModel) table.getModel();

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        
       Color tabCoul = model.couleurBackground(row, column);
        
       
       c.setBackground(tabCoul);
       //cacher le texte
         ((JLabel) c).setText("");
         

        return c;
    }
}