package Inheritance.Exercise.restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage {
    private final double COFFEE_MILLILITERS = 50;
    private final BigDecimal COFFEE_PRICE = BigDecimal.valueOf(3.50);
    double caffeine;

    public Coffee(String name, double caffeine) {
        super(name, BigDecimal.valueOf(3.50), 50);
        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return caffeine;
    }
}
