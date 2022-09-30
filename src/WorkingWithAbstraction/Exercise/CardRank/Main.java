package WorkingWithAbstraction.Exercise.CardRank;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(scan.nextLine() + ":");
        Arrays.stream(CardRank.values()).forEach(value -> System.out.println(value.getInfo()));
    }
}
