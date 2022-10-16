package SOLID.Exercise.products;

public abstract class Food implements Product {
    private final double grams;

    public Food(double grams) {
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }

    @Override
    public double getAmount() {
        return grams / 1000;
    }
}
