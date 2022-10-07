package InterfacesAndAbstraction.Exercise.Telephony;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> phoneNumbers = Arrays.stream(scan.nextLine().split("\\s+")).collect(Collectors.toList());
        List<String> urls = Arrays.stream(scan.nextLine().split("\\s+")).collect(Collectors.toList());
        Smartphone smartphone = new Smartphone(phoneNumbers, urls);
        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());
    }
}
