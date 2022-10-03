package Encapsulation.Exercise.PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");
        String pizzaName = input[1];
        int numberOfToppings = Integer.parseInt(input[2]);

        try {
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);

            input = scan.nextLine().split("\\s+");
            String type = input[1];
            String bakingTechnique = input[2];
            double weightInGrams = Double.parseDouble(input[3]);

            Dough dough = new Dough(type, bakingTechnique, weightInGrams);
            pizza.setDough(dough);

            input = scan.nextLine().split("\\s+");
            while (!input[0].equals("END")) {

                Topping topping = new Topping(input[1], Double.parseDouble(input[2]));

                pizza.addTopping(topping);

                input = scan.nextLine().split("\\s+");
            }
            System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
