package DesignPatterns.Exercise.factoryExercise.Cakes;

public class WhiteCake extends Cake {
    public WhiteCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + WhiteCake.class.getSimpleName());
    }

    @Override
    public void bake() {
        System.out.println("Baking " + WhiteCake.class.getSimpleName());
    }

    @Override
    public void box() {
        System.out.println("Boxing " + WhiteCake.class.getSimpleName());
    }

    @Override
    public String toString() {
        return "WhiteCake{" +
               "diameter=" + diameter +
               ", price=" + price +
               ", pieces=" + pieces +
               '}';
    }
}
