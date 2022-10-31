package DesignPatterns.Exercise.factoryExercise;

import DesignPatterns.Exercise.factoryExercise.Cakes.Cake;

public class Pastryshop {
    public static Cake prepare(String cakeType) {
        Cake cake = CakeFactory.createCake(cakeType, 10, 20, 8);
        cake.prepare();
        cake.bake();
        cake.box();
        return cake;
    }
}
