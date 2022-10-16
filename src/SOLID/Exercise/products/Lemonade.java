package SOLID.Exercise.products;

public class Lemonade extends Drink {

    public static final double CALORIES_PER_100_GRAMS = 53.0;
    public static final double DENSITY = 0.7;

    public Lemonade(double milliliters) {
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
