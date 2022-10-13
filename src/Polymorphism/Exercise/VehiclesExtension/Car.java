package Polymorphism.Exercise.VehiclesExtension;

public class Car extends Vehicle {

    Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 0.9, tankCapacity);
    }
}
