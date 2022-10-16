package SOLID.Exercise.products;

public abstract class Drink implements Product {
    private final double milliliters;

    public Drink(double milliliters) {
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
