package ExceptionsAndErrorHandling;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class NumberInRange {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] range = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int start = range[0];
        int end = range[1];

        System.out.println("Range: [" + start + "..." + end + "]");

        String input = scan.nextLine();

        boolean isInRange = false;

        while (!isInRange) {
            OptionalInt number = OptionalInt.empty();
            try {
                number = OptionalInt.of(Integer.parseInt(input));
            } catch (NumberFormatException ignored) {
            }
            if (number.isEmpty() || number.getAsInt() < start || number.getAsInt() > end) {
                System.out.println("Invalid number: " + input);
                input = scan.nextLine();
            } else {
                isInRange = true;
            }
        }
        System.out.println("Valid number: " + input);
    }
}
