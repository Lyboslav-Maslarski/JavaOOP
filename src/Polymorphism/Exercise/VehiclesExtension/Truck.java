package Polymorphism.Exercise.VehiclesExtension;

public class Truck extends Vehicle {

    Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 1.6, tankCapacity);
    }

    @Override
    public void refuel(double fuel) {
        if (fuel <= 0) {
            System.out.println("Fuel must be a positive number");
            return;
        }

        double newFuel = this.getFuelQuantity() + fuel * 0.95;
        if (newFuel <= this.getTankCapacity()) {
            this.setFuelQuantity(newFuel);
        } else {
            System.out.println("Cannot fit fuel in tank");
        }
    }
}
