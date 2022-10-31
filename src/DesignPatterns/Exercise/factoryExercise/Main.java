package DesignPatterns.Exercise.factoryExercise;

import DesignPatterns.Exercise.factoryExercise.Cakes.Cake;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cakeType = scan.nextLine();
        Cake cake = Pastryshop.prepare(cakeType);
        System.out.println(cake);
    }
}
