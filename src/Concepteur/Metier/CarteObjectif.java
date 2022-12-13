package Metier;

import java.util.ArrayList;;

public class CarteObjectif {
    private static ArrayList<CarteObjectif> pioche = new ArrayList<>();

    private Ville ville1;
    private Ville ville2;
    private int   points;

    public CarteObjectif(Ville ville1, Ville ville2, int points) {
        this.ville1 = ville1;
        this.ville2 = ville2;
        this.points = points;
    }
}
