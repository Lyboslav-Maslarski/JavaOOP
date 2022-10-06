package Inheritance.Exercise.restaurant;

import java.math.BigDecimal;

public class Cake extends Dessert {
    private final double CAKE_GRAMS = 250;
    private final double CAKE_CALORIES = 1000;
    private final BigDecimal CAKE_PRICE = BigDecimal.valueOf(5);

    public Cake(String name) {
        super(name, BigDecimal.valueOf(5), 250, 1000);
    }
}
