package InterfacesAndAbstraction.Lab.BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Identifiable> identifiables = new ArrayList<>();

        String input = scan.nextLine();
        while (!input.equals("End")) {

            String[] data = input.split("\\s+");

            if (data.length == 3) {
                identifiables.add(new Citizen(data[0], Integer.parseInt(data[1]), data[2]));
            } else {
                identifiables.add(new Robot(data[0], data[1]));
            }

            input = scan.nextLine();
        }

        String fakeIdEnding = scan.nextLine();

        identifiables.stream()
                .map(Identifiable::getId)
                .filter(id->id.endsWith(fakeIdEnding))
                .forEach(System.out::println);
    }
}
