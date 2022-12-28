package src.metier;

public class CarteVehicule {
    private Type type;
    
    public CarteVehicule(Type type) {
        this.type = type;
    }

    public String getType() {
        return this.type.toString();
    }
}



