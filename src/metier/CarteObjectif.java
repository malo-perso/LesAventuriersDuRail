package src.metier;
import java.util.ArrayList;;

public class CarteObjectif {
    private static ArrayList<CarteObjectif> pioche = new ArrayList<>();

    private Noeud noeud1;
    private Noeud noeud2;
    private int   points;

    public CarteObjectif(Noeud noeud1, Noeud noeud2, int points) {
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.points = points;
    }
}
