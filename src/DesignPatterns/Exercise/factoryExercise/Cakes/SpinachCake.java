package DesignPatterns.Exercise.factoryExercise.Cakes;

public class SpinachCake extends Cake {
    public SpinachCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + SpinachCake.class.getSimpleName());
    }

    @Override
    public void bake() {
        System.out.println("Baking " + SpinachCake.class.getSimpleName());
    }

    @Override
    public void box() {
        System.out.println("Boxing " + SpinachCake.class.getSimpleName());
    }

    @Override
    public String toString() {
        return "SpinachCake{" +
               "diameter=" + diameter +
               ", price=" + price +
               ", pieces=" + pieces +
               '}';
    }
}
