package src.ihm.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import src.ihm.grilles.GrillesJoueurModel;


public class ColorCellRendererResultat extends DefaultTableCellRenderer {

    public ColorCellRendererResultat()
    {
        this.setOpaque(true);
    }

    
    /** 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return Component
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        
       Color tabCoul = (Color) value;
        
       c.setBackground(tabCoul);
       ((JLabel) c).setText("");

        return c;
    }
}