package src.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Point {
    
    private static int INF = (int) 1e7;

    public static boolean aReussitDestination(List<Arete> aretes, List<Noeud> noeuds,Joueur joueur, CarteObjectif carteObjectif) {
        int[][] matriceTrajet = matriceTrajet(noeuds,aretes,joueur);
        int[][] parcours = initParcours(noeuds, matriceTrajet);
        floydWarshall(noeuds,matriceTrajet, parcours);

        Vector<Integer> chemin = constructionCheminh(noeuds.indexOf(carteObjectif.getNoeud1()), noeuds.indexOf(carteObjectif.getNoeud2()),matriceTrajet, parcours);

        if (chemin == null || chemin.size() == 0)
            return false;
        else
            return true;
    }

     public static int[][] matriceTrajet(List<Noeud> noeuds,List<Arete> aretes,Joueur joueur) {
        int[][] matrixe = new int[noeuds.size()][noeuds.size()];
        for (int i = 0; i < matrixe.length; i++) {
            for (int j = 0; j < matrixe.length; j++) {
                matrixe[i][j] = INF;
            }
        }

        for (Arete t : aretes) {
            if ( t.getProprietaire() == joueur){
                matrixe[noeuds.indexOf(t.noeud1)][noeuds.indexOf(t.noeud2)]=t.getLongueur();
                matrixe[noeuds.indexOf(t.noeud2)][noeuds.indexOf(t.noeud1)]=t.getLongueur();
            }
        }
        return matrixe;
    }

    public static int[][] initParcours(List<Noeud> noeuds,int[][] matriceTrajet)
    {
        int [][] parcours = new int [noeuds.size()][noeuds.size()];
        for (int i = 0; i < matriceTrajet.length; i++) {
            for (int j = 0; j < matriceTrajet.length; j++) {
                if (matriceTrajet[i][j] == INF)
                    parcours[i][j] = -1;
                else
                    parcours[i][j] = j;
            }
        }
        return parcours;
    }

    public static void floydWarshall(List<Noeud> noeuds,int[][] matriceTrajet,int[][] parcours)
    {
        int V=noeuds.size();
        for(int k = 0; k < V; k++)
            for(int i = 0; i < V; i++)
                for(int j = 0; j < V; j++)
                {
                    if (matriceTrajet[i][k] == INF || matriceTrajet[k][j] == INF)
                        continue;
                        
                    if (matriceTrajet[i][j] > matriceTrajet[i][k] + matriceTrajet[k][j])
                    {
                        matriceTrajet[i][j] = matriceTrajet[i][k] + matriceTrajet[k][j];
                        parcours[i][j] = parcours[i][k];
                    }
                }
    }

    public static Vector<Integer> constructionCheminh(int u,int v,int[][] matriceTrajet,int[][] parcours){

        int distance = 0;
        if (parcours[u][v] == -1)
            return null;
    
        Vector<Integer> path = new Vector<Integer>();
        path.add(u);
        
        while (u != v)
        {
            distance += matriceTrajet[u][parcours[u][v]];
            u = parcours[u][v];
            path.add(u);
        }
        return path;
    }

    public static int longueurCheminh(int u,int v,int[][] matriceTrajet,int[][] parcours){

        int distance = 0;
        if (parcours[u][v] == -1)
            return 0;
            
        while (u != v)
        {
            distance += matriceTrajet[u][parcours[u][v]];
            u = parcours[u][v];
        }
        return distance;
    }

    public static int cheminLePlusLong(List<Arete> aretes, List<Noeud> noeuds,Joueur joueur)
    {
        //16 21 
        int max = 0;
        int longueur = 0;
        int[][] matriceTrajet = matriceTrajet(noeuds,aretes,joueur);
        int[][] parcours = initParcours(noeuds, matriceTrajet);
        floydWarshall(noeuds,matriceTrajet, parcours);

        for (Noeud noeud1 : noeuds) 
            for (Noeud noeud2 : noeuds) 
                if (noeud1 != noeud2){
                    longueur = longueurCheminh(noeuds.indexOf(noeud1), noeuds.indexOf(noeud2), matriceTrajet, parcours);
                    if (longueur > max)
                        max = longueur;
                }
        
        return max;
    }




}
