package Polymorphism.Exercise.WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Animal> animals = new ArrayList<>();
        String command = scan.nextLine();
        while (!"End".equals(command)) {
            String[] animalInfo = command.split("\\s+");
            String[] foodInfo = scan.nextLine().split("\\s+");
            Animal animal;
            if ("Cat".equals(animalInfo[0])) {
                animal = new Cat(animalInfo[0], animalInfo[1], Double.parseDouble(animalInfo[2]), animalInfo[3], animalInfo[4]);
            } else if ("Tiger".equals(animalInfo[0])) {
                animal = new Tiger(animalInfo[0], animalInfo[1], Double.parseDouble(animalInfo[2]), animalInfo[3]);
            } else if ("Zebra".equals(animalInfo[0])) {
                animal = new Zebra(animalInfo[0], animalInfo[1], Double.parseDouble(animalInfo[2]), animalInfo[3]);
            } else {
                animal = new Mouse(animalInfo[0], animalInfo[1], Double.parseDouble(animalInfo[2]), animalInfo[3]);
            }
            Food food;
            if ("Meat".equals(foodInfo[0])) {
                food = new Meat(Integer.parseInt(foodInfo[1]));
            } else {
                food = new Vegetable(Integer.parseInt(foodInfo[1]));
            }
            animal.makeSound();
            animal.eat(food);
            animals.add(animal);
            command = scan.nextLine();
        }
        animals.forEach(System.out::println);
    }
}
