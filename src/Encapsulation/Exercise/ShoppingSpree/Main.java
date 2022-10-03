package Encapsulation.Exercise.ShoppingSpree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Person> personMap = new LinkedHashMap<>();
        Map<String, Product> productMap = new HashMap<>();

        String[] peopleInfo = scan.nextLine().split(";");
        for (String personData : peopleInfo) {
            String[] personDataParts = personData.split("=");
            String name = personDataParts[0];
            double money = Double.parseDouble(personDataParts[1]);
            try {
                Person person = new Person(name, money);
                personMap.put(name, person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        String[] productInfo = scan.nextLine().split(";");
        for (String productData : productInfo) {
            String[] productDataParts = productData.split("=");
            String name = productDataParts[0];
            double price = Double.parseDouble(productDataParts[1]);
            try {
                Product product = new Product(name, price);
                productMap.put(name, product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        String command = scan.nextLine();
        while (!"END".equals(command)) {
            String[] commandParts = command.split("\\s+");
            Person person = personMap.get(commandParts[0]);
            Product product = productMap.get(commandParts[1]);
            try {
                person.buyProduct(product);
                System.out.println(person.getName() + " bought " + product.getName());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            command = scan.nextLine();
        }
        personMap.values().forEach(System.out::println);
    }
}
