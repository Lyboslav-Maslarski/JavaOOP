package Polymorphism.Exercise.Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    protected final static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    protected Vehicle(double fuelQuantity, double fuelConsumption) {
        setFuelQuantity(fuelQuantity);
        setFuelConsumption(fuelConsumption);
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected double getFuelConsumption() {
        return fuelConsumption;
    }

    private void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public abstract void drive(double distance);

    public abstract void refuel(double fuel);

    @Override
    public String toString() {
        return String.format(": %.2f", fuelQuantity);
    }
}
