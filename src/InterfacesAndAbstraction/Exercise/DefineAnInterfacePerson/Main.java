package InterfacesAndAbstraction.Exercise.DefineAnInterfacePerson;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Buyer> buyers = new HashMap<>();
        int n = Integer.parseInt(scan.nextLine());
        while (n-- > 0) {
            String input = scan.nextLine();

            String[] data = input.split("\\s+");

            if (data.length == 4) {
                buyers.put(data[0], new Citizen(data[0], Integer.parseInt(data[1]), data[2], data[3]));
            } else {
                buyers.put(data[0], new Rebel(data[0], Integer.parseInt(data[1]), data[2]));
            }
        }
        String input = scan.nextLine();
        while (!input.equals("End")) {
            if (buyers.containsKey(input)) {
                buyers.get(input).buyFood();
            }
            input = scan.nextLine();
        }
        System.out.println(buyers.values().stream()
                .mapToInt(Buyer::getFood)
                .sum());
    }
}
