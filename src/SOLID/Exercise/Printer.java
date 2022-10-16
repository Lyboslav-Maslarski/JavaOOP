package SOLID.Exercise;

import SOLID.Exercise.calculators.Calculator;
import SOLID.Exercise.products.Product;

import java.util.List;

public class Printer {
    private final Calculator calculator;

    public Printer(Calculator calculator) {
        this.calculator = calculator;
    }

    private static final String SUM_FORMAT = "Sum: %f";
    private static final String AVERAGE_FORMAT = "Average: %f";

    private void print(String format, double amount) {
        System.out.printf(format + "%n", amount);
    }

    public void printSum(List<Product> products) {
        print(SUM_FORMAT, calculator.sum(products));
    }

    public void printAverage(List<Product> products) {
        print(AVERAGE_FORMAT, calculator.average(products));
    }
}
