package Polymorphism.Exercise.Vehicles;

public class Car extends Vehicle {
    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 0.9);
    }

    @Override
    public void drive(double distance) {
        double fuelNeeded = distance * getFuelConsumption();
        if (fuelNeeded <= getFuelQuantity()) {
            setFuelQuantity(getFuelQuantity() - fuelNeeded);
            System.out.println("Car travelled " + decimalFormat.format(distance) + " km");
        } else {
            System.out.println("Car needs refueling");
        }
    }

    @Override
    public void refuel(double fuel) {
        this.setFuelQuantity(this.getFuelQuantity() + fuel);
    }

    @Override
    public String toString() {
        return "Car" + super.toString();
    }
}
