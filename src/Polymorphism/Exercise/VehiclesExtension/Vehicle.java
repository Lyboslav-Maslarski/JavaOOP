package Polymorphism.Exercise.VehiclesExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;
    protected final static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        setFuelQuantity(fuelQuantity);
        setFuelConsumption(fuelConsumption);
        setTankCapacity(tankCapacity);
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity<0){
            System.out.println("Fuel must be a positive number");
            this.fuelQuantity=0;
            return;
        }
        this.fuelQuantity = fuelQuantity;
    }

    protected double getFuelConsumption() {
        return fuelConsumption;
    }

    private void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void drive(double modifier, double distance) {
        double fuelNeeded = distance * (getFuelConsumption() - modifier);
        if (fuelNeeded <= getFuelQuantity()) {
            setFuelQuantity(getFuelQuantity() - fuelNeeded);
            System.out.println(getClass().getSimpleName() + " travelled " + decimalFormat.format(distance) + " km");
        } else {
            System.out.println(getClass().getSimpleName() + " needs refueling");
        }
    }

    public void refuel(double fuel) {
        if (fuel <= 0) {
            System.out.println("Fuel must be a positive number");
            return;
        }

        double newFuel = this.getFuelQuantity() + fuel;
        if (newFuel <= tankCapacity) {
            this.setFuelQuantity(newFuel);
        } else {
            System.out.println("Cannot fit fuel in tank");
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getClass().getSimpleName(), fuelQuantity);
    }
}
