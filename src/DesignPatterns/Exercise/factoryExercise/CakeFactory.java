package DesignPatterns.Exercise.factoryExercise;

import DesignPatterns.Exercise.factoryExercise.Cakes.*;

public class CakeFactory {
    public static Cake createCake(String cakeType, double diameter, double price, int pieces) {
        return switch (cakeType) {
            case "BiscuitCake" -> new BiscuitCake(diameter, price, pieces);
            case "ChocolateCake" -> new ChocolateCake(diameter, price, pieces);
            case "SpinachCake" -> new SpinachCake(diameter, price, pieces);
            case "WhiteCake" -> new WhiteCake(diameter, price, pieces);
            default -> null;
        };
    }
}
