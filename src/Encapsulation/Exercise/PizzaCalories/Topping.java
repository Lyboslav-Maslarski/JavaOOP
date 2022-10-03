package Encapsulation.Exercise.PizzaCalories;

import java.util.Arrays;

public class Topping {
    enum toppingTypes {
        Meat(1.2),
        Veggies(0.8),
        Cheese(1.1),
        Sauce(0.9);
        private double modifier;

        toppingTypes(double modifier) {
            this.modifier = modifier;
        }

        public double getModifier() {
            return modifier;
        }
    }

    private toppingTypes toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        boolean toppingExists = Arrays.stream(toppingTypes.values()).anyMatch(toppingTypes -> toppingTypes.name().equals(toppingType));
        if (toppingExists) {
            this.toppingType = toppingTypes.valueOf(toppingType);
        } else {
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(toppingType.name() + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return 2 * weight * toppingType.getModifier();
    }
}
