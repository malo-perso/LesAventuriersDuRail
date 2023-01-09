package src.metier;

public class CarteVehicule implements Comparable<CarteVehicule>{

    private Type type;
    
    public CarteVehicule(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public int compareTo(CarteVehicule o) {
        if( this.getType().getColor() == null){
            return 0;
        }
        return this.type.getColor().getRGB() - o.getType().getColor().getRGB();
    }
}