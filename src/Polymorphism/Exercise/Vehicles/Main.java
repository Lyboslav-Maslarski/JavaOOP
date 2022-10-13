package Polymorphism.Exercise.Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] carInfo = scan.nextLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));

        String[] truckInfo = scan.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        int n = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {

            String[] command = scan.nextLine().split("\\s+");
            double number = Double.parseDouble(command[2]);
            if ("Drive".equals(command[0])) {

                if ("Car".equals(command[1])) {
                    car.drive(number);
                } else {
                    truck.drive(number);
                }
            } else {
                if ("Car".equals(command[1])) {
                    car.refuel(number);
                } else {
                    truck.refuel(number);
                }
            }
        }
        System.out.println(car);
        System.out.println(truck);
    }
}
