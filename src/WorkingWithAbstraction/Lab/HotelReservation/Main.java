package WorkingWithAbstraction.Lab.HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] tokens = scan.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(tokens[0]);
        int days = Integer.parseInt(tokens[1]);
        Season season = Season.parse(tokens[2]);
        DiscountType discountType = DiscountType.parse(tokens[3]);

        PriceCalculator priceCalculator = new PriceCalculator(pricePerDay, days, season, discountType);

        System.out.printf("%.2f", priceCalculator.calculatePrice());
    }
}
