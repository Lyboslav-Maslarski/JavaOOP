package Polymorphism.Exercise.VehiclesExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] carInfo = scan.nextLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]), Double.parseDouble(carInfo[3]));

        String[] truckInfo = scan.nextLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]), Double.parseDouble(truckInfo[3]));

        String[] busInfo = scan.nextLine().split("\\s+");
        Vehicle bus = new Bus(Double.parseDouble(busInfo[1]), Double.parseDouble(busInfo[2]), Double.parseDouble(busInfo[3]));

        Map<String, Vehicle> vehicleMap = new HashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        vehicleMap.put("Bus", bus);

        int n = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {
            String[] command = scan.nextLine().split("\\s+");
            double number = Double.parseDouble(command[2]);

            if ("Drive".equals(command[0])) {
                vehicleMap.get(command[1]).drive(0, number);

            } else if ("Refuel".equals(command[0])) {

                vehicleMap.get(command[1]).refuel(number);

            } else if ("DriveEmpty".equals(command[0])) {
                vehicleMap.get(command[1]).drive(1.4, number);
            }
        }
        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }
}
