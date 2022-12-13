package IHM.asuppr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInformationPoint extends JPanel implements ActionListener {
    private JPanel pnlTEST;
    private JTextField[][] tabTxtPointVoie;
    public PanelInformationPoint()
    {
        this.setLayout(new BorderLayout());
        this.pnlTEST = new JPanel(new GridLayout(6,2));
        this.tabTxtPointVoie = new JTextField[6][2];

        for(int cpt =0;cpt<6;cpt++)
        {
            this.tabTxtPointVoie[cpt][0] = new JTextField(""+(cpt+1));
            this.tabTxtPointVoie[cpt][1] = new JTextField(""+((cpt+1)*2));

            this.pnlTEST.add(tabTxtPointVoie[cpt][0]);
            this.pnlTEST.add(tabTxtPointVoie[cpt][1]);
            this.tabTxtPointVoie[cpt][0].addActionListener(this);
            this.tabTxtPointVoie[cpt][1].addActionListener(this);
        }


        this.add(this.pnlTEST,BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent e)
    {


    }


}

