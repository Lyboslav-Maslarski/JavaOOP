package SOLID.Exercise;

import SOLID.Exercise.calculators.QuantityCalculator;
import SOLID.Exercise.products.Coke;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer(new QuantityCalculator());
        printer.printAverage(List.of(new Coke(100)));
    }
}
