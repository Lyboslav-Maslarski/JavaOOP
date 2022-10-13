package Polymorphism.Exercise.Vehicles;

public class Truck extends Vehicle {
    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 1.6);
    }

    @Override
    public void drive(double distance) {
        double fuelNeeded = distance * getFuelConsumption();
        if (fuelNeeded <= getFuelQuantity()) {
            setFuelQuantity(getFuelQuantity() - fuelNeeded);
            System.out.println("Truck travelled " + decimalFormat.format(distance) + " km");
        } else {
            System.out.println("Truck needs refueling");
        }
    }

    @Override
    public void refuel(double fuel) {
        this.setFuelQuantity(this.getFuelQuantity() + fuel * 0.95);
    }

    @Override
    public String toString() {
        return "Truck" + super.toString();
    }
}
