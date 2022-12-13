package livrable.IHM;

import javax.swing.*;
import java.awt.*;

public class PanelConcepteur extends JPanel {

    private PanelPlateau pnlPlateau;
    public PanelConcepteur() {
        this.setLayout(new BorderLayout());

        this.pnlPlateau = new PanelPlateau();

    }
}
