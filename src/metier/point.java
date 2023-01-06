package src.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class point {

    private int[][] matriceTrajet;
    private int[][] parcours;

    
    private static int INF = (int) 1e7;

    public point(List<Arete> aretes, List<Noeud> noeuds)
    {
            
    }

    public boolean aReussitDestination(List<Arete> aretes, List<Noeud> noeuds,Joueur joueur, CarteObjectif carteObjectif) {
        this.matriceTrajet = matriceTrajet(noeuds,aretes,joueur);
        this.parcours = initParcours(noeuds);
        floydWarshall(noeuds);

        Vector<Integer> chemin = constructionCheminh(noeuds.indexOf(carteObjectif.getNoeud1()), noeuds.indexOf(carteObjectif.getNoeud2()));
        if (chemin.size() == 0)
            return false;
        else
            return true;
    }

     public int[][] matriceTrajet(List<Noeud> noeuds,List<Arete> aretes,Joueur joueur) {
        int[][] matrixe = new int[noeuds.size()][noeuds.size()];
        for (int i = 0; i < matrixe.length; i++) {
            for (int j = 0; j < matrixe.length; j++) {
                matrixe[i][j] = INF;
            }
        }

        for (Arete t : aretes) {
            if ( t.getProprietaire() == joueur){
                matrixe[noeuds.indexOf(t.noeud1)][noeuds.indexOf(t.noeud2)]=1;
                matrixe[noeuds.indexOf(t.noeud2)][noeuds.indexOf(t.noeud1)]=1;
            }
        }
        return matrixe;
    }

    public int[][] initParcours(List<Noeud> noeuds)
    {
        int [][] parcours = new int [noeuds.size()][noeuds.size()];
        for (int i = 0; i < this.matriceTrajet.length; i++) {
            for (int j = 0; j < this.matriceTrajet.length; j++) {
                if (this.matriceTrajet[i][j] == INF)
                    parcours[i][j] = -1;
                else
                    parcours[i][j] = j;
            }
        }
        return parcours;
    }

    public void floydWarshall(List<Noeud> noeuds)
    {
        int V=noeuds.size();
        for(int k = 0; k < V; k++)
            for(int i = 0; i < V; i++)
                for(int j = 0; j < V; j++)
                {
                    if (this.matriceTrajet[i][k] == INF || this.matriceTrajet[k][j] == INF)
                        continue;
                        
                    if (this.matriceTrajet[i][j] > this.matriceTrajet[i][k] + this.matriceTrajet[k][j])
                    {
                        this.matriceTrajet[i][j] = this.matriceTrajet[i][k] + this.matriceTrajet[k][j];
                        this.parcours[i][j] = this.parcours[i][k];
                    }
                }
    }

    public  Vector<Integer> constructionCheminh(int u,int v){

        int distance = 0;
        if (this.parcours[u][v] == -1)
            return null;
    
        Vector<Integer> path = new Vector<Integer>();
        path.add(u);
        
        while (u != v)
        {
            distance += this.matriceTrajet[u][this.parcours[u][v]];
            u = this.parcours[u][v];
            path.add(u);
        }
        return path;
    }

    public int distanceTrajet(Vector<Integer> path) {
        return path.size();
    }


}
