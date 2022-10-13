package Polymorphism.Exercise.VehiclesExtension;

public class Bus extends Vehicle {


    Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + 1.4, tankCapacity);
    }

}
