package DesignPatterns.Exercise.factoryExercise.Cakes;

public class ChocolateCake extends Cake {
    public ChocolateCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + BiscuitCake.class.getSimpleName());
    }

    @Override
    public void bake() {
        System.out.println("Baking " + BiscuitCake.class.getSimpleName());
    }

    @Override
    public void box() {
        System.out.println("Boxing " + BiscuitCake.class.getSimpleName());
    }

    @Override
    public String toString() {
        return "ChocolateCake{" +
               "diameter=" + diameter +
               ", price=" + price +
               ", pieces=" + pieces +
               '}';
    }
}
