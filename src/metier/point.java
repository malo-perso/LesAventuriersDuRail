package src.metier;

import java.util.ArrayList;
import java.util.List;

public class point {

    private int[][] matriceAdjacence;

    public point(List<Arete> aretes, List<Noeud> noeuds)
    {
        this.matriceAdjacence = new int[noeuds.size()][noeuds.size()];
        for (Arete arete : aretes) {
            
        }
    }


}
