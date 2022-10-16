package SOLID.Exercise.products;

public class Coke extends Drink {

    public static final double CALORIES_PER_100_GRAMS = 44.0;
    public static final double DENSITY = 0.6;

    public Coke(double milliliters) {
        super(milliliters);
    }

    @Override
    public double getAmountOfCalories() {
        return (CALORIES_PER_100_GRAMS / 100) * getMilliliters() * DENSITY;
    }

    @Override
    public double getAmount() {
        return (getMilliliters() / 1000) * DENSITY;
    }
}
