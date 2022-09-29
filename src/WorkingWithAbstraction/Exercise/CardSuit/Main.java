package WorkingWithAbstraction.Exercise.CardSuit;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(scan.nextLine()+":");
        Arrays.stream(CardSuit.values()).forEach(value-> System.out.println(value.getInfo()));
    }
}
