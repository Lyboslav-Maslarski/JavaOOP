package WorkingWithAbstraction.Exercise.greedyTimes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long capacity = Long.parseLong(scanner.nextLine());
        String[] loot = scanner.nextLine().split("\\s+");

        Bag bag = new Bag(capacity);

        for (int i = 0; i < loot.length; i += 2) {

            String currentLoot = loot[i];
            long amount = Long.parseLong(loot[i + 1]);

            bag.lootMaterial(currentLoot, amount);

        }
        System.out.println(bag);
    }
}