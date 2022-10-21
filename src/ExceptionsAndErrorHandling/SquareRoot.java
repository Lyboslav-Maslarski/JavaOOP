package ExceptionsAndErrorHandling;

import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double sqrt = -1;
        int number;
        try {
            number = Integer.parseInt(scan.nextLine());
            sqrt = sqrt(number);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid");
        }
        if (sqrt != -1) {
            System.out.printf("%.2f%n", sqrt);
        }
        System.out.println("Goodbye");
    }

    public static double sqrt(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        return Math.sqrt(n);
    }
}
