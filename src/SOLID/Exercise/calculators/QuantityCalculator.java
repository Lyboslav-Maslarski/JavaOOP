package SOLID.Exercise.calculators;

import SOLID.Exercise.products.Product;

import java.util.List;

public class QuantityCalculator implements Calculator{
    @Override
    public double sum(List<Product> products) {
        return products.stream().mapToDouble(Product::getAmount).sum();
    }
    @Override
    public double average(List<Product> products) {
        return this.sum(products) / products.size();
    }

}
