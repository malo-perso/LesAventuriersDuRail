package src.ihm.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import src.ihm.grilles.GrillesVehiculeModel;



public class ColorCellRenderer extends DefaultTableCellRenderer {

    public ColorCellRenderer()
    {
        this.setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        GrillesVehiculeModel model = (GrillesVehiculeModel) table.getModel();

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Color tabCoul = model.couleurBAckground(row, column);

        c.setBackground(tabCoul);

        return c;
    }
}